package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.general.MainMenuBar;
import github.weichware10.util.ToolType;
import github.weichware10.util.config.CodeChartsConfiguration;
import github.weichware10.util.config.ConfigLoader;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.config.ZoomMapsConfiguration;
import github.weichware10.util.gui.AbsScene;
import java.io.File;
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
     * @param nextMode - welcher Modus nach dem befüllen ausgewählt werden soll
     */
    private static void fillGui(Configuration configuration, Mode nextMode) {
        // allgemeine Konfiguration
        controller.toolTypeBox.setValue(configuration.getToolType());
        controller.introField.setText(configuration.getIntro());
        controller.imgUrlField.setText(configuration.getImageUrl());
        controller.outroField.setText(configuration.getOutro());
        controller.tutorialBox.setSelected(configuration.getTutorial());

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

        if (controller.toolTypeBox.getValue() == ToolType.CODECHARTS) {
            ;
        }

        return configuration;
    }

    /**
     * Lädt eine Konfiguration aus einer JSON-Datei.
     */
    protected static void loadFromJson() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("JSON Trial auswählen");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("JSON Dateien", "*.json"));
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
