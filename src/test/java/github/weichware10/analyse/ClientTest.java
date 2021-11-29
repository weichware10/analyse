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
        assertTrue("setAnalyseTypes sollte True zurückgeben", client1.setAnalyseTypes(list1));
        assertTrue("Vergleich sollte True zurückgeben", client1.getAnalyseTypes().equals(list1));
        assertFalse("Test auf leere Liste sollte False sein", client1.getAnalyseTypes().isEmpty());

        Client client2 = new Client();
        List<AnalyseType> list2 = new ArrayList<AnalyseType>(
                Arrays.asList(AnalyseType.VERLAUF));
        assertTrue("setAnalyseTypes sollte True zurückgeben", client2.setAnalyseTypes(list2));
        assertTrue("Vergleich sollte True zurückgeben", client2.getAnalyseTypes().equals(list2));
        assertFalse("Test auf leere Liste sollte False sein", client2.getAnalyseTypes().isEmpty());

        Client client3 = new Client();
        List<AnalyseType> list3 = new ArrayList<AnalyseType>(
                Arrays.asList(AnalyseType.RELFRQIMGAREA));
        assertTrue("setAnalyseTypes sollte True zurückgeben", client3.setAnalyseTypes(list3));
        assertTrue("Vergleich sollte True zurückgeben", client3.getAnalyseTypes().equals(list3));
        assertFalse("Test auf leere Liste sollte False sein", client3.getAnalyseTypes().isEmpty());

        assertFalse("Vergleich sollte False zurückgeben", client1.getAnalyseTypes().equals(list2));
        assertFalse("Vergleich sollte False zurückgeben", client1.getAnalyseTypes().equals(list3));
        assertFalse("Vergleich sollte False zurückgeben", client2.getAnalyseTypes().equals(list3));

        // * Leere Liste wird übergeben
        Client client4 = new Client();
        assertFalse("setAnalyseTypes sollte False zurückgeben, da leere Liste",
                client4.setAnalyseTypes(new ArrayList<AnalyseType>()));
    }

    /**
     * Testet, ob Daten gesetzt werden.
     */
    @Test
    public void dataShouldBeSet() {
        // * Korrekte Zeiteingabe und Daten zu Tool-Typ vorhanden
        Client client1 = new Client();
        assertTrue("getData sollte True zurückgeben", client1.getData(
                new DateTime(2021, 11, 28, 16, 0, 0),
                new DateTime(2021, 11, 28, 19, 0, 0), ToolType.ZOOM));

        Client client2 = new Client();
        assertTrue("getData sollte True zurückgeben", client2.getData(
                new DateTime(2021, 11, 28, 15, 0, 0),
                new DateTime(2021, 11, 28, 18, 0, 0), ToolType.EYETRACKING));

        Client client3 = new Client();
        assertTrue("getData sollte True zurückgeben", client3.getData(
                new DateTime(2021, 11, 28, 19, 0, 0),
                new DateTime(2021, 11, 28, 20, 0, 0), ToolType.CODECHARTS));

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
        assertFalse("getData sollte False zurückgeben", client7.getData(
                new DateTime(2021, 11, 28, 18, 0, 0),
                new DateTime(2021, 11, 28, 17, 0, 0), ToolType.ZOOM));

        Client client8 = new Client();
        assertFalse("getData sollte False zurückgeben", client8.getData(
                new DateTime(2021, 11, 28, 18, 0, 0),
                new DateTime(2021, 11, 28, 17, 0, 0), ToolType.EYETRACKING));

        Client client9 = new Client();
        assertFalse("getData sollte False zurückgeben", client9.getData(
                new DateTime(2021, 11, 28, 18, 0, 0),
                new DateTime(2021, 11, 28, 17, 0, 0), ToolType.CODECHARTS));

        // * Zeitpunkt in der Zunkunft
        Client client10 = new Client();
        assertFalse("getData sollte False zurückgeben", client10.getData(
                new DateTime(2021, 11, 28, 18, 0, 0),
                DateTime.now().plusMinutes(5), ToolType.ZOOM));

        Client client11 = new Client();
        assertFalse("getData sollte False zurückgeben", client11.getData(
                DateTime.now().plusMinutes(5),
                DateTime.now().plusMinutes(10), ToolType.EYETRACKING));
    }

    // ? Woher kommen Daten für den Export?
    /**
     * Testet, ob Export der analysierten Daten funktioniert.
     */
    @Test
    public void exportShouldWork() {
        Client client1 = new Client();
        assertTrue("export sollte True zurückgeben", client1.export());
    }

    // ? Woher kommen Daten für den Export?
    /**
     * Testet, ob Export der rohen Daten der Analyse funktioniert.
     */
    @Test
    public void exportRawShouldWork() {
        Client client1 = new Client();
        assertTrue("exportRaw sollte True zurückgeben", client1.exportRaw());
    }

}
