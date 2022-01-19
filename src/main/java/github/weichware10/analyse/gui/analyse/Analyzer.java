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
import java.util.Arrays;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static String heatmapImage;
    private static LineChart<Number, Number> verlaufLineChart;

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
        controller.statusLabel.setVisible(false);
        controller.exportButton.setDisable(true);
        controller.exportRawButton.setDisable(true);
        controller.analysePane.getChildren().clear();
        heatmapImage = null;
        verlaufLineChart = null;

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
                controller.statusLabel.setVisible(false);
                controller.exportButton.setDisable(true);
                controller.exportRawButton.setDisable(true);
                controller.analysePane.getChildren().clear();
                heatmapImage = null;
                verlaufLineChart = null;
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
        controller.statusLabel.setVisible(false);

        if (analyseType == AnalyseType.COMPHEATMAP
                || analyseType == AnalyseType.HEATMAP) {
            HeatmapConfigurator.start(hmConfig);
            Logger.info("Start Heatmap Configurator");
        } else if (analyseType == AnalyseType.RELDEPTHDISTR) {
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

        controller.statusLabel.setVisible(false);

        switch (analyseType) {
            case HEATMAP:
                heatmapImage = Heatmap.createHeatmap(trial, hmConfig);
                break;
            case COMPHEATMAP:
                heatmapImage = Heatmap.createHeatmapComp(trial, trialComp, hmConfig);
                break;
            case VERLAUF:
                verlaufLineChart = Verlauf.createVerlauf(Arrays.asList(trial));
                break;
            case COMPVERLAUF:
                verlaufLineChart = Verlauf.createVerlauf(Arrays.asList(trial, trialComp));
                break;
            case RELDEPTHDISTR:
                break;
            default:
                // sollte niemals eintreten
                break;
        }
        if (heatmapImage != null) {
            // Pane leeren
            controller.analysePane.getChildren().clear();

            // ImageView mit Heatmap setzen
            ImageView imageView = new ImageView(new Image(heatmapImage));
            imageView.setFitWidth(680.0f);
            imageView.setFitHeight(405.0f);
            imageView.setPreserveRatio(true);
            controller.analysePane.getChildren().addAll(imageView);

            // Export aktivieren
            controller.exportButton.setDisable(false);
            controller.exportRawButton.setDisable(false);
        } else if (verlaufLineChart != null) {
            // Pane leeren
            controller.analysePane.getChildren().clear();

            // LineChart vom Verlauf setzen
            controller.analysePane.getChildren().addAll(verlaufLineChart);

            // Export aktivieren
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
            if (analyseType == AnalyseType.HEATMAP || analyseType == AnalyseType.COMPHEATMAP) {
                Thread taskThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (saveImage(location)) {
                            Platform.runLater(() -> controller.setExportStatus(
                                    String.format("Bild unter %s gesepeichert", location)));
                        } else {
                            Platform.runLater(() -> controller.setExportStatus(
                                    "Bild konnte nicht gespeichert werden"));
                        }
                    }
                });
                taskThread.start();
            } else if (analyseType == AnalyseType.VERLAUF
                    || analyseType == AnalyseType.COMPVERLAUF) {
                if (saveAsPng(location)) {
                    controller.setExportStatus(String.format(
                            "Diagramm unter %s gesepeichert", location));
                } else {
                    controller.setExportStatus("Diagramm konnte nicht gespeichert werden");
                }
            }
        }
    }

    /**
     * Speichert Bild unter location.
     *
     * @param location - Speicherort
     * @return Erfolgsboolean
     */
    private static boolean saveImage(String location) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(heatmapImage));
            ImageIO.write(image, "png", new File(location));
        } catch (IOException e) {
            Logger.error("Failed to save image", e, true);
            return false;
        }
        Logger.info("Image saved: " + location);
        return true;
    }

    /**
     * Speichert Diagramm als Bild unter location.
     *
     * @param location - Speicherort
     * @return Erfolgsboolean
     */
    private static boolean saveAsPng(String location) {
        WritableImage image = controller.analysePane.snapshot(new SnapshotParameters(), null);

        // Behebt Bug, dass Elemente nach Snapshot verschoben werden
        Main.primaryStage.setWidth(Main.primaryStage.getWidth() + 0.0001f);
        Main.primaryStage.setHeight(Main.primaryStage.getHeight() + 0.0001f);

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
     * Entscheidet ob eine oder zwei Versuche exportiert werden (Raw-Daten).
     */
    public static void exportRaw() {
        exportRaw(trial);
        if (analyseType == AnalyseType.COMPVERLAUF || analyseType == AnalyseType.COMPHEATMAP) {
            exportRaw(trialComp);
        }
    }

    /**
     * Export von Raw-Daten des Versuchs.
     */
    private static void exportRaw(TrialData trial) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Versuchs-Daten speichern unter");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("JSON File", "*.json"));

        // Dateipfad als String speichern und json laden
        String location = fileChooser.showSaveDialog(Main.primaryStage).getAbsolutePath();
        if (location != null) {
            Thread taskThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (TrialData.toJson(location, trial)) {
                        Platform.runLater(() -> controller.setExportStatus(
                                String.format("Versuchs-Daten unter %s gespeichert", location)));
                    } else {
                        Platform.runLater(() -> controller.setExportStatus(
                                "Versuchs-Daten konnten nicht gespeichert werden"));
                    }
                }
            });
            taskThread.start();
        }

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
        controller.exportButton.setDisable(true);
        controller.exportRawButton.setDisable(true);
        controller.errorLabel.setVisible(false);
        controller.statusLabel.setVisible(false);
        controller.analysePane.getChildren().clear();
        Analyzer.analyseType = null;
        Analyzer.trial = null;
        Analyzer.trialComp = null;
        Analyzer.hmConfig = new HeatmapConfig();
        Analyzer.diaConfig = new DiagramConfig();
        Analyzer.heatmapImage = null;
        Analyzer.verlaufLineChart = null;
    }
}
