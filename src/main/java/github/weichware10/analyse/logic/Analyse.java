package github.weichware10.analyse.logic;

import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.data.DataPoint;
import java.io.IOException;
import java.util.List;
import javafx.geometry.Rectangle2D;

/**
 * beinhaltet Methoden die zur Analyse benötigt werden.
 */
public class Analyse {

    /**
     * Speichert das Versuchs-Bild.
     *
     * @param imageUrl - URL des Versuchs-Bilds
     * @return Pfad zum gespeicherten Bild
     */
    public static String saveImage(String imageUrl) {
        String imageLocation = null;
        try {
            imageLocation = Files.saveImage(imageUrl);
        } catch (IllegalArgumentException | IOException e) {
            Logger.error("Failed to save img", e, true);
        }
        return imageLocation;
    }

    /**
     * Berechnet maximale Tiefe eines ZoomMaps-Versuchs.
     *
     * @param data - Datenpunkte des Versuchs
     * @param width - Breite des verwendeten Bilds
     * @param height - Höhe des verwendeten Bilds
     * @return maximale Tiefe des Versuchs
     */
    protected static double findMaxDepth(List<DataPoint> data, double width, double height) {
        double maxDepth = Float.MAX_VALUE;
        for (DataPoint dataPoint : data) {
            double currentWidth = dataPoint.viewport.getWidth();
            double currentHeight = dataPoint.viewport.getHeight();
            double depth = (currentWidth * currentHeight) / (width * height);
            if (depth < maxDepth) {
                maxDepth = depth;
            }
        }
        return java.lang.Math.log10(1 / maxDepth);
    }

    /**
     * Berechnet die relative Tiefe eines Datenpunktes (ZoomMaps).
     *
     * @param viewport - Viewport des Datenpunktes
     * @param imageWidth - Breite Versuchs-Bild
     * @param imageHeight - Höhe Versuchs-Bild
     * @param maxDepth - maximale Tiefe des Versuchs
     * @return relative Tiefe des Datenpunkts
     */
    protected static double calcRelDepthZm(Rectangle2D viewport, double imageWidth,
            double imageHeight, double maxDepth) {
        // Tiefe berechnen
        double currentWidth = viewport.getWidth();
        double currentHeight = viewport.getHeight();
        double tempRelDepth = 1 / ((currentWidth * currentHeight) / (imageWidth * imageHeight));

        // Logarithmus der Tiefe für linearen Verlauf
        tempRelDepth = java.lang.Math.log10(tempRelDepth);

        // relative Tiefe berechnen
        double relDepth = tempRelDepth / maxDepth;
        // Logger.debug(relDepth + " " + tempRelDepth);

        return relDepth;
    }

    /**
     * Berechnet die relative Tiefe eines Datenpunktes (CodeCharts).
     *
     * @param depth - Tiefe des Datenpunktes
     * @param maxDepth - Maximale Tiefe des Versuchs
     * @return relative Tiefe des Datenpunkts
     */
    protected static double calcRelDepthCc(double depth, double maxDepth) {
        return depth / maxDepth;
    }
}
