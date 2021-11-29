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
        assertEquals("getMinColor[0] sollte 0 zurückgeben", 0, confHm1.getMinColor()[0], 0.0001f);
        assertEquals("getMinColor[1] sollte 0 zurückgeben", 0, confHm1.getMinColor()[1], 0.0001f);
        assertEquals("getMinColor[2] sollte 1 zurückgeben", 1, confHm1.getMinColor()[2], 0.0001f);

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        assertTrue("setMinColor sollte True zurückgeben",
            confHm2.setMinColor(new float[] { 0, 1, 0.5f }));

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setMinColor(new float[] { 0.25f, 0.1f, 1 });
        assertEquals("getMinColor[0] sollte 0.25 zurückgeben",
            0.25f, confHm3.getMinColor()[0], 0.0001f);
        assertEquals("getMinColor[1] sollte 0.1 zurückgeben",
            0.1f, confHm3.getMinColor()[1], 0.0001f);
        assertEquals("getMinColor[2] sollte 1 zurückgeben",
            1, confHm3.getMinColor()[2], 0.0001f);

        ConfigHeatmap confHm4 = new ConfigHeatmap();
        assertFalse("setMinColor sollte False zurückgeben",
            confHm4.setMinColor(new float[] { -1, 0, 1 }));

        ConfigHeatmap confHm5 = new ConfigHeatmap();
        assertFalse("setMinColor sollte False zurückgeben",
            confHm5.setMinColor(new float[] { 0.5f, 1.3f, 1 }));

        ConfigHeatmap confHm6 = new ConfigHeatmap();
        assertFalse("setMinColor sollte False zurückgeben",
            confHm6.setMinColor(new float[] { 0.5f, 1.3f, -1 }));
    }

    /**
     * Testet, ob maximale Farbe gesetzt wird.
     */
    @Test
    public void maxColorShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertEquals("getMaxColor[0] sollte 1 zurückgeben", 1, confHm1.getMaxColor()[0], 0.0001f);
        assertEquals("getMaxColor[1] sollte 0 zurückgeben", 0, confHm1.getMaxColor()[1], 0.0001f);
        assertEquals("getMaxColor[2] sollte 0 zurückgeben", 0, confHm1.getMaxColor()[2], 0.0001f);

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        assertTrue("setMaxColor sollte True zurückgeben",
            confHm2.setMaxColor(new float[] { 0.6f, 0.15f, 1.0f }));

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setMaxColor(new float[] { 0.1f, 1.0f, 0.45f });
        assertEquals("getMaxColor[0] sollte 0.1 zurückgeben",
            0.1f, confHm3.getMaxColor()[0], 0.0001f);
        assertEquals("getMaxColor[1] sollte 1.0 zurückgeben",
            1.0f, confHm3.getMaxColor()[1], 0.0001f);
        assertEquals("getMaxColor[2] sollte 0.45 zurückgeben",
            0.45f, confHm3.getMaxColor()[2], 0.0001f);

        ConfigHeatmap confHm4 = new ConfigHeatmap();
        assertFalse("setMaxColor sollte False zurückgeben",
            confHm4.setMaxColor(new float[] { 0.2f, -0.85f, 0.0f }));

        ConfigHeatmap confHm5 = new ConfigHeatmap();
        assertFalse("setMaxColor sollte False zurückgeben",
            confHm5.setMaxColor(new float[] { 0.2f, 0.16f, 3.0f }));

        ConfigHeatmap confHm6 = new ConfigHeatmap();
        assertFalse("setMaxColor sollte False zurückgeben",
            confHm6.setMaxColor(new float[] { 1.2f, 0.16f, -3.0f }));
    }

    /**
     * Testet, ob Raster aktiviert oder deaktiviert wird.
     */
    @Test
    public void gridShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertTrue("isGrid sollte true zurückgeben", confHm1.isGrid());

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        confHm2.setGrid(false);
        assertFalse("isGrid sollte false zurückgeben", confHm2.isGrid());

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setGrid(true);
        assertTrue("isGrid sollte true zurückgeben", confHm3.isGrid());
    }

    /**
     * Testet, ob Bild aktiviert oder deaktiviert wird.
     */
    @Test
    public void imageShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertTrue("isImage sollte true zurückgeben", confHm1.isImage());

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        confHm2.setImage(false);
        assertFalse("isImage sollte false zurückgeben", confHm2.isImage());

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setImage(true);
        assertTrue("isImage sollte true zurückgeben", confHm3.isImage());
    }

    /**
     * Testet, ob minimale Farbe für Vergleich gesetzt wird.
     */
    @Test
    public void minDiffShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertEquals("getMinDiff[0] sollte 1 zurückgeben", 1, confHm1.getMinDiff()[0], 0.0001f);
        assertEquals("getMinDiff[1] sollte 0 zurückgeben", 0, confHm1.getMinDiff()[1], 0.0001f);
        assertEquals("getMinDiff[2] sollte 1 zurückgeben", 1, confHm1.getMinDiff()[2], 0.0001f);

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        assertTrue("setMinDiff sollte True zurückgeben",
            confHm2.setMinDiff(new float[] { 0.5f, 0.3f, 1.0f }));

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setMinDiff(new float[] { 0.75f, 0.2f, 0.5f });
        assertEquals("getMinDiff[0] sollte 0.75 zurückgeben",
            0.75f, confHm3.getMinDiff()[0], 0.0001f);
        assertEquals("getMinDiff[1] sollte 0.2 zurückgeben",
            0.2f, confHm3.getMinDiff()[1], 0.0001f);
        assertEquals("getMinDiff[2] sollte 0.5 zurückgeben",
            0.5f, confHm3.getMinDiff()[2], 0.0001f);

        ConfigHeatmap confHm4 = new ConfigHeatmap();
        assertFalse("setMinDiff sollte False zurückgeben",
            confHm4.setMinDiff(new float[] { -1, 0, 1 }));

        ConfigHeatmap confHm5 = new ConfigHeatmap();
        assertFalse("setMinDiff sollte False zurückgeben",
            confHm5.setMinDiff(new float[] { 0.5f, 1.3f, 1 }));

        ConfigHeatmap confHm6 = new ConfigHeatmap();
        assertFalse("setMinDiff sollte False zurückgeben",
            confHm6.setMinDiff(new float[] { 0.5f, 1.3f, -1 }));
    }

    /**
     * Testet, ob maximale Farbe für Vergleich gesetzt wird.
     */
    @Test
    public void maxDiffShouldBeSet() {
        ConfigHeatmap confHm1 = new ConfigHeatmap();
        assertEquals("getMaxDiff[0] sollte 1 zurückgeben", 1, confHm1.getMaxDiff()[0], 0.0001f);
        assertEquals("getMaxDiff[1] sollte 1 zurückgeben", 1, confHm1.getMaxDiff()[1], 0.0001f);
        assertEquals("getMaxDiff[2] sollte 0 zurückgeben", 0, confHm1.getMaxDiff()[2], 0.0001f);

        ConfigHeatmap confHm2 = new ConfigHeatmap();
        assertTrue("setMaxDiff sollte True zurückgeben",
            confHm2.setMaxDiff(new float[] { 0.0f, 0.65f, 0.2f }));

        ConfigHeatmap confHm3 = new ConfigHeatmap();
        confHm3.setMaxDiff(new float[] { 0.2f, 1.0f, 0.5f });
        assertEquals("getMaxDiff[0] sollte 0.2 zurückgeben",
            0.2f, confHm3.getMaxDiff()[0], 0.0001f);
        assertEquals("getMaxDiff[1] sollte 1 zurückgeben",
            1.0f, confHm3.getMaxDiff()[1], 0.0001f);
        assertEquals("getMaxDiff[2] sollte 0.5 zurückgeben",
            0.5f, confHm3.getMaxDiff()[2], 0.0001f);

        ConfigHeatmap confHm4 = new ConfigHeatmap();
        assertFalse("setMaxDiff sollte False zurückgeben",
            confHm4.setMaxDiff(new float[] { -1, 0, 1 }));

        ConfigHeatmap confHm5 = new ConfigHeatmap();
        assertFalse("setMaxDiff sollte False zurückgeben",
            confHm5.setMaxDiff(new float[] { 0.5f, 1.3f, 1 }));

        ConfigHeatmap confHm6 = new ConfigHeatmap();
        assertFalse("setMaxDiff sollte False zurückgeben",
            confHm6.setMaxDiff(new float[] { 0.5f, 1.3f, -1 }));
    }

}
