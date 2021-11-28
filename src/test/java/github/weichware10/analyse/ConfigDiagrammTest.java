package github.weichware10.analyse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit Test für Klasse ConfigDiagramm.
 */
public class ConfigDiagrammTest {
    /**
     * Testet, ob Anzahl Felder gesetzt wird.
     */
    @Test
    public void amountAreasShouldBeSet() {
        ConfigDiagramm confDia1 = new ConfigDiagramm();
        assertEquals(4, confDia1.getAmountAreas());

        ConfigDiagramm confDia2 = new ConfigDiagramm();
        assertTrue(confDia2.setAmountAreas(6));

        ConfigDiagramm confDia3 = new ConfigDiagramm();
        confDia3.setAmountAreas(2);
        assertEquals(2, confDia3.getAmountAreas());

        ConfigDiagramm confDia4 = new ConfigDiagramm();
        assertFalse(confDia4.setAmountAreas(10));

        ConfigDiagramm confDia5 = new ConfigDiagramm();
        assertFalse(confDia5.setAmountAreas(-2));
    }

    /**
     * Testet, ob minimale Zeit gesetzt wird.
     */
    @Test
    public void minTimeShouldBeSet() {
        ConfigDiagramm confDia1 = new ConfigDiagramm();
        assertEquals(0.5f, confDia1.getMinTime(), 0.00001f);

        ConfigDiagramm confDia2 = new ConfigDiagramm();
        assertTrue(confDia2.setNewTime(1.0f, 2.5f));

        ConfigDiagramm confDia3 = new ConfigDiagramm();
        confDia3.setNewTime(2.0f, 2.5f);
        assertEquals(2.0f, confDia3.getMinTime(), 0.00001f);

        ConfigDiagramm confDia4 = new ConfigDiagramm();
        assertFalse(confDia4.setNewTime(3.0f, 2.5f));

        ConfigDiagramm confDia5 = new ConfigDiagramm();
        assertFalse(confDia5.setNewTime(-1.0f, 2.5f));
    }

    /**
     * Testet, ob maximale Zeit gesetzt wird.
     */
    @Test
    public void maxTimeShouldBeSet() {
        ConfigDiagramm confDia1 = new ConfigDiagramm();
        assertEquals(2.5f, confDia1.getMaxTime(), 0.00001f);

        ConfigDiagramm confDia2 = new ConfigDiagramm();
        assertTrue(confDia2.setNewTime(0.5f, 3.0f));

        ConfigDiagramm confDia3 = new ConfigDiagramm();
        confDia3.setNewTime(0.5f, 1.0f);
        assertEquals(1.0f, confDia3.getMaxTime(), 0.00001f);

        ConfigDiagramm confDia4 = new ConfigDiagramm();
        assertFalse(confDia4.setNewTime(0.5f, 0.2f));

        ConfigDiagramm confDia5 = new ConfigDiagramm();
        assertFalse(confDia5.setNewTime(0.5f, 12.5f));
    }

    /**
     * Testet, ob Zwischenschritte gesetzt werden.
     */
    @Test
    public void stepsBetweenShouldBeSet() {
        ConfigDiagramm confDia1 = new ConfigDiagramm();
        assertEquals(4, confDia1.getStepsBetween());

        ConfigDiagramm confDia2 = new ConfigDiagramm();
        assertTrue(confDia2.setStepsBetween(8));

        ConfigDiagramm confDia3 = new ConfigDiagramm();
        confDia3.setStepsBetween(1);
        assertEquals(1, confDia3.getStepsBetween());

        ConfigDiagramm confDia4 = new ConfigDiagramm();
        assertFalse(confDia4.setStepsBetween(12));

        ConfigDiagramm confDia5 = new ConfigDiagramm();
        assertFalse(confDia5.setStepsBetween(-2));

        ConfigDiagramm confDia6 = new ConfigDiagramm();
        confDia6.setNewTime(3.0f, 3.0f);
        confDia6.setStepsBetween(5);
        assertEquals(0, confDia6.getStepsBetween());

        ConfigDiagramm confDia7 = new ConfigDiagramm();
        confDia7.setStepsBetween(10);
        confDia7.setNewTime(3.0f, 3.0f);
        assertEquals(0, confDia7.getStepsBetween());
    }
}
