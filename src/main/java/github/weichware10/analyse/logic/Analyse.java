package github.weichware10.analyse.logic;

import github.weichware10.util.data.TrialData;
import java.util.List;

/**
 * beinhaltet Methoden die zur Analyse benötigt werden.
 */
public abstract class Analyse {

    /**
     * berechnet die relativen Häufigkeiten der Bildkoordinaten.
     *
     * @return Liste mit den relativen Häufigkeiten der Bildkoordinaten
     */
    protected List<List<Float>> calcRelFreq(TrialData data) {
        return null;
    }
}
