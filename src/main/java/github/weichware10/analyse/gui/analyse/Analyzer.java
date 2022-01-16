package github.weichware10.analyse.gui.analyse;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.config.DiagramConfig;
import github.weichware10.analyse.config.HeatmapConfig;
import github.weichware10.analyse.enums.AnalyseType;
import github.weichware10.analyse.gui.general.MainMenuBar;
import github.weichware10.analyse.logic.Heatmap;
import github.weichware10.analyse.logic.Verlauf;
import github.weichware10.util.Logger;
import github.weichware10.util.data.TrialData;
import github.weichware10.util.gui.AbsScene;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

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
    private static String analysedImage;
    private static LineChart<Number, Number> lineChartSeries;

    /**
     * Startet die App.
     */
    public static void start() {
        InitResult ir = start(Main.primaryStage,
                Analyzer.class.getResource("Analyzer.fxml"),
                root,
                controller,
                "Analyse",
                MainMenuBar.getMenuBar(),
                null,
                null,
                null);
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
        controller.analysedImage.setImage(null);
        controller.exportButton.setDisable(true);
        controller.exportRawButton.setDisable(true);
        controller.chartPane.getChildren().clear();
        analysedImage = null;
        lineChartSeries = null;

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
                controller.errorLabel.setVisible(false);
                controller.analysedImage.setImage(null);
                controller.exportButton.setDisable(true);
                controller.exportRawButton.setDisable(true);
                controller.chartPane.getChildren().clear();
                analysedImage = null;
                lineChartSeries = null;
                hmConfig = new HeatmapConfig();
                diaConfig = new DiagramConfig();
            }
            controller.analyseTypMenuButton.setDisable(false);
            Logger.info("trialId set to " + trial.trialId);
        }
    }

    /**
     * Setzt das ausgewählte Vergleichs Trial.
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
                    "Trial & Vergleichs-Trial sind identisch. Wähle ein anderes Vergleichs-Trial!");
            controller.errorLabel.setVisible(true);
        }
    }

    /**
     * Setzt die Konfiguration für Heatmap bzw. Diagramm Analyse
     */
    public static void setConfig() {
        controller.errorLabel.setVisible(false);

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

        controller.errorLabel.setVisible(false);

        switch (analyseType) {
            case HEATPMAP:
                analysedImage = Heatmap.createHeatmap(trial, hmConfig);
                break;
            case COMPHEATMAP:
                analysedImage = Heatmap.createHeatmapComp(trial, trialComp, hmConfig);
                break;
            case VERLAUF:
                lineChartSeries = Verlauf.createVerlauf(trial);
                break;
            case RELFRQIMGAREA:
                break;
            case VIEWTIMEDISTR:
                break;
            default:
                // sollte niemals eintreten
                break;
        }
        if (analysedImage != null) {
            controller.analysedImage.setImage(new Image(analysedImage));
            controller.exportButton.setDisable(false);
            controller.exportRawButton.setDisable(false);
        } else if (lineChartSeries != null) {
            controller.chartPane.getChildren().clear();
            controller.chartPane.getChildren().addAll(lineChartSeries);
            controller.exportButton.setDisable(false);
            controller.exportRawButton.setDisable(false);
        }
    }

    /**
     * Exportiert erstelltes Bild oder Diagramm.
     */
    public static void export() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Analyse Bild speichern unter");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("PNG Image", "*.png"));

        // Dateipfad als String speichern
        String location = fileChooser.showSaveDialog(Main.primaryStage).getAbsolutePath();
        if (location != null) {
            if (analyseType.equals(AnalyseType.HEATPMAP)) {
                if (saveImage(location)) {
                    controller.setExportStatus(
                            String.format("Bild unter %s gesepeichert", location));
                } else {
                    controller.setExportStatus("Bild konnte nicht gespeichert werden");
                }
            } else if (analyseType.equals(AnalyseType.VERLAUF)) {
                if (saveAsPng(location)) {
                    controller.setExportStatus(String.format(
                            "Diagramm unter %s gesepeichert", location));
                } else {
                    controller.setExportStatus("Diagramm konnte nicht gespeichert werden");
                }
            }
        }
    }

    // TODO: in util verschieben?(ja -> analysedImage als Paramter dazu)
    /**
     * Speichert Bild unter location.
     *
     * @param location - Speicherort
     * @return Erfolgsboolean
     */
    private static boolean saveImage(String location) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(analysedImage));
            ImageIO.write(image, "png", new File(location));
        } catch (IOException e) {
            Logger.error("Failed to save image", e, true);
            return false;
        }
        Logger.info("Image saved: " + location);
        return true;
    }

    // TODO: in util verschieben?
    /**
     * Speichert Diagramm als Bild unter location.
     *
     * @param location - Speicherort
     * @return Erfolgsboolean
     */
    private static boolean saveAsPng(String location) {
        WritableImage image = controller.chartPane.snapshot(new SnapshotParameters(), null);

        File file = new File(location);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            Logger.error("Failed to save Snapshot", e, true);
            return false;
        }
        return true;
    }

    /**
     * Setzt Analyse zurück.
     */
    public static void reset() {
        controller.analyseButton.setDisable(true);
        controller.analyseTypMenuButton.setDisable(true);
        controller.configButton.setDisable(true);
        controller.exportButton.setDisable(true);
        controller.exportRawButton.setDisable(true);
        controller.selectCompTrialButton.setVisible(false);
        controller.analysedImage.setImage(null);
        controller.exportButton.setDisable(true);
        controller.exportRawButton.setDisable(true);
        controller.errorLabel.setVisible(false);
        controller.chartPane.getChildren().clear();
        Analyzer.analyseType = null;
        Analyzer.trial = null;
        Analyzer.trialComp = null;
        Analyzer.hmConfig = new HeatmapConfig();
        Analyzer.diaConfig = new DiagramConfig();
        Analyzer.analysedImage = null;
        Analyzer.lineChartSeries = null;
    }
}
