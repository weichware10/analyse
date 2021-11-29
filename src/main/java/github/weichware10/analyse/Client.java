package github.weichware10.analyse;

import github.weichware10.util.Data;
import github.weichware10.util.Enums.ToolType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Grundlegende Klasse für den Analyse-Client.
 */
public class Client {
    private Data data;
    private Data dataForComp;
    private List<AnalyseType> analyseTypes;
    private List<String> analyzedData;
    private ConfigHeatmap confHm;
    private ConfigDiagramm confDia;

    public Client() {
        analyseTypes = new ArrayList<AnalyseType>();
        analyzedData = new ArrayList<String>();
    }

    /**
     * setzt die ausgewählten Analyse-Typen.
     *
     * @param selectedAnalyseTypes - die ausgewählten Analyse-Typen
     * @return
     *         true, falls Analyse-Typen gesetzt wurden;
     *         false, falls keine Analyse-Typen gesetzt wurden
     */
    public boolean setAnalyseTypes(List<AnalyseType> selectedAnalyseTypes) {
        if (selectedAnalyseTypes.isEmpty()) {
            return false;
        }
        this.analyseTypes = selectedAnalyseTypes;
        return true;
    }

    /**
     * gibt die Anaylse-Typen zurück.
     *
     * @return Analyse-Typen
     */
    public List<AnalyseType> getAnalyseTypes() {
        return this.analyseTypes;
    }

    /**
     * holt die angeforderten Daten vom Speichermedium, falls diese existieren.
     *
     * @param start - Startzeitpunkt der benötigten Daten
     * @param end - Endzeitpunkt der benötigten Daten
     * @param dataType - Tool-Typ der benötigten Daten
     * @return
     *         true, falls benötigte Daten gefunden und gesetzt wurden;
     *         false, falls benötigte Daten nicht gefunden wurden
     */
    public boolean getData(DateTime start, DateTime end, ToolType dataType) {
        if (start.isAfter(end) || start.isAfter(DateTime.now()) || end.isAfter(DateTime.now())) {
            return false;
        }

        // * Provisorische Lösung für Test, da er sonst nicht funktionieren würde
        int amountData = 3;
        List<DateTime> dataStart = new ArrayList<DateTime>(Arrays.asList(
            new DateTime(2021, 11, 28, 16, 0, 0), new DateTime(2021, 11, 28, 15, 0, 0),
            new DateTime(2021, 11, 28, 19, 0, 0)));
        List<DateTime> dataEnd = new ArrayList<DateTime>(Arrays.asList(
            new DateTime(2021, 11, 28, 19, 0, 0), new DateTime(2021, 11, 28, 18, 0, 0),
            new DateTime(2021, 11, 28, 20, 0, 0)));
        List<ToolType> dataToolType = new ArrayList<ToolType>(Arrays.asList(
            ToolType.ZOOM, ToolType.EYETRACKING, ToolType.CODECHARTS));

        for (int id = 0; id < amountData; id++) {
            if (dataStart.get(id).equals(start) && dataEnd.get(id).equals(end)
                && dataToolType.get(id).equals(dataType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * verändert die Standard-Konfiguration für die Heatmap-Analyse.
     *
     * @param confHm - Konfiguration der Heatmap-Analyse
     */
    public void setConfigAnalyseHm(ConfigHeatmap confHm) {
        this.confHm = confHm;
    }

    /**
     * verändert die Standard-Konfiguration für die Diagramm-Analyse.
     *
     * @param confDia - Konfiguration der Diagramm-Analyse
     */
    public void setConfigAnalyseDia(ConfigDiagramm confDia) {
        this.confDia = confDia;
    }

    /**
     * führt die ausgewählten Analysen durch.
     */
    public void analyseData() {
        ;
    }

    /**
     * zeigt die analysierten Daten an.
     */
    public void displayAnalyzedData() {
        ;
    }

    /**
     * exportiert die analysierten Daten.
     *
     * @return
     *         true, wenn Export erfolreich war;
     *         false, wenn Fehler aufgetreten ist
     */
    public boolean export() {
        return true;
    }

    /**
     * exportiert die rohen Daten der Analyse.
     *
     * @return
     *         true, wenn Export erfolreich war;
     *         false, wenn Fehler aufgetreten ist
     */
    public boolean exportRaw() {
        return true;
    }

}