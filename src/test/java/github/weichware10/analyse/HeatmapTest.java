package github.weichware10.analyse;

import static org.junit.Assert.assertEquals;

import github.weichware10.util.Enums.ToolType;
import github.weichware10.util.data.TrialData;
import org.junit.Test;

/**
 * Unit Test für Klasse Heatmap.
 */
public class HeatmapTest {
    /**
     * Teset, ob String erstellt und ausgegeben wird.
     */
    @Test
    public void createHeatmapShouldWork() {
        Heatmap hm1 = new Heatmap(
                new TrialData(ToolType.ZOOMMAPS, "1", "3"),
                new ConfigHeatmap());
        assertEquals("createHeatMap sollte .../heatmap/HEATMAP_ZOOMMAPS_1.jpg ausgeben",
                ".../heatmap/HEATMAP_ZOOMMAPS_1.jpg", hm1.createHeatmap());

        Heatmap hm2 = new Heatmap(
                new TrialData(ToolType.EYETRACKING, "7", "3"),
                new ConfigHeatmap());
        assertEquals("createHeatMap sollte .../heatmap/HEATMAP_EYETRACKING_7.jpg ausgeben",
                ".../heatmap/HEATMAP_EYETRACKING_7.jpg", hm2.createHeatmap());

        Heatmap hm3 = new Heatmap(
                new TrialData(ToolType.CODECHARTS, "3", "3"),
                new ConfigHeatmap());
        assertEquals("createHeatMap sollte .../heatmap/HEATMAP_CODECHARTS_3.jpg ausgeben",
                ".../heatmap/HEATMAP_CODECHARTS_3.jpg", hm3.createHeatmap());
    }

    /**
     * Testet, ob String erstellt und ausgegeben wird.
     */
    @Test
    public void compHeatmapsShouldWork() {
        Heatmap hm1 = new Heatmap(
                new TrialData(ToolType.CODECHARTS, "4", "3"),
                new ConfigHeatmap());
        Heatmap hm2 = new Heatmap(
                new TrialData(ToolType.EYETRACKING, "2", "3"),
                new ConfigHeatmap());
        assertEquals(
                "compHeatmaps sollte .../heatmap/COMPHEATMAP_CODECHARTS_1_EYETRACKING_2.jpg ausgeb",
                ".../heatmap/COMPHEATMAP_CODECHARTS_4_EYETRACKING_2.jpg",
                Heatmap.compHeatmaps(hm1, hm2));

        Heatmap hm3 = new Heatmap(
                new TrialData(ToolType.ZOOMMAPS, "1", "3"),
                new ConfigHeatmap());
        Heatmap hm4 = new Heatmap(
                new TrialData(ToolType.CODECHARTS, "6", "3"),
                new ConfigHeatmap());
        assertEquals(
                "compHeatmaps sollte .../heatmap/COMPHEATMAP_ZOOMMAPS_1_CODECHARTS_6.jpg ausgeben",
                ".../heatmap/COMPHEATMAP_ZOOMMAPS_1_CODECHARTS_6.jpg",
                Heatmap.compHeatmaps(hm3, hm4));

        Heatmap hm5 = new Heatmap(
                new TrialData(ToolType.EYETRACKING, "8", "3"),
                new ConfigHeatmap());
        Heatmap hm6 = new Heatmap(
                new TrialData(ToolType.ZOOMMAPS, "12", "3"),
                new ConfigHeatmap());
        assertEquals(
                "sollte .../heatmap/COMPHEATMAP_EYETRACKING_8_ZOOMMAPS_12.jpg ausgeben",
                ".../heatmap/COMPHEATMAP_EYETRACKING_8_ZOOMMAPS_12.jpg",
                Heatmap.compHeatmaps(hm5, hm6));
    }
}
