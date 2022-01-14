package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
import javafx.scene.Parent;

/**
 * Interface zum Erstellen von Trials (auf Basis von configIds).
 */
public class TrialCreator extends AbsScene {
    private static Parent root;

    /**
     * Startet den TrialCreator.
     */
    public static void start() {
        root = start(Main.primaryStage,
                TrialCreator.class.getResource("TrialCreator.fxml"),
                root,
                null,
                "Analyse - Trialerstellung",
                null,
                null,
                null).root;
    }
}
