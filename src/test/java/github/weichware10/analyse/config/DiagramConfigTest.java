package github.weichware10.analyse.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit Test für Klasse ConfigDiagramm.
 */
public class DiagramConfigTest {

    /**
     * Testet, ob minimale Zeit gesetzt wird.
     */
    @Test
    public void minTimeShouldBeSet() {
        DiagramConfig confDia1 = new DiagramConfig();
        assertEquals("getMinTime sollte 0.0 zurückgeben", 0.0f, confDia1.getMinTime(), 0.00001f);

        DiagramConfig confDia2 = new DiagramConfig();
        assertTrue("setNewTime sollte True zurückgeben", confDia2.setNewTime(1.0f, 2.5f));

        DiagramConfig confDia3 = new DiagramConfig();
        confDia3.setNewTime(2.0f, 2.5f);
        assertEquals("getMinTime sollte 2.0 zurückgeben", 2.0f, confDia3.getMinTime(), 0.00001f);

        DiagramConfig confDia4 = new DiagramConfig();
        assertFalse("setNewTime sollte False zurückgeben", confDia4.setNewTime(3.0f, 2.5f));

        DiagramConfig confDia5 = new DiagramConfig();
        assertFalse("setNewTime sollte False zurückgeben", confDia5.setNewTime(-1.0f, 2.5f));
    }

    /**
     * Testet, ob maximale Zeit gesetzt wird.
     */
    @Test
    public void maxTimeShouldBeSet() {
        DiagramConfig confDia1 = new DiagramConfig();
        assertEquals("getMaxTime sollte 10.0 zurückgeben", 10.0f, confDia1.getMaxTime(), 0.00001f);

        DiagramConfig confDia2 = new DiagramConfig();
        assertTrue("setNewTime sollte True zurückgeben", confDia2.setNewTime(0.5f, 3.0f));

        DiagramConfig confDia3 = new DiagramConfig();
        confDia3.setNewTime(0.5f, 1.0f);
        assertEquals("getMaxTime sollte 1.0 zurückgeben", 1.0f, confDia3.getMaxTime(), 0.00001f);

        DiagramConfig confDia4 = new DiagramConfig();
        assertFalse("setNewTime sollte False zurückgeben", confDia4.setNewTime(0.5f, 0.2f));

        DiagramConfig confDia5 = new DiagramConfig();
        assertFalse("setNewTime sollte False zurückgeben", confDia5.setNewTime(0.5f, 12.5f));
    }

    /**
     * Testet, ob Zwischenschritte gesetzt werden.
     */
    @Test
    public void stepsBetweenShouldBeSet() {
        DiagramConfig confDia1 = new DiagramConfig();
        assertEquals("getStepsBetween sollte 4 zurückgeben", 4, confDia1.getStepsBetween());

        DiagramConfig confDia2 = new DiagramConfig();
        assertTrue("setStepsBetween sollte True zurückgeben", confDia2.setStepsBetween(8));

        DiagramConfig confDia3 = new DiagramConfig();
        confDia3.setStepsBetween(1);
        assertEquals("getStepsBetween sollte 1 zurückgeben", 1, confDia3.getStepsBetween());

        DiagramConfig confDia4 = new DiagramConfig();
        assertFalse("setStepsBetween sollte False zurückgeben", confDia4.setStepsBetween(12));

        DiagramConfig confDia5 = new DiagramConfig();
        assertFalse("setStepsBetween sollte False zurückgeben", confDia5.setStepsBetween(-2));

        DiagramConfig confDia6 = new DiagramConfig();
        confDia6.setNewTime(3.0f, 3.0f);
        confDia6.setStepsBetween(5);
        assertEquals("getStepsBetween sollte 0 zurückgeben", 0, confDia6.getStepsBetween());

        DiagramConfig confDia7 = new DiagramConfig();
        confDia7.setStepsBetween(10);
        confDia7.setNewTime(3.0f, 3.0f);
        assertEquals("getStepsBetween sollte 0 zurückgeben", 0, confDia7.getStepsBetween());
    }

}
