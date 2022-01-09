package github.weichware10.analyse.config;

import javafx.scene.paint.Color;

/**
 * Speicherung der Konfiguration der Heatmap-Analysemethoden.
 */
public class HeatmapConfig {
    private Color minColorDiff;
    private Color maxColorDiff;
    private boolean grid;
    private boolean image;

    /**
     * Speicherung der Konfiguration der Heatmap-Analysemethoden.
     */
    public HeatmapConfig() {
        this.minColorDiff = new Color(0.0f, 0.0f, 1.0f, 1.0f);
        this.maxColorDiff = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        this.grid = true;
        this.image = true;
    }


    public Color getMinColorDiff() {
        return minColorDiff;
    }


    public void setMinColorDiff(Color minColorDiff) {
        this.minColorDiff = minColorDiff;
    }


    public Color getMaxColorDiff() {
        return maxColorDiff;
    }


    public void setMaxColorDiff(Color maxColorDiff) {
        this.maxColorDiff = maxColorDiff;
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
}
