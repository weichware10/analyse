package github.weichware10.analyse.logic;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.config.HeatmapConfig;
import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.data.DataPoint;
import github.weichware10.util.data.TrialData;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

/**
 * verantwortlich für die Erstellung der Heatmap.
 */
public class Heatmap {

    private static DataPointComparator comparator = new DataPointComparator();
    private static final float ALPHA = .75f;

    /**
     * Erstellt Heatmap.
     *
     * @param trial - Versuch
     * @param hmConfig - Konfiguration
     * @return Pfad des Heatmap-Bilds
     */
    public static String createHeatmap(TrialData trial, HeatmapConfig hmConfig) {
        Configuration config = Main.dataBaseClient.configurations.get(trial.configId);

        // Bild laden für Bildbreite und -höhe
        String sourceImgLocation = Analyse.saveImage(config.getImageUrl());
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(sourceImgLocation));
        } catch (Exception e) {
            Logger.error("Failed to save the image", e, true);
            return null;
        }
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Heatmap Bild erstellen
        BufferedImage heatmap = null;

        heatmap = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D heatmapGraphics = heatmap.createGraphics();
        heatmapGraphics.setBackground(new java.awt.Color(1, 1, 1, 0));
        heatmapGraphics.clearRect(0, 0, width, height);

        // so sortieren, dass immer weiter reingezoomt wird
        List<DataPoint> sortedDataPoints = trial.getData().stream()
                .sorted(comparator).collect(Collectors.toList());

        createHeatmapFromData(heatmapGraphics, hmConfig, sortedDataPoints, width, height);
        heatmapGraphics.dispose();

        // Generierte Heatmap-Bild speichern
        String heatmapLocation = null;
        try {
            heatmapLocation = Files.saveGeneratedImage(heatmap, "HEATMAP" + trial.trialId + ".png");
        } catch (IllegalArgumentException | IOException e) {
            Logger.error("Failed to save the image", e, true);
        }

        if (image == null || !hmConfig.isImage()) {
            return heatmapLocation;
        }

        Graphics2D imageGraphics = image.createGraphics();
        AlphaComposite acomp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, ALPHA);
        imageGraphics.setComposite(acomp);
        imageGraphics.drawImage(heatmap, 0, 0, null);
        imageGraphics.dispose();

        String imgLocation = null;
        try {
            imgLocation = Files.saveGeneratedImage(image, "IMGHEATMAP" + trial.trialId + ".png");
        } catch (IllegalArgumentException | IOException e) {
            Logger.error("Failed to save the image", e, true);
        }

        return imgLocation;
    }

    private static void createHeatmapFromData(Graphics2D graphic, HeatmapConfig hmConfig,
            List<DataPoint> sortedDataPoints, double imageWidth, double imageHeight) {

        if (sortedDataPoints.size() == 0) {
            return;
        }

        DataPoint minDataPoint = sortedDataPoints.get(0);
        DataPoint maxDataPoint = sortedDataPoints.get(sortedDataPoints.size() - 1);

        double minDepth = Analyse.calculateDepth(minDataPoint, imageWidth, imageHeight, null, null);
        double maxDepth = Analyse.calculateDepth(maxDataPoint, imageWidth, imageHeight, null, null);


        for (DataPoint dataPoint : sortedDataPoints) {
            double relDepth = Analyse.calculateDepth(
                    dataPoint, imageWidth, imageHeight, minDepth, maxDepth);
            graphic.setColor(HeatmapConfig.fxToAwtColor(hmConfig.getMinColorDiff()
                    .interpolate(hmConfig.getMaxColorDiff(), relDepth)));
            graphic.fillRect(
                    (int) dataPoint.viewport.getMinX(),
                    (int) dataPoint.viewport.getMinY(),
                    (int) dataPoint.viewport.getWidth(),
                    (int) dataPoint.viewport.getHeight());
        }
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
