package github.weichware10.analyse.logic;

import static org.junit.Assert.assertEquals;

import github.weichware10.analyse.config.DiagramConfig;
import github.weichware10.util.ToolType;
import github.weichware10.util.data.TrialData;
import org.junit.Test;

/**
 * Unit Test für Klasse Diagramm.
 */
public class DiagramTest {
    /**
     * Testet, ob String erstellt und ausgegeben wird.
     */
    @Test
    public void createRelFreqImgAreaShouldWork() {
        Diagram dia1 = new Diagram(
                new TrialData(ToolType.ZOOMMAPS, "1", "3"),
                new DiagramConfig());
        assertEquals(
                "createRelFreqImgAre sollte .../diagramm/RELFRQIMGAREA_ZOOMMAPS_1 zurückgeben",
                ".../diagramm/RELFRQIMGAREA_ZOOMMAPS_1.jpg",
                dia1.createRelFreqImgArea());

        Diagram dia2 = new Diagram(
                new TrialData(ToolType.EYETRACKING, "5", "3"),
                new DiagramConfig());
        assertEquals(
                "createRelFreqImgAre sollte .../diagramm/RELFRQIMGAREA_EYETRACKING_5 zurückgeben",
                ".../diagramm/RELFRQIMGAREA_EYETRACKING_5.jpg",
                dia2.createRelFreqImgArea());

        Diagram dia3 = new Diagram(
                new TrialData(ToolType.CODECHARTS, "132", "3"),
                new DiagramConfig());
        assertEquals(
                "createRelFreqImgAre sollte .../diagramm/RELFRQIMGAREA_CODECHARTS_132 zurückgeben",
                ".../diagramm/RELFRQIMGAREA_CODECHARTS_132.jpg",
                dia3.createRelFreqImgArea());
    }

    /**
     * Testet, ob String erstellt und ausgegeben wird.
     */
    @Test
    public void createViewTimeDistrSouldWork() {
        Diagram dia1 = new Diagram(
                new TrialData(ToolType.ZOOMMAPS, "1", "3"),
                new DiagramConfig());
        assertEquals("createViewTimeDistr sollte .../diagramm/VIEWTIMEDISTR_ZOOMMAPS_1 zurückgeben",
                ".../diagramm/VIEWTIMEDISTR_ZOOMMAPS_1.jpg",
                dia1.createViewTimeDistr());

        Diagram dia2 = new Diagram(
                new TrialData(ToolType.EYETRACKING, "5", "3"),
                new DiagramConfig());
        assertEquals(
                "createViewTimeDistr sollte .../diagramm/VIEWTIMEDISTR_EYETRACKING_5 zurückgeben",
                ".../diagramm/VIEWTIMEDISTR_EYETRACKING_5.jpg",
                dia2.createViewTimeDistr());

        Diagram dia3 = new Diagram(
                new TrialData(ToolType.CODECHARTS, "132", "3"),
                new DiagramConfig());
        assertEquals(
                "createViewTimeDistr sollte .../diagramm/VIEWTIMEDISTR_CODECHARTS_132 zurückgeben",
                ".../diagramm/VIEWTIMEDISTR_CODECHARTS_132.jpg",
                dia3.createViewTimeDistr());
    }
}
