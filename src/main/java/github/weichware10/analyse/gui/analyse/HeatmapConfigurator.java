package github.weichware10.analyse.gui.analyse;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.config.HeatmapConfig;
import github.weichware10.analyse.gui.util.AbsScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Konfiguratorfenster für Heatmap-Analysen der App.
 */
public class HeatmapConfigurator extends AbsScene {

    private static VBox root;
    private static HeatmapConfiguratorController controller;

    /**
     * Startet die App.
     *
     * @param hmConfig - aktuelle Konfiguration
     */
    public static void start(HeatmapConfig hmConfig) {
        Dialog<String> configDialog = new Dialog<>();
        ButtonType applyButtonType = new ButtonType("Übernehmen", ButtonData.APPLY);
        configDialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);

        configDialog.setTitle("Konfiguration Heatmap");

        Stage stage = (Stage) configDialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image(Main.class.getResource("app-icon.png").toString()));

        InitResult ir =
                initialize(HeatmapConfigurator.class.getResource("HeatmapConfigurator.fxml"));
        root = (VBox) ir.root;
        controller = (HeatmapConfiguratorController) ir.controller;

        // Beim erneueten Aufrufen bereits gesetzte Konfiguration wieder setzten
        controller.setMinColorDiff(hmConfig.getMinColorDiff());
        controller.setMaxColorDiff(hmConfig.getMaxColorDiff());
        controller.setShowGrid(hmConfig.isGrid());
        controller.setShowImage(hmConfig.isImage());

        final Button ok = (Button) configDialog.getDialogPane().lookupButton(applyButtonType);
        ok.addEventFilter(ActionEvent.ACTION, applyEvent -> {
            hmConfig.setMinColorDiff(controller.getMinColorDiff());
            hmConfig.setMaxColorDiff(controller.getMaxColorDiff());
            hmConfig.setGrid(controller.getShowGrid());
            hmConfig.setImage(controller.getShowImage());
        });
        configDialog.getDialogPane().setContent(root);
        configDialog.showAndWait();
    }
}
