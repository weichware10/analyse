package github.weichware10.analyse;

import static org.junit.Assert.assertEquals;

import github.weichware10.util.Data;
import github.weichware10.util.Enums.ToolType;
import org.junit.Test;

/**
 * Unit Test für Klasse Verlauf.
 */
public class VerlaufTest {
    /**
     * Testet, ob String erstellt und ausgegeben wird.
     */
    @Test
    public void createVerlaufShouldWork() {
        Verlauf verlauf1 = new Verlauf(new Data(ToolType.ZOOMMAPS, 1));
        assertEquals("createVerlauf sollte .../verlauf/VERLAUF_ZOOM_1.jpg ausgeben",
                ".../verlauf/VERLAUF_ZOOM_1.jpg", verlauf1.createVerlauf());

        Verlauf verlauf2 = new Verlauf(new Data(ToolType.EYETRACKING, 6));
        assertEquals("createVerlauf sollte .../verlauf/VERLAUF_EYETRACKING_6.jpg ausgeben",
                ".../verlauf/VERLAUF_EYETRACKING_6.jpg",
                verlauf2.createVerlauf());

        Verlauf verlauf3 = new Verlauf(new Data(ToolType.CODECHARTS, 20));
        assertEquals("createVerlauf sollte .../verlauf/VERLAUF_CODECHARTS_20.jpg ausgeben",
                ".../verlauf/VERLAUF_CODECHARTS_20.jpg",
                verlauf3.createVerlauf());
    }
}
