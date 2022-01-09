package github.weichware10.analyse.gui;

import github.weichware10.analyse.config.DiagramConfig;
import github.weichware10.analyse.enums.AnalyseType;
import github.weichware10.analyse.gui.util.AbsScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

/**
 * Konfiguratorfenster für Diagramm-Analysen der App.
 */
public class DiagramConfigurator extends AbsScene {

    private static VBox root;
    private static DiagramConfiguratorController controller;

    /**
     * Startet die App.
     *
     * @param diaConfig - aktuelle Konfiguration
     */
    public static void start(DiagramConfig diaConfig, AnalyseType analyseType) {
        Dialog<String> configDialog = new Dialog<>();
        ButtonType applyButtonType = new ButtonType("Übernehmen", ButtonData.APPLY);
        configDialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, applyButtonType);

        configDialog.setTitle("Konfiguration Diagramm");

        InitResult ir =
                initialize(DiagramConfigurator.class.getResource("DiagramConfigurator.fxml"));
        root = (VBox) ir.root;
        controller = (DiagramConfiguratorController) ir.controller;

        // Sichtbarkeitseinstellungen
        if (analyseType == AnalyseType.RELFRQIMGAREA) {
            controller.amountAreas.setDisable(false);
            controller.minTimeSlider.setDisable(true);
            controller.maxTimeSlider.setDisable(true);
            controller.stepsSlider.setDisable(true);
            controller.minTimeLabel.setVisible(false);
            controller.maxTimeLabel.setVisible(false);
            controller.stepsLabel.setVisible(false);
        } else if (analyseType == AnalyseType.VIEWTIMEDISTR) {
            controller.amountAreas.setDisable(true);
            controller.minTimeSlider.setDisable(false);
            controller.maxTimeSlider.setDisable(false);
            controller.stepsSlider.setDisable(false);
            controller.minTimeLabel.setVisible(true);
            controller.maxTimeLabel.setVisible(true);
            controller.stepsLabel.setVisible(true);
        }

        // Beim erneueten Aufrufen bereits gesetzte Konfiguration wieder setzten
        controller.initAmountBox(diaConfig.getAmountAreas());
        controller.initMinTimeSlider(diaConfig.getMinTime());
        controller.initMaxTimeSlider(diaConfig.getMaxTime());
        controller.initStepsSlider(diaConfig.getStepsBetween());


        final Button ok = (Button) configDialog.getDialogPane().lookupButton(applyButtonType);
        ok.addEventFilter(ActionEvent.ACTION, applyEvent -> {
            if (analyseType == AnalyseType.RELFRQIMGAREA) {
                diaConfig.setAmountAreas(controller.getAmountAreas());
            } else if (analyseType == AnalyseType.VIEWTIMEDISTR) {
                diaConfig.setNewTime(controller.getMinTime(), controller.getMaxTime());
                diaConfig.setStepsBetween(controller.getSteps());
            }
        });
        configDialog.getDialogPane().setContent(root);
        configDialog.showAndWait();
    }
}
