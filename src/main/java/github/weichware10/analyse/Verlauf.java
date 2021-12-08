package github.weichware10.analyse;

import github.weichware10.util.data.TrialData;
import java.util.List;

/**
 * verantwortlich für die Erstellung des Verlauf-Diagramms.
 */
public class Verlauf extends Analyse {
    private final TrialData data;

    /**
     * verantwortlich für die Erstellung des Verlauf-Diagramms.
     *
     * @param data - Daten die zur Erstellung des Diagramms benötigt werden
     */
    public Verlauf(TrialData data) {
        this.data = data;
    }

    /**
     * erstellt Diagramm, welches den Verlauf der betrachteten Bildkoordinaten
     * darstellt.
     *
     * @return Pfad des Bildes des erstellten Diagramms
     */
    public String createVerlauf() {
        return drawDiagramm(null);
    }

    /**
     * zeichnet das Diagramm.
     *
     * @param diagrammData - Daten die zur Erstellung des Diagramms benötigt werden
     * @return Pfad des Bildes des erstellten Diagramms
     */
    private String drawDiagramm(List<List<Float>> diagrammData) {
        return ".../verlauf/VERLAUF_" + this.data.toolType.toString()
            + "_" + this.data.configId + ".jpg";
    }

}
