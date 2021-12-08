package github.weichware10.analyse;

import static org.junit.Assert.assertEquals;

import github.weichware10.util.data.TrialData;
import github.weichware10.util.Enums.ToolType;
import org.junit.Test;

/**
 * Unit Test für Klasse Diagramm.
 */
public class DiagrammTest {
    /**
     * Testet, ob String erstellt und ausgegeben wird.
     */
    @Test
    public void createRelFreqImgAreaShouldWork() {
        Diagramm dia1 = new Diagramm(new TrialData(ToolType.ZOOMMAPS, "1", "3"), new ConfigDiagramm());
        assertEquals(
                "createRelFreqImgAre sollte .../diagramm/RELFRQIMGAREA_ZOOMMAPS_1 zurückgeben",
                ".../diagramm/RELFRQIMGAREA_ZOOMMAPS_1.jpg",
                dia1.createRelFreqImgArea());

        Diagramm dia2 = new Diagramm(new TrialData(ToolType.EYETRACKING, "5", "3"), new ConfigDiagramm());
        assertEquals(
                "createRelFreqImgAre sollte .../diagramm/RELFRQIMGAREA_EYETRACKING_5 zurückgeben",
                ".../diagramm/RELFRQIMGAREA_EYETRACKING_5.jpg",
                dia2.createRelFreqImgArea());

        Diagramm dia3 = new Diagramm(new TrialData(ToolType.CODECHARTS, "132", "3"), new ConfigDiagramm());
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
        Diagramm dia1 = new Diagramm(new TrialData(ToolType.ZOOMMAPS, "1", "3"), new ConfigDiagramm());
        assertEquals("createViewTimeDistr sollte .../diagramm/VIEWTIMEDISTR_ZOOMMAPS_1 zurückgeben",
                ".../diagramm/VIEWTIMEDISTR_ZOOMMAPS_1.jpg",
                dia1.createViewTimeDistr());

        Diagramm dia2 = new Diagramm(new TrialData(ToolType.EYETRACKING, "5", "3"), new ConfigDiagramm());
        assertEquals(
                "createViewTimeDistr sollte .../diagramm/VIEWTIMEDISTR_EYETRACKING_5 zurückgeben",
                ".../diagramm/VIEWTIMEDISTR_EYETRACKING_5.jpg",
                dia2.createViewTimeDistr());

        Diagramm dia3 = new Diagramm(new TrialData(ToolType.CODECHARTS, "132", "3"), new ConfigDiagramm());
        assertEquals(
                "createViewTimeDistr sollte .../diagramm/VIEWTIMEDISTR_CODECHARTS_132 zurückgeben",
                ".../diagramm/VIEWTIMEDISTR_CODECHARTS_132.jpg",
                dia3.createViewTimeDistr());
    }
}
