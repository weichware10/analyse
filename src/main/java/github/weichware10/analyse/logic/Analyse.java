package github.weichware10.analyse.logic;

import github.weichware10.util.data.DataPoint;
import java.util.List;

/**
 * beinhaltet Methoden die zur Analyse benötigt werden.
 */
public class Analyse {

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
     * @param currentWidth - Breite Viewport des Datenpunktes
     * @param currentHeight - Höhe Viewport des Datenpunktes
     * @param imageWidth - Breite Versuchs-Bild
     * @param imageHeight - Höhe Versuchs-Bild
     * @param maxDepth - maximale Tiefe des Versuchs
     * @return relative Tiefe des Datenpunkts
     */
    protected static double calcRelDepthZm(double currentWidth, double currentHeight,
            double imageWidth, double imageHeight, double maxDepth) {
        // Tiefe berechnen
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
