package github.weichware10.analyse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit Test für Klasse ConfigHeatmap.
 */
public class ConfigHeatmapTest {
    /**
     * Testet, ob minimale Farbe gesetzt wird.
     */
    @Test
    public void minColorShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        // assertEquals(new float[] { 1, 0, 0 }, confHm1.getMinColor());
        assertEquals(0, confHm1.getMinColor()[0], 0.0001f);
        assertEquals(0, confHm1.getMinColor()[1], 0.0001f);
        assertEquals(1, confHm1.getMinColor()[2], 0.0001f);

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        assertTrue(confHm2.setMinColor(new float[] { 0, 1, 0.5f }));

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setMinColor(new float[] { 0.25f, 0.1f, 1 });
        // assertEquals(new float[] { 0.25f, 0.1f, 1 }, confHm3.getMinColor());
        assertEquals(0.25f, confHm3.getMinColor()[0], 0.0001f);
        assertEquals(0.1f, confHm3.getMinColor()[1], 0.0001f);
        assertEquals(1, confHm3.getMinColor()[2], 0.0001f);

        ConfigHeatmap confHm4 = new ConfigHeatmap();
        assertFalse(confHm4.setMinColor(new float[] { -1, 0, 1 }));

        ConfigHeatmap confHm5 = new ConfigHeatmap();
        assertFalse(confHm5.setMinColor(new float[] { 0.5f, 1.3f, 1 }));

        ConfigHeatmap confHm6 = new ConfigHeatmap();
        assertFalse(confHm6.setMinColor(new float[] { 0.5f, 1.3f, -1 }));
    }

    /**
     * Testet, ob maximale Farbe gesetzt wird.
     */
    @Test
    public void maxColorShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertEquals(1, confHm1.getMaxColor()[0], 0.0001f);
        assertEquals(0, confHm1.getMaxColor()[1], 0.0001f);
        assertEquals(0, confHm1.getMaxColor()[2], 0.0001f);

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        assertTrue(confHm2.setMaxColor(new float[] { 0.6f, 0.15f, 1.0f }));

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setMaxColor(new float[] { 0.1f, 1.0f, 0.45f });
        assertEquals(0.1f, confHm3.getMaxColor()[0], 0.0001f);
        assertEquals(1.0f, confHm3.getMaxColor()[1], 0.0001f);
        assertEquals(0.45f, confHm3.getMaxColor()[2], 0.0001f);

        ConfigHeatmap confHm4 = new ConfigHeatmap();
        assertFalse(confHm4.setMaxColor(new float[] { 0.2f, -0.85f, 0.0f }));

        ConfigHeatmap confHm5 = new ConfigHeatmap();
        assertFalse(confHm5.setMaxColor(new float[] { 0.2f, 0.16f, 3.0f }));

        ConfigHeatmap confHm6 = new ConfigHeatmap();
        assertFalse(confHm6.setMaxColor(new float[] { 1.2f, 0.16f, -3.0f }));
    }

    /**
     * Testet, ob Raster aktiviert oder deaktiviert wird.
     */
    @Test
    public void gridShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertTrue(confHm1.isGrid());

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        confHm2.setGrid(false);
        assertFalse(confHm2.isGrid());

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setGrid(true);
        assertTrue(confHm3.isGrid());
    }

    /**
     * Testet, ob Bild aktiviert oder deaktiviert wird.
     */
    @Test
    public void imageShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertTrue(confHm1.isImage());

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        confHm2.setImage(false);
        assertFalse(confHm2.isImage());

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setImage(true);
        assertTrue(confHm3.isImage());
    }

    /**
     * Testet, ob minimale Farbe für Vergleich gesetzt wird.
     */
    @Test
    public void minDiffShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertEquals(1, confHm1.getMinDiff()[0], 0.0001f);
        assertEquals(0, confHm1.getMinDiff()[1], 0.0001f);
        assertEquals(1, confHm1.getMinDiff()[2], 0.0001f);

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        assertTrue(confHm2.setMinDiff(new float[] { 0.5f, 0.3f, 1.0f }));

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setMinDiff(new float[] { 0.75f, 0.2f, 0.5f });
        assertEquals(0.75f, confHm3.getMinDiff()[0], 0.0001f);
        assertEquals(0.2f, confHm3.getMinDiff()[1], 0.0001f);
        assertEquals(0.5f, confHm3.getMinDiff()[2], 0.0001f);

        ConfigHeatmap confHm4 = new ConfigHeatmap();
        assertFalse(confHm4.setMinDiff(new float[] { -1, 0, 1 }));

        ConfigHeatmap confHm5 = new ConfigHeatmap();
        assertFalse(confHm5.setMinDiff(new float[] { 0.5f, 1.3f, 1 }));

        ConfigHeatmap confHm6 = new ConfigHeatmap();
        assertFalse(confHm6.setMinDiff(new float[] { 0.5f, 1.3f, -1 }));
    }

    /**
     * Testet, ob maximale Farbe für Vergleich gesetzt wird.
     */
    @Test
    public void maxDiffShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertEquals(1, confHm1.getMaxDiff()[0], 0.0001f);
        assertEquals(1, confHm1.getMaxDiff()[1], 0.0001f);
        assertEquals(0, confHm1.getMaxDiff()[2], 0.0001f);

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        assertTrue(confHm2.setMaxDiff(new float[] { 0.0f, 0.65f, 0.2f }));

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setMaxDiff(new float[] { 0.2f, 1.0f, 0.5f });
        assertEquals(0.2f, confHm3.getMaxDiff()[0], 0.0001f);
        assertEquals(1.0f, confHm3.getMaxDiff()[1], 0.0001f);
        assertEquals(0.5f, confHm3.getMaxDiff()[2], 0.0001f);

        ConfigHeatmap confHm4 = new ConfigHeatmap();
        assertFalse(confHm4.setMaxDiff(new float[] { -1, 0, 1 }));

        ConfigHeatmap confHm5 = new ConfigHeatmap();
        assertFalse(confHm5.setMaxDiff(new float[] { 0.5f, 1.3f, 1 }));

        ConfigHeatmap confHm6 = new ConfigHeatmap();
        assertFalse(confHm6.setMaxDiff(new float[] { 0.5f, 1.3f, -1 }));
    }
}
