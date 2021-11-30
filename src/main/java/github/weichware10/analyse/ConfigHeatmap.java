package github.weichware10.analyse;

/**
 * Speicherung der Konfiguration der Heatmap-Analysemethoden.
 */
public class ConfigHeatmap {
    private float[] minColor;
    private float[] maxColor;
    private boolean grid;
    private boolean image;
    private float[] minDiff;
    private float[] maxDiff;

    /**
     * Speicherung der Konfiguration der Heatmap-Analysemethoden.
     */
    public ConfigHeatmap() {
        this.minColor = new float[] { 0, 0, 1 };
        this.maxColor = new float[] { 1, 0, 0 };
        this.grid = true;
        this.image = true;
        this.minDiff = new float[] { 1, 0, 1 };
        this.maxDiff = new float[] { 1, 1, 0 };
    }

    /**
     * setzt neue minimale Farbe.
     *
     * @param minColor - neue minimale Farbe
     * @return
     *         true, falls neue minimale Farbe erfolgreich gesetzt wird;
     *         false, falls neue minimale Farbe nicht gesetzt wird
     */
    public boolean setMinColor(float[] minColor) {
        if (minColor[0] > 1 || minColor[0] < 0 || minColor[1] > 1
                || minColor[1] < 0 || minColor[2] > 1 || minColor[2] < 0) {
            return false;
        } else {
            this.minColor = minColor;
            return true;
        }
    }

    /**
     * gibt minimale Farbe zurück.
     *
     * @return minimale Farbe
     */
    public float[] getMinColor() {
        return minColor;
    }

    /**
     * setzt neue maximale Farbe.
     *
     * @param maxColor - neue maximale Farbe
     * @return
     *         true, falls neue maximale Farbe erfolgreich gesetzt wird;
     *         false, falls neue maximale Farbe nicht gesetzt wird
     */
    public boolean setMaxColor(float[] maxColor) {
        if (maxColor[0] > 1 || maxColor[0] < 0 || maxColor[1] > 1
                || maxColor[1] < 0 || maxColor[2] > 1 || maxColor[2] < 0) {
            return false;
        } else {
            this.maxColor = maxColor;
            return true;
        }
    }

    /**
     * gibt maximale Farbe zurück.
     *
     * @return maximale Farbe
     */
    public float[] getMaxColor() {
        return maxColor;
    }

    /**
     * aktiviert bzw. deaktiviert die Anzeige des Rasters.
     *
     * @param grid - Anzeige Raster aktivieren bzw. deaktivieren
     */
    public void setGrid(boolean grid) {
        this.grid = grid;
    }

    /**
     * gibt zurück ob das Raster angezeigt wird.
     *
     * @return Wert von grid
     */
    public boolean isGrid() {
        return grid;
    }

    /**
     * aktiviert bzw. deaktiviert die Anzeige des Bildes.
     *
     * @param image - Anzeige Bild aktivieren bzw. deaktivieren
     */
    public void setImage(boolean image) {
        this.image = image;
    }

    /**
     * gibt zurück ob das Bild angezeigt wird.
     *
     * @return Wert von image
     */
    public boolean isImage() {
        return image;
    }

    /**
     * setzt neue minimale Farbe für Vergleich.
     *
     * @param minDiff - neue minimale Farbe für Vergleich
     * @return
     *         true, falls neue minimale Farbe für Vergleich erfolgreich gesetzt
     *         wird;
     *         false, falls neue minimale Farbe für Vergleich nicht gesetzt wird
     */
    public boolean setMinDiff(float[] minDiff) {
        if (minDiff[0] > 1 || minDiff[0] < 0 || minDiff[1] > 1
                || minDiff[1] < 0 || minDiff[2] > 1 || minDiff[2] < 0) {
            return false;
        } else {
            this.minDiff = minDiff;
            return true;
        }
    }

    /**
     * gibt minimale Farbe für Vergleich zurück.
     *
     * @return minimale Farbe für Vergleich
     */
    public float[] getMinDiff() {
        return minDiff;
    }

    /**
     * setzt neue maximale Farbe für Vergleich.
     *
     * @param maxDiff - neue maximale Farbe für Vergleich
     * @return
     *         true, falls neue maximale Farbe für Vergleich erfolgreich gesetzt
     *         wird;
     *         false, falls neue maximale Farbe für Vergleich nicht gesetzt wird
     */
    public boolean setMaxDiff(float[] maxDiff) {
        if (maxDiff[0] > 1 || maxDiff[0] < 0 || maxDiff[1] > 1
                || maxDiff[1] < 0 || maxDiff[2] > 1 || maxDiff[2] < 0) {
            return false;
        } else {
            this.maxDiff = maxDiff;
            return true;
        }
    }

    /**
     * gibt maximale Farbe für Vergleich zurück.
     *
     * @return maximale Farbe für Vergleich
     */
    public float[] getMaxDiff() {
        return maxDiff;
    }
}
