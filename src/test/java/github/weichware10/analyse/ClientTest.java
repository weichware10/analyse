package github.weichware10.analyse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import github.weichware10.util.Enums.ToolType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * Unit Test für Klasse Client.
 */
public class ClientTest {

    /**
     * Testet, ob Analyse-Typen gesetzt werden.
     */
    @Test
    public void analyseTypesShouldBeSet() {
        // * Nichtleere Liste wird übergeben
        Client client1 = new Client();
        List<AnalyseType> list1 = new ArrayList<AnalyseType>(
                Arrays.asList(AnalyseType.HEATPMAP));
        assertTrue(client1.setAnalyseTypes(list1));
        assertTrue(client1.getAnalyseTypes().equals(list1));
        assertFalse(client1.getAnalyseTypes().isEmpty());

        Client client2 = new Client();
        List<AnalyseType> list2 = new ArrayList<AnalyseType>(
                Arrays.asList(AnalyseType.VERLAUF));
        assertTrue(client2.setAnalyseTypes(list2));
        assertTrue(client2.getAnalyseTypes().equals(list2));
        assertFalse(client2.getAnalyseTypes().isEmpty());

        Client client3 = new Client();
        List<AnalyseType> list3 = new ArrayList<AnalyseType>(
                Arrays.asList(AnalyseType.RELFRQIMGAREA));
        assertTrue(client3.setAnalyseTypes(list3));
        assertTrue(client3.getAnalyseTypes().equals(list3));
        assertFalse(client3.getAnalyseTypes().isEmpty());

        assertFalse(client1.getAnalyseTypes().equals(list2));
        assertFalse(client1.getAnalyseTypes().equals(list3));
        assertFalse(client2.getAnalyseTypes().equals(list3));

        // * Leere Liste wird übergeben
        Client client4 = new Client();
        assertFalse(client4.setAnalyseTypes(new ArrayList<AnalyseType>()));
    }

    /**
     * Testet, ob Daten gesetzt werden.
     */
    @Test
    public void dataShouldBeSet() {
        // * Korrekte Zeiteingabe und Daten zu Tool-Typ vorhanden
        Client client1 = new Client();
        assertTrue(client1.getData(new DateTime(2021, 11, 28, 17, 0, 0),
                new DateTime(2021, 11, 28, 18, 0, 0), ToolType.ZOOM));

        Client client2 = new Client();
        assertTrue(client2.getData(new DateTime(2021, 11, 28, 17, 0, 0),
                new DateTime(2021, 11, 28, 18, 0, 0), ToolType.EYETRACKING));

        Client client3 = new Client();
        assertTrue(client3.getData(new DateTime(2021, 11, 28, 17, 0, 0),
                new DateTime(2021, 11, 28, 18, 0, 0), ToolType.CODECHARTS));

        // * Korrekte Zeiteingabe, aber Daten zu Tool-Typ nicht vorhanden
        Client client4 = new Client();
        assertFalse(client4.getData(new DateTime(2021, 11, 28, 15, 0, 0),
                new DateTime(2021, 11, 28, 16, 0, 0), ToolType.ZOOM));

        Client client5 = new Client();
        assertFalse(client5.getData(new DateTime(2021, 11, 28, 15, 0, 0),
                new DateTime(2021, 11, 28, 16, 0, 0), ToolType.EYETRACKING));

        Client client6 = new Client();
        assertFalse(client6.getData(new DateTime(2021, 11, 28, 15, 0, 0),
                new DateTime(2021, 11, 28, 16, 0, 0), ToolType.CODECHARTS));

        // * Falsche Zeiteingabe
        Client client7 = new Client();
        assertFalse(client7.getData(new DateTime(2021, 11, 28, 18, 0, 0),
                new DateTime(2021, 11, 28, 17, 0, 0), ToolType.ZOOM));

        Client client8 = new Client();
        assertFalse(client8.getData(new DateTime(2021, 11, 28, 18, 0, 0),
                new DateTime(2021, 11, 28, 17, 0, 0), ToolType.EYETRACKING));

        Client client9 = new Client();
        assertFalse(client9.getData(new DateTime(2021, 11, 28, 18, 0, 0),
                new DateTime(2021, 11, 28, 17, 0, 0), ToolType.CODECHARTS));
    }

    // ? Woher kommen Daten für den Export?
    /**
     * Testet, ob Export der analysierten Daten funktioniert.
     */
    @Test
    public void exportShouldWork() {
        Client client1 = new Client();
        assertTrue(client1.export());
    }

    // ? Woher kommen Daten für den Export?
    /**
     * Testet, ob Export der rohen Daten der Analyse funktioniert.
     */
    @Test
    public void exportRawShouldWork() {
        Client client1 = new Client();
        assertTrue(client1.exportRaw());
    }

}
