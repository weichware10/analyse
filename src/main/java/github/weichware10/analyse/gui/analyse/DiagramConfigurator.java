package github.weichware10.analyse.gui.analyse;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.config.DiagramConfig;
import github.weichware10.analyse.enums.AnalyseType;
import github.weichware10.util.gui.AbsScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        Stage stage = (Stage) configDialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image(Main.class.getResource("app-icon.png").toString()));

        InitResult ir = initialize(
                DiagramConfigurator.class.getResource("DiagramConfigurator.fxml"));
        root = (VBox) ir.root;
        controller = (DiagramConfiguratorController) ir.controller;

        // Beim erneueten Aufrufen bereits gesetzte Konfiguration wieder setzten
        controller.initStepsSlider(diaConfig.getStepsBetween());
        controller.setBarChart(diaConfig.isBarChart());
        controller.setPieChart(!diaConfig.isBarChart());

        final Button ok = (Button) configDialog.getDialogPane().lookupButton(applyButtonType);
        ok.addEventFilter(ActionEvent.ACTION, applyEvent -> {
            diaConfig.setStepsBetween(controller.getSteps());
            diaConfig.setBarChart(controller.isBarChart());
        });
        configDialog.getDialogPane().setContent(root);
        configDialog.showAndWait();
    }
}
