package github.weichware10.analyse.logic;

import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.data.DataPoint;
import java.io.IOException;

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
     * Berechnet die Tiefe eines Viewports. Falls min-/maxDepth nicht gegeben sind,
     * wird die unkorrigierte Tiefe zurückgegeben (Zur Berechnug von min / max Depth)
     *
     * @param dataPoint - der betroffene DataPoint
     * @param imageWidth - die Größe des Bildes
     * @param imageHeight - die Höhe des Bildes
     * @param minDepth - die minimale Tiefe, oder {@code null}, falls diese berechnet werden soll
     * @param maxDepth - die maximale Tiefe, oder {@code null}, falls diese berechnet werden soll
     * @return die berechnete Tiefe
     */
    protected static double calculateDepth(DataPoint dataPoint,
            double imageWidth, double imageHeight, Double minDepth, Double maxDepth) {
        double localDepth = 1 - ((dataPoint.viewport.getWidth() * dataPoint.viewport.getHeight())
                / (imageWidth * imageHeight));

        if (minDepth == null || maxDepth == null) {
            return localDepth;
        } else {
            return (Math.pow(100, (localDepth - minDepth) / (maxDepth - minDepth)) - 1) / 99;
        }
    }
}
