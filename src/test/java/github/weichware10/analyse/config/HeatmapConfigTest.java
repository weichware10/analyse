package github.weichware10.analyse.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javafx.scene.paint.Color;
import org.junit.Test;

/**
 * Unit Test für Klasse ConfigHeatmap.
 */
public class HeatmapConfigTest {
    /**
     * Testet, ob minimale Farbe gesetzt wird.
     */
    @Test
    public void minColorShouldBeSet() {
        HeatmapConfig confHm1 = new HeatmapConfig();
        assertEquals("getMinColorDiff sollte 0 zurückgeben", 0,
                confHm1.getMinColorDiff().getRed(), 0.0001f);
        assertEquals("getMinColorDiff sollte 0 zurückgeben", 0,
                confHm1.getMinColorDiff().getGreen(), 0.0001f);
        assertEquals("getMinColorDiff sollte 1 zurückgeben", 1,
                confHm1.getMinColorDiff().getBlue(), 0.0001f);

        HeatmapConfig confHm2 = new HeatmapConfig();
        confHm2.setMinColorDiff(new Color(0.25f, 0.1f, 1.0f, 1.0f));
        assertEquals("getMinColor[0] sollte 0.25 zurückgeben",
                0.25f, confHm2.getMinColorDiff().getRed(), 0.0001f);
        assertEquals("getMinColor[1] sollte 0.1 zurückgeben",
                0.1f, confHm2.getMinColorDiff().getGreen(), 0.0001f);
        assertEquals("getMinColor[2] sollte 1 zurückgeben",
                1.0f, confHm2.getMinColorDiff().getBlue(), 0.0001f);
    }

    @Test(expected = IllegalArgumentException.class) // erwartet, dass Fehler auftritt
    public void shouldThrowAtWrongMinColorDiff() throws IllegalArgumentException {
        HeatmapConfig confHm1 = new HeatmapConfig();
        confHm1.setMinColorDiff(new Color(2.0f, -1.0f, 0.0f, 1.0f));
    }

    /**
     * Testet, ob maximale Farbe gesetzt wird.
     */
    @Test
    public void maxColorShouldBeSet() {
        HeatmapConfig confHm1 = new HeatmapConfig();
        assertEquals("getMaxColor[0] sollte 1 zurückgeben", 1,
                confHm1.getMaxColorDiff().getRed(), 0.0001f);
        assertEquals("getMaxColor[1] sollte 0 zurückgeben", 0,
                confHm1.getMaxColorDiff().getGreen(), 0.0001f);
        assertEquals("getMaxColor[2] sollte 0 zurückgeben", 0,
                confHm1.getMaxColorDiff().getBlue(), 0.0001f);

        HeatmapConfig confHm2 = new HeatmapConfig();
        confHm2.setMaxColorDiff(new Color(0.1f, 1.0f, 0.45f, 1.0f));
        assertEquals("getMaxColor[0] sollte 0.1 zurückgeben",
                0.1f, confHm2.getMaxColorDiff().getRed(), 0.0001f);
        assertEquals("getMaxColor[1] sollte 1.0 zurückgeben",
                1.0f, confHm2.getMaxColorDiff().getGreen(), 0.0001f);
        assertEquals("getMaxColor[2] sollte 0.45 zurückgeben",
                0.45f, confHm2.getMaxColorDiff().getBlue(), 0.0001f);
    }

    @Test(expected = IllegalArgumentException.class) // erwartet, dass Fehler auftritt
    public void shouldThrowAtWrongMaxColorDiff() throws IllegalArgumentException {
        HeatmapConfig confHm1 = new HeatmapConfig();
        confHm1.setMaxColorDiff(new Color(2.0f, -1.0f, 0.0f, 1.0f));
    }

    /**
     * Testet, ob Raster aktiviert oder deaktiviert wird.
     */
    @Test
    public void gridShouldBeSet() {
        HeatmapConfig confHm1 = new HeatmapConfig();
        assertTrue("isGrid sollte true zurückgeben", confHm1.isGrid());

        HeatmapConfig confHm2 = new HeatmapConfig();
        confHm2.setGrid(false);
        assertFalse("isGrid sollte false zurückgeben", confHm2.isGrid());

        HeatmapConfig confHm3 = new HeatmapConfig();
        confHm3.setGrid(true);
        assertTrue("isGrid sollte true zurückgeben", confHm3.isGrid());
    }

    /**
     * Testet, ob Bild aktiviert oder deaktiviert wird.
     */
    @Test
    public void imageShouldBeSet() {
        HeatmapConfig confHm1 = new HeatmapConfig();
        assertTrue("isImage sollte true zurückgeben", confHm1.isImage());

        HeatmapConfig confHm2 = new HeatmapConfig();
        confHm2.setImage(false);
        assertFalse("isImage sollte false zurückgeben", confHm2.isImage());

        HeatmapConfig confHm3 = new HeatmapConfig();
        confHm3.setImage(true);
        assertTrue("isImage sollte true zurückgeben", confHm3.isImage());
    }
}
