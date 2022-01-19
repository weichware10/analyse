package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.general.MainMenuBar;
import github.weichware10.util.ToolType;
import github.weichware10.util.config.CodeChartsConfiguration;
import github.weichware10.util.config.ConfigLoader;
import github.weichware10.util.config.ConfigWriter;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.config.ZoomMapsConfiguration;
import github.weichware10.util.gui.AbsScene;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Konfigurator zum Anzeigen und Erstellen von Konfigurationen.
 */
public class Configurator extends AbsScene {
    private static Parent root;
    private static ConfiguratorController controller;
    private static List<String> strings = new ArrayList<>();
    protected static SimpleObjectProperty<Mode> mode = new SimpleObjectProperty<>(Mode.NEW);
    protected static String configId = null;

    /**
     * startet den Konfigurator.
     */
    public static void start() {
        InitResult ir = start(Main.primaryStage,
                Configurator.class.getResource("Configurator.fxml"),
                root,
                null,
                "Analyse - Konfigurator",
                MainMenuBar.getMenuBar(),
                null,
                null,
                null);
        root = ir.root;
        controller = (ConfiguratorController) ir.controller;
    }

    /**
     * befüllt die GUI mit den Wrten aus der Konfiguration.
     *
     * @param configuration - die Konfiguration mit den Werten
     * @param nextMode      - welcher Modus nach dem befüllen ausgewählt werden soll
     */
    private static void fillGui(Configuration configuration, Mode nextMode) {
        // allgemeine Konfiguration
        controller.toolTypeBox.setValue(configuration.getToolType());
        controller.introField.setText(configuration.getIntro());
        controller.imgUrlField.setText(configuration.getImageUrl());
        controller.outroField.setText(configuration.getOutro());
        controller.tutorialBox.setSelected(configuration.getTutorial());
        configId = configuration.getConfigId();

        // toolspezifisch
        switch (configuration.getToolType()) {
            case CODECHARTS:
                CodeChartsConfiguration ccConfig = configuration.getCodeChartsConfiguration();
                controller.stringIdField.setText(
                        ccConfig.getStringId());
                controller.initialSizeFieldX.setText(
                        Integer.toString(ccConfig.getInitialSize()[0]));
                controller.initialSizeFieldY.setText(
                        Integer.toString(ccConfig.getInitialSize()[1]));
                controller.timingsImgField.setText(
                        Long.toString(ccConfig.getTimings()[0]));
                controller.timingsGridField.setText(
                        Long.toString(ccConfig.getTimings()[1]));
                controller.showGridBox.setSelected(
                        ccConfig.getShowGrid());
                controller.relativeSizeBox.setSelected(
                        ccConfig.getRelativeSize());
                controller.randomStringsBox.setSelected(
                        ccConfig.getRandomized());
                controller.iterationsField.setText(
                        Integer.toString(ccConfig.getIterations()));
                controller.maxDepthField.setText(
                        Integer.toString(ccConfig.getMaxDepth()));
                controller.horizontalSplitField.setText(
                        Integer.toString(ccConfig.getDefaultHorizontal()));
                controller.verticalSplitField.setText(
                        Integer.toString(ccConfig.getDefaultVertical()));
                updateStringList(ccConfig.getStrings());
                break;
            case ZOOMMAPS:
                ZoomMapsConfiguration zmConfig = configuration.getZoomMapsConfiguration();
                controller.questionField.setText(
                        configuration.getQuestion());
                controller.speedField.setText(
                        Double.toString(zmConfig.getSpeed()));
                controller.imageViewWidthField.setText(
                        Double.toString(zmConfig.getImageViewWidth()));
                controller.imageViewHeightField.setText(
                        Double.toString(zmConfig.getImageViewHeight()));
                break;
            default: // EYETRACKING
                break;
        }
        mode.set(nextMode);
    }

