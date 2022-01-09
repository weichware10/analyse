package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.config.DiagramConfig;
import github.weichware10.analyse.config.HeatmapConfig;
import github.weichware10.analyse.enums.AnalyseType;
import github.weichware10.analyse.gui.util.AbsScene;
import github.weichware10.util.Logger;
import github.weichware10.util.data.TrialData;
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
    private static TrialData trial;
    private static TrialData trialComp;
    private static HeatmapConfig hmConfig = new HeatmapConfig();
    private static DiagramConfig diaConfig = new DiagramConfig();

    /**
     * Startet die App.
     */
    public static void start() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = start(Main.primaryStage,
                Login.class.getResource("Analyzer.fxml"),
                root,
                null,
                "Analyse",
                MainMenuBar.getMenuBar(),
                (int) screenBounds.getWidth() / 2,
                (int) screenBounds.getHeight() / 2).root;
    }

    /**
     * /**
     * Setzt den Analyse-Typ.
     *
     * @param analyseType - Analyse-Typ
     */
    public static void setAnalyseType(AnalyseType analyseType) {
        Analyzer.analyseType = analyseType;

        // Zurücksetzen bei Änderung des Analyse-Typs
        hmConfig = new HeatmapConfig();
        diaConfig = new DiagramConfig();

        if (Analyzer.analyseType != AnalyseType.COMPHEATMAP) {
            trialComp = null;
        }

        Logger.info("analyseType set to " + analyseType);
    }

    /**
     * Setzt das ausgewählte Trial.
     *
     * @param analyseTypMenuButton - MenuButton für Analyse-Typ
     */
    public static void setTrialId(MenuButton analyseTypMenuButton,
            Button configButton, Button analyseButton, Button selectCompTrialButton) {
        String oldTrialId = "null";
        if (trial != null) {
            oldTrialId = trial.getTrialId();
        }
        trial = TrialSelector.getTrialData();

        if (oldTrialId != trial.getTrialId()) {
            // Zurücksetzen bei Änderung des Trials
            analyseTypMenuButton.setDisable(true);
            configButton.setDisable(true);
            analyseButton.setDisable(true);
            selectCompTrialButton.setVisible(false);
            hmConfig = new HeatmapConfig();
            diaConfig = new DiagramConfig();
        }

        if (trial != null) {
            analyseTypMenuButton.setDisable(false);
        }
        Logger.info("trialId set to " + trial.trialId);
    }

    /**
     *  Setzt das ausgewählte Vergleichs Trial.
     *
     * @param analyseButton - MenuButton für Analyse-Typ
     */
    public static void setTrialIdComp(Button analyseButton) {
        trialComp = TrialSelector.getTrialData();
        if (trialComp != null) {
            analyseButton.setDisable(false);
        } else {
            analyseButton.setDisable(true);
        }
        Logger.info("trialIdComp set to " + trialComp.trialId);
    }

    /**
     * setzt die Konfiguration für Heatmap bzw. Diagramm Analyse
     */
    public static void setConfig() {
        if (analyseType == AnalyseType.COMPHEATMAP
                || analyseType == AnalyseType.HEATPMAP) {
            HeatmapConfigurator.start(hmConfig);
        } else if (analyseType == AnalyseType.RELFRQIMGAREA
                || analyseType == AnalyseType.VIEWTIMEDISTR) {
            // TODO: DiagrammConfigurator
        }
    }

    public static void analyse() {
        Logger.info("Analyzer");
    }

}
