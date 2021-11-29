package github.weichware10.analyse;

import github.weichware10.util.Data;
import github.weichware10.util.Enums.ToolType;
import java.util.ArrayList;
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
        return false;
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
     * @param start    - Startzeitpunkt der benötigten Daten
     * @param end      - Endzeitpunkt der benötigten Daten
     * @param dataType - Tool-Typ der benötigten Daten
     * @return
     *         true, falls benötigte Daten gefunden und gesetzt wurden;
     *         false, falls benötigte Daten nicht gefunden wurden
     */
    public boolean getData(DateTime start, DateTime end, ToolType dataType) {
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
        return false;
    }

    /**
     * exportiert die rohen Daten der Analyse.
     *
     * @return
     *         true, wenn Export erfolreich war;
     *         false, wenn Fehler aufgetreten ist
     */
    public boolean exportRaw() {
        return false;
    }

}