    /**
     * konvertiert die Eingaben in eine Konfiguration.
     *
     * @return die erstellte Konfiguration
     */
    private static Configuration getConfiguration() {
        Configuration configuration = null;

        String imageUrl = controller.imgUrlField.getText();
        String intro = controller.introField.getText();
        String outro = controller.outroField.getText();
        boolean tutorial = controller.tutorialBox.selectedProperty().get();

        if (imageUrl == null || imageUrl.length() == 0) {
            controller.problemArea.setText("Die Bild-URL ist nicht-optional.");
            return null;
        } else if (intro == null || intro.length() == 0) {
            controller.problemArea.setText("Das Intro ist nicht-optional.");
            return null;
        } else if (outro == null || outro.length() == 0) {
            controller.problemArea.setText("Das Outro ist nicht-optional.");
            return null;
        }

        try {
            if (controller.toolTypeBox.getValue() == ToolType.CODECHARTS) {
                String stringId = controller.stringIdField.getText();
                int[] initialSize = new int[] {
                        Integer.valueOf(controller.initialSizeFieldX.getText()),
                        Integer.valueOf(controller.initialSizeFieldY.getText())
                };
                long[] timings = new long[] {
                        Long.valueOf(controller.timingsImgField.getText()),
                        Long.valueOf(controller.timingsGridField.getText())
                };
                boolean showGrid = controller.showGridBox.selectedProperty().get();
                boolean relativeSize = controller.relativeSizeBox.selectedProperty().get();
                boolean randomized = controller.randomStringsBox.selectedProperty().get();
                int iterations = Integer.valueOf(controller.iterationsField.getText());
                int maxDepth = Integer.valueOf(controller.maxDepthField.getText());
                int defaultHorizontal = Integer.valueOf(controller.horizontalSplitField.getText());
                int defaultVertical = Integer.valueOf(controller.verticalSplitField.getText());

                if (stringId == null || stringId.length() == 0) {
                    controller.problemArea.setText("Die String-ID ist nicht-optional.");
                    return null;
                } else if (strings == null || strings.size() == 0) {
                    controller.problemArea.setText("Die String-Liste ist leer.");
                    return null;
                }

                CodeChartsConfiguration codeChartsConfiguration = new CodeChartsConfiguration(
                        stringId,
                        strings,
                        initialSize,
                        timings,
                        showGrid,
                        relativeSize,
                        randomized,
                        maxDepth,
                        iterations,
                        defaultHorizontal,
                        defaultVertical);

                configuration = new Configuration(
                        configId,
                        null,
                        imageUrl,
                        intro,
                        outro,
                        tutorial,
                        codeChartsConfiguration);
            } else {
                String question = controller.questionField.getText();
                double speed = Double.valueOf(controller.speedField.getText());
                double imageViewWidth = Double.valueOf(controller.imageViewWidthField.getText());
                double imageViewHeight = Double.valueOf(controller.imageViewHeightField.getText());

                if (question == null || question.length() == 0) {
                    controller.problemArea.setText("Die Frage ist nicht-optional.");
                    return null;
                }

                ZoomMapsConfiguration zoomMapsConfiguration = new ZoomMapsConfiguration(
                        speed,
                        imageViewWidth,
                        imageViewHeight);

                configuration = new Configuration(
                        configId,
                        question,
                        imageUrl,
                        intro,
                        outro,
                        tutorial,
                        zoomMapsConfiguration);
            }
        } catch (NumberFormatException e) {
            controller.problemArea.setText("Eine eingegebene Zahl ist invalid:\n" + e.toString());
        }

        return configuration;
    }

    /**
     * Lädt eine Konfiguration aus einer JSON-Datei.
     */
    protected static void loadFromJson() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("JSON Config auswählen");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("JSON Dateien",
                        "*.json"));
        File jsonFile = fileChooser.showOpenDialog(Main.primaryStage);
        String location = (jsonFile != null) ? jsonFile.getAbsolutePath() : null;
        Configuration configuration = null;
        if (location != null) {
            configuration = ConfigLoader.fromJson(location);
        }
        if (configuration == null) {
            controller.problemArea.setText(String.format("Could not load from \"%s\"", location));
        }
        fillGui(configuration, Mode.JSONVIEW);
    }

    /**
     * Schreibt eine Konfiguration in eine JSON-Datei.
     */
    protected static void writeToJson() {
        Configuration configuration = getConfiguration();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("JSON Config Speicherort auswählen");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("JSON Dateien",
                        "*.json"));
        File jsonFile = fileChooser.showSaveDialog(Main.primaryStage);
        String location = (jsonFile != null) ? jsonFile.getAbsolutePath() : null;
        if (location != null && !ConfigWriter.toJson(location, configuration)) {
            controller.problemArea.setText(String.format("Could not write to \"%s\"", location));
        }
    }

    /**
     * Updated die String-Liste, auf die stringId achtend / mit einer gerade
     * importierten Liste.
     *
     * @param newStrings - die neue String-Liste
     */
    protected static void updateStringList(List<String> newStrings) {
        String stringId = controller.stringIdField.getText();
        if (stringId == null || stringId.length() == 0) {
            controller.stringIdButton.setText("...");
            controller.stringIdButton.setDisable(true);
            return;
        }
        controller.stringIdButton.setDisable(false);
        if (newStrings == null || newStrings.size() == 0) {
            strings = Main.dataBaseClient.strings.get(stringId);
        } else {
            strings = newStrings;
        }
        if (strings == null || strings.size() == 0) {
            controller.stringIdButton.setText("Liste mit dieser ID erstellen");
        } else {
            controller.stringIdButton.setText("Liste anzeigen");
        }
    }

    /**
     * updated den Konfigurator-Modus.
     */
    protected static void changeToEdit() {
        switch (mode.get()) {
            case JSONVIEW:
                mode.set(Mode.JSONEDIT);
                break;
            case DBVIEW:
                mode.set(Mode.DBEDIT);
                break;
            default: // do not change
                break;
        }
    }

    /**
     * mode of configurator.
     */
    protected enum Mode {
        JSONVIEW, JSONEDIT, DBVIEW, DBEDIT, NEW
    }
}
