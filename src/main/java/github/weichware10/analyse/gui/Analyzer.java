package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.enums.AnalyseType;
import github.weichware10.analyse.gui.util.AbsScene;
import github.weichware10.util.Logger;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Screen;

/**
 * Analysefenster der App.
 */
public class Analyzer extends AbsScene {

    private static Parent root;
    private static AnalyseType analyseType;
    private static String trialId;
    private static String trialIdComp;

    /**
     * Startet die App.
     */
    public static void start() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = start(Main.primaryStage,
                Login.class.getResource("Analyzer.fxml"),
                root,
                "Analyse",
                MainMenuBar.getMenuBar(),
                (int) screenBounds.getWidth() / 2,
                (int) screenBounds.getHeight() / 2);
    }

    /**
     * /**
     * Setzt den Analyse-Typ.
     *
     * @param analyseType - Analyse-Typ
     */
    public static void setAnalyseType(AnalyseType analyseType) {
        Analyzer.analyseType = analyseType;

        if (Analyzer.analyseType != AnalyseType.COMPHEATMAP) {
            trialIdComp = null;
        }

        Logger.info("analyseType set to " + analyseType);
    }

    /**
     * Setzt die ausgewählte TrialID.
     *
     * @param analyseTypMenuButton - MenuButton für Analyse-Typ
     */
    public static void setTrialId(MenuButton analyseTypMenuButton) {
        String trialId = TrialSelector.getTrialId();
        if (trialId != null) {
            analyseTypMenuButton.setDisable(false);
        }
        Logger.info("trialId set to " + trialId);
    }

    /**
     *  Setzt die ausgewählte TrialID für Vergleichs Trial.
     *
     * @param analyseButton - MenuButton für Analyse-Typ
     */
    public static void setTrialIdComp(Button analyseButton) {
        String trialId = TrialSelector.getTrialId();
        if (trialId != null) {
            analyseButton.setDisable(false);
        } else {
            analyseButton.setDisable(true);
        }
        Logger.info("trialIdComp set to " + trialIdComp);
    }

}
