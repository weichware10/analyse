package github.weichware10.analyse.logic;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.config.HeatmapConfig;
import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.ToolType;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.data.DataPoint;
import github.weichware10.util.data.TrialData;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * verantwortlich für die Erstellung der Heatmap.
 */
public class Heatmap {

    /**
     * Erstellt Heatmap.
     *
     * @param trial    - Versuch
     * @param hmConfig - Konfiguration
     * @return ?
     */
    public static String createHeatmap(TrialData trial, HeatmapConfig hmConfig) {
        Configuration config = Main.dataBaseClient.configurations.get(trial.configId);

        // Load image for width & height
        String imageUrl = null;
        ;
        try {
            imageUrl = Files.saveImage(config.getImageUrl());
        } catch (IllegalArgumentException | IOException e) {
            Logger.error("Failed to save img", e, true);
        }
        Image image = new Image(imageUrl);
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Create image
        BufferedImage heatmap = null;
        double opacity;

        if (hmConfig.isImage()) {
            try {
                heatmap = ImageIO.read(new File(imageUrl));
            } catch (IOException e) {
                Logger.error("Failed to load image", e, true);
            }
            opacity = 0.4f;
        } else {
            heatmap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            opacity = 1.0f;
        }
        Graphics2D graphic = heatmap.createGraphics();
        // Tiefe 0 setzten
        graphic.setColor(calcColor(0.0f, opacity,
                hmConfig.getMinColorDiff(), hmConfig.getMaxColorDiff()));
        graphic.fillRect(0, 0, width, height);

        if (trial.toolType == ToolType.ZOOMMAPS) {
            // TODO: Implementieren
        } else if (trial.toolType == ToolType.CODECHARTS) {
            // Rechtecke der Durchläufe setzen
            for (DataPoint dataPoint : trial.getData()) {
                // Relative Tiefe berechnen
                double relDepth = (double) dataPoint.depth
                        / (double) config.getCodeChartsConfiguration().getMaxDepth();

                // Rechteck färben und setzten
                graphic.setColor(calcColor(relDepth, opacity,
                        hmConfig.getMinColorDiff(), hmConfig.getMaxColorDiff()));
                graphic.fillRect((int) dataPoint.viewport.getMinX(),
                        (int) dataPoint.viewport.getMinY(),
                        (int) dataPoint.viewport.getWidth(),
                        (int) dataPoint.viewport.getHeight());
            }
        }

        graphic.dispose();

        // save generated image
        String imgLocation = null;
        try {
            imgLocation = Files.saveGeneratedImage(heatmap, "test.png");
        } catch (IllegalArgumentException | IOException e) {
            Logger.error("Failed to save the image", e, true);
        }

        return imgLocation;
    }

    /**
     * Erstellt Heatmap-Vergleich.
     *
     * @param trial     - 1. Versuch
     * @param trialComp - 2. Versuch
     * @param hmConfig  - Konfiguration
     * @return ?
     */
    public static String createHeatmapComp(TrialData trial,
            TrialData trialComp, HeatmapConfig hmConfig) {
        return null;
    }

    /**
     * Berechnet neue Farbe anhand der Tiefe (CodeCharts).
     *
     * @param relDepth     - relative Tiefe
     * @param opacity      - Transparenz
     * @param minColorDiff - minimale Farbe
     * @param maxColorDiff - maximale Farbe
     * @return berechnete Farbe (java.awt.Color Color)
     */
    private static java.awt.Color calcColor(double relDepth, double opacity,
            javafx.scene.paint.Color minColorDiff,
            javafx.scene.paint.Color maxColorDiff) {
        double r = minColorDiff.getRed() * (1.0f - relDepth) + maxColorDiff.getRed() * relDepth;
        double g = minColorDiff.getGreen() * (1.0f - relDepth) + maxColorDiff.getGreen() * relDepth;
        double b = minColorDiff.getBlue() * (1.0f - relDepth) + maxColorDiff.getBlue() * relDepth;
        Logger.debug(String.format("R:%f, G:%fm B:%f", r, g, b));

        javafx.scene.paint.Color color = new javafx.scene.paint.Color(r, g, b, opacity);
        return HeatmapConfig.fxToAwtColor(color);
    }
}
