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
import javafx.stage.Screen;

/**
 * Analysefenster der App.
 */
public class Analyzer extends AbsScene {

    private static Parent root;
    private static AnalyzerController controller;
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
        InitResult ir = start(Main.primaryStage,
                Login.class.getResource("Analyzer.fxml"),
                root,
                null,
                "Analyse",
                MainMenuBar.getMenuBar(),
                (int) screenBounds.getWidth() / 2,
                (int) screenBounds.getHeight() / 2);
        root = ir.root;
        controller = (AnalyzerController) ir.controller;
    }

    /**
     * Setzt den Analyse-Typ.
     *
     * @param analyseType - Analyse-Typ
     */
    public static void setAnalyseType(AnalyseType analyseType) {
        Analyzer.analyseType = analyseType;

        // Zurücksetzen bei Änderung des Analyse-Typs
        hmConfig = new HeatmapConfig();
        diaConfig = new DiagramConfig();

        controller.errorLabel.setVisible(false);

        if (Analyzer.analyseType != AnalyseType.COMPHEATMAP) {
            trialComp = null;
        }

        Logger.info("analyseType set to " + analyseType);
    }

    /**
     * Setzt das ausgewählte Trial.
     */
    public static void setTrialId() {
        String oldTrialId = "null";
        if (trial != null) {
            oldTrialId = trial.getTrialId();
        }
        try {
            trial = TrialSelector.getTrialData(null);
        } catch (NullPointerException e) {
            Logger.error("Null Trial returned", e, true);
        }

        if (trial != null) {
            if (oldTrialId != trial.getTrialId()) {
                // Zurücksetzen bei Änderung des Trials
                controller.analyseTypMenuButton.setDisable(true);
                controller.configButton.setDisable(true);
                controller.analyseButton.setDisable(true);
                controller.selectCompTrialButton.setVisible(false);
                controller.analyseTypMenuButton.setText("Analyse-Typ");
                hmConfig = new HeatmapConfig();
                diaConfig = new DiagramConfig();
                controller.errorLabel.setVisible(false);
            }
            controller.analyseTypMenuButton.setDisable(false);
            Logger.info("trialId set to " + trial.trialId);
        }
    }

    /**
     *  Setzt das ausgewählte Vergleichs Trial.
     */
    public static void setTrialIdComp() {
        trialComp = TrialSelector.getTrialData(trial.configId);
        if (!trialComp.getTrialId().equals(trial.getTrialId())) {
            controller.analyseButton.setDisable(false);
            controller.errorLabel.setVisible(false);
            Logger.info("trialIdComp set to " + trialComp.trialId);
        } else {
            controller.analyseButton.setDisable(true);
            controller.errorLabel.setText(
                "Trial und Vergleichs-Trial sind identisch. Wähle ein anderes Vergleichs-Trial!");
            controller.errorLabel.setVisible(true);
        }
    }

    /**
     * Setzt die Konfiguration für Heatmap bzw. Diagramm Analyse
     */
    public static void setConfig() {
        if (analyseType == AnalyseType.COMPHEATMAP
                || analyseType == AnalyseType.HEATPMAP) {
            HeatmapConfigurator.start(hmConfig);
            Logger.info("Start Heatmap Configurator");
        } else if (analyseType == AnalyseType.RELFRQIMGAREA
                || analyseType == AnalyseType.VIEWTIMEDISTR) {
            DiagramConfigurator.start(diaConfig, analyseType);
            Logger.info("Start Diagram Configurator");
        }
    }

    /**
     * Start Analyse.
     */
    public static void analyse() {
        String output = String.format(
                "analyseType: %s \ntrial: %s \ntrialComp: %s \nhmConfig: %s \ndiaConfig: %s",
                analyseType != null ? analyseType : "null",
                trial != null ? trial.toString() : "null",
                trialComp != null ? trialComp.toString() : "null",
                hmConfig.toString(),
                diaConfig.toString());
        Logger.info(output);
    }
}
