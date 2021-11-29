package github.weichware10.analyse;

import github.weichware10.util.Data;
import java.util.List;

/**
 * verantwortlich für die Erstellung der Heatmap.
 */
public class Heatmap extends Analyse {
    private final Data data;
    private final ConfigHeatmap confHm;
    private List<List<Float>> heatmap;

    /**
     * verantwortlich für die Erstellung der Heatmap.
     *
     * @param data   - Daten die zur Erstellung der Heatmps benötigt werden
     * @param confHm - Konfiguration der Heatmap
     */
    public Heatmap(Data data, ConfigHeatmap confHm) {
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
        return null;
    }

    /**
     * vergleicht zwei Heatmaps und erstellt aus dem Vergleich eine Heatmap.
     *
     * @param heatmap1 - erste Heatmap für den Vergleich
     * @param heatmap2 - zweite Heatmap für den Vergleich
     * @return Pfad des Bildes der erstellten Heatmap
     */
    public static String compHeatmaps(Heatmap heatmap1, Heatmap heatmap2) {
        return null;
    }
}
