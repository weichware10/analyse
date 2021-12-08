package github.weichware10.analyse;

import github.weichware10.util.data.TrialData;
import java.util.List;

/**
 * verantwortlich für die Erstellung der Heatmap.
 */
public class Heatmap extends Analyse {
    private final TrialData data;
    private final ConfigHeatmap confHm;
    private List<List<Float>> heatmap;

    /**
     * verantwortlich für die Erstellung der Heatmap.
     *
     * @param data   - Daten die zur Erstellung der Heatmaps benötigt werden
     * @param confHm - Konfiguration der Heatmap
     */
    public Heatmap(TrialData data, ConfigHeatmap confHm) {
        this.data = data;
        this.confHm = confHm;
    }

    /**
     * erstellt die Heatmap, welche die relativen Häufigkeiten der betrachteten
     * Bildkoordinaten darstellt.
     *
     * @return Pfad des Bildes der erstellten Heatmap
     */
    public String createHeatmap() {
        return ".../heatmap/HEATMAP_" + this.data.toolType.toString()
            + "_" + this.data.configId + ".jpg";
    }

    /**
     * vergleicht zwei Heatmaps und erstellt aus dem Vergleich eine Heatmap.
     *
     * @param heatmap1 - erste Heatmap für den Vergleich
     * @param heatmap2 - zweite Heatmap für den Vergleich
     * @return Pfad des Bildes der erstellten Heatmap
     */
    public static String compHeatmaps(Heatmap heatmap1, Heatmap heatmap2) {
        return ".../heatmap/COMPHEATMAP_" + heatmap1.data.toolType.toString()
            + "_" + heatmap1.data.configId + "_" + heatmap2.data.toolType.toString()
            + "_" + heatmap2.data.configId + ".jpg";
    }
}
