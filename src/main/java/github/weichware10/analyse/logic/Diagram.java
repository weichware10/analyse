package github.weichware10.analyse.logic;

import github.weichware10.analyse.config.DiagramConfig;
import github.weichware10.util.data.TrialData;
import java.util.List;

/**
 * verantwortlich für die Erstellung der Diagramm-Diagramme.
 */
@SuppressWarnings("unused")
public class Diagram extends Analyse {
    private final TrialData data;
    private final DiagramConfig confDia;

    /**
     * verantwortlich für die Erstellung der Diagramm-Diagramme.
     *
     * @param data    - Daten die zur Erstellung der Diagramme benötigt werden
     * @param confDia - Konfigurationen für die Diagrammarten
     */
    public Diagram(TrialData data, DiagramConfig confDia) {
        this.data = data;
        this.confDia = confDia;
    }

    /**
     * erstellt Diagramm, welches die relativen Häufigkeiten der Blickedauer bzw.
     * Zoomstärken darstellt.
     *
     * @return Pfad des Bildes des erstellten Diagramms
     */
    public String createViewTimeDistr() {
        return drawDiagramm(null);
    }

    /**
     * berechnet die relativen Häufigkeiten der Blickdauer bzw. Zoomstärken
     *
     * @param timeTableData - Tabelle mit Zeitpunkt und Bildkoordinate bzw.
     *                      Zoomstärke
     * @return Daten für die Erstellung des Diagramms
     */
    private List<Float> calcViewTimeDistr(List<List<Float>> timeTableData) {
        return null;
    }

    /**
     * zeichnet das Diagramm.
     *
     * @param diagrammData - Daten die zur Erstellung des Diagramms benötigt werden
     * @param type         - Diagramm-Typ des zu erstellenden Diagramms
     * @return Pfad des Bildes des erstellten Diagramms
     */
    private String drawDiagramm(List<Float> diagrammData) {
        return null;
    }

}
