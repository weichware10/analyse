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

        // Bild laden für Bildbreite und -höhe
        String imageUrl = null;
        try {
            imageUrl = Files.saveImage(config.getImageUrl());
        } catch (IllegalArgumentException | IOException e) {
            Logger.error("Failed to save img", e, true);
        }
        Image image = new Image(imageUrl);
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Heatmap Bild erstellen
        BufferedImage heatmap = null;
        double opacity;

        // Wenn Versuchs-Bild angezeigt werden soll, dieses als Hintergrund setzen sonst
        // leerer Hintergrund
        if (hmConfig.isImage()) {
            try {
                heatmap = ImageIO.read(new File(imageUrl));
            } catch (IOException e) {
                Logger.error("Failed to load image", e, true);
            }
            opacity = 0.5f;
        } else {
            heatmap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            opacity = 1.0f;
        }

        Graphics2D graphic = heatmap.createGraphics();

        if (trial.toolType == ToolType.ZOOMMAPS) {
            // Tiefe 0 setzten
            graphic.setColor(calcColorCc(0.0f, opacity,
                    hmConfig.getMinColorDiff(), hmConfig.getMaxColorDiff()));
            graphic.fillRect(0, 0, width, height);

            // Maximale Tiefe ermitteln
            double maxDepth = Analyse.findMaxDepth(
                    trial.getData(), (double) width, (double) height);
            double oldRelDepth = 0.0f;

            // Rechtecke der Zooms setzen
            for (DataPoint dataPoint : trial.getData()) {
                // Relative Tiefe berechnen
                double stepWidth = dataPoint.viewport.getWidth();
                double stepHeight = dataPoint.viewport.getHeight();
                double relDepth = Analyse.calcRelDepthZm(
                            stepWidth, stepHeight, width, height, maxDepth);

                // Prüfen ob herausgezoomt wurde
                if (oldRelDepth > relDepth) {
                    oldRelDepth = relDepth;
                    continue;
                }
                oldRelDepth = relDepth;

                // Rechteck färben und setzten
                graphic.setColor(calcColorZm(relDepth, 0.1f, hmConfig.getMaxColorDiff()));
                int minX = (dataPoint.viewport.getMinX() < 0.0f
                        ? 0 : (int) dataPoint.viewport.getMinX());
                int minY = (dataPoint.viewport.getMinY() < 0.0f
                        ? 0 : (int) dataPoint.viewport.getMinY());
                graphic.fillRect(minX, minY, (int) stepWidth, (int) stepHeight);
            }
        } else if (trial.toolType == ToolType.CODECHARTS) {
            // Tiefe 0 setzten
            graphic.setColor(calcColorCc(0.0f, opacity,
                    hmConfig.getMinColorDiff(), hmConfig.getMaxColorDiff()));
            graphic.fillRect(0, 0, width, height);

            // Rechtecke der Durchläufe setzen
            for (DataPoint dataPoint : trial.getData()) {
                // Relative Tiefe berechnen
                double relDepth = Analyse.calcRelDepthCc((double) dataPoint.depth,
                        (double) config.getCodeChartsConfiguration().getMaxDepth());

                // Rechteck färben und setzten
                graphic.setColor(calcColorCc(relDepth, opacity,
                        hmConfig.getMinColorDiff(), hmConfig.getMaxColorDiff()));
                graphic.fillRect((int) dataPoint.viewport.getMinX(),
                        (int) dataPoint.viewport.getMinY(),
                        (int) dataPoint.viewport.getWidth(),
                        (int) dataPoint.viewport.getHeight());
            }
        }
        graphic.dispose();

        // Generierte Heatmap-Bild speichern
        String imgLocation = null;
        try {
            imgLocation = Files.saveGeneratedImage(heatmap, "test.png");
        } catch (IllegalArgumentException | IOException e) {
            Logger.error("Failed to save the image", e, true);
        }

        return imgLocation;
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
    private static java.awt.Color calcColorCc(double relDepth, double opacity,
            javafx.scene.paint.Color minColorDiff,
            javafx.scene.paint.Color maxColorDiff) {
        double r = minColorDiff.getRed() * (1.0f - relDepth) + maxColorDiff.getRed() * relDepth;
        double g = minColorDiff.getGreen() * (1.0f - relDepth) + maxColorDiff.getGreen() * relDepth;
        double b = minColorDiff.getBlue() * (1.0f - relDepth) + maxColorDiff.getBlue() * relDepth;
        // Logger.debug(String.format("R:%f, G:%fm B:%f", r, g, b));

        javafx.scene.paint.Color color = new javafx.scene.paint.Color(r, g, b, opacity);
        return HeatmapConfig.fxToAwtColor(color);
    }

    /**
     * Berechnet neue Farbe anhand der Tiefe (ZoomMaps).
     *
     * @param relDepth     - relative Tiefe
     * @param opacity      - Transparenz
     * @param maxColorDiff - maximale Farbe
     * @return berechnete Farbe (java.awt.Color Color)
     */
    private static java.awt.Color calcColorZm(double relDepth, double opacity,
            javafx.scene.paint.Color maxColorDiff) {
        double r = 1.0f * (1.0f - relDepth) + maxColorDiff.getRed() * relDepth;
        double g = 1.0f * (1.0f - relDepth) + maxColorDiff.getGreen() * relDepth;
        double b = 1.0f * (1.0f - relDepth) + maxColorDiff.getBlue() * relDepth;
        double a = opacity * relDepth;
        // Logger.debug(String.format("R:%f, G:%fm B:%f", r, g, b));

        javafx.scene.paint.Color color = new javafx.scene.paint.Color(r, g, b, a);
        return HeatmapConfig.fxToAwtColor(color);
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
}
