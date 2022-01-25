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
    }

    @Test
    public void barChartShouldBeSet() {
        DiagramConfig confDia1 = new DiagramConfig();
        assertTrue("isBarChart sollte true sein", confDia1.isBarChart());

        DiagramConfig confDia2 = new DiagramConfig();
        confDia2.setBarChart(false);
        assertFalse("isBarChart sollte false sein", confDia2.isBarChart());
    }

}
