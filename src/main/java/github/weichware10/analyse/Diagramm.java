package github.weichware10.analyse;

import github.weichware10.util.data.TrialData;
import java.util.List;

/**
 * verantwortlich für die Erstellung der Diagramm-Diagramme.
 */
public class Diagramm extends Analyse {
    private final TrialData data;
    private final ConfigDiagramm confDia;

    /**
     * verantwortlich für die Erstellung der Diagramm-Diagramme.
     *
     * @param data    - Daten die zur Erstellung der Diagramme benötigt werden
     * @param confDia - Konfigurationen für die Diagrammarten
     */
    public Diagramm(TrialData data, ConfigDiagramm confDia) {
        this.data = data;
        this.confDia = confDia;
    }

    /**
     * erstellt Diagramm, welches die relativen Häufigkeiten pro Bildbereich
     * darstellt.
     *
     * @return Pfad des Bildes des erstellten Diagramms
     */
    public String createRelFreqImgArea() {
        return drawDiagramm(null, DiagrammType.RELFRQIMGAREA);
    }

    /**
     * erstellt Diagramm, welches die relativen Häufigkeiten der Blickedauer bzw.
     * Zoomstärken darstellt.
     *
     * @return Pfad des Bildes des erstellten Diagramms
     */
    public String createViewTimeDistr() {
        return drawDiagramm(null, DiagrammType.VIEWTIMEDISTR);
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
    private String drawDiagramm(List<Float> diagrammData, DiagrammType type) {
        return ".../diagramm/" + type + "_" + this.data.toolType.toString()
                + "_" + this.data.configId + ".jpg";
    }

}
