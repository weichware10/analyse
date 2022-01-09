package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
import github.weichware10.util.ToolType;
import github.weichware10.util.data.TrialData;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import org.joda.time.DateTime;

/**
 * Auswahldialog für Trial.
 */
public class TrialSelector extends AbsScene {

    private static BorderPane root;
    private static TrialSelectorController controller;
    private static String trialId;

    /**
     * Auswahldialog für Trial.
     */
    public static TrialData getTrialData() {

        Dialog<Void> selectorDialog = new Dialog<>();
        ButtonType select = new ButtonType("Trial auswählen", ButtonData.APPLY);
        selectorDialog.getDialogPane().getButtonTypes().addAll(select, ButtonType.CANCEL);

        selectorDialog.getDialogPane().setContent((root != null) ? root : initialize());

        final Button selectButton = (Button) selectorDialog.getDialogPane().lookupButton(select);

        selectButton.addEventFilter(ActionEvent.ACTION, selectEvent -> {
            TrialData selected = controller.resultTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                controller.setWarn("Bitte wählen Sie ein Trial aus.");
                selectEvent.consume();
                return;
            }
            trialId = selected.trialId;
        });


        selectorDialog.showAndWait();

        return (trialId != null) ? Main.dataBaseClient.trials.getTrial(trialId) : null;
    }

    protected static void search() {
        String confContent = controller.configIdField.getText();
        final String configId = (confContent.length() > 0) ? confContent : null;

        final ToolType toolType = controller.toolTypeBox.getValue();

        final DateTime startDate = localDateToDateTime(controller.startPicker.getValue(), false);
        final DateTime endDate = localDateToDateTime(controller.endPicker.getValue(), true);

        final Integer amount = controller.checkAmountInput();

        controller.indicator.setVisible(true);

        Thread searchThread = new Thread(new Runnable() {

            @Override
            public void run() {
                List<TrialData> result = Main.dataBaseClient.trials.getList(
                        configId, toolType, startDate, endDate, amount);

                Platform.runLater(() -> {
                    controller.indicator.setVisible(false);
                    controller.resultTable.getItems().clear();
                    controller.resultTable.getItems().addAll(result);
                });
            }
        });

        searchThread.start();
    }

    private static DateTime localDateToDateTime(java.time.LocalDate localDate, boolean endOfDay) {
        if (localDate == null) {
            return null;
        }
        // Korrigieren der Zeit um einen Tag, dadurch Ende des Tages nach Aufruf von atStartOfDay()
        localDate = endOfDay ? localDate.plusDays(1) : localDate;
        LocalDateTime localStart = localDate.atStartOfDay();
        long instant = localStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return new org.joda.time.LocalDateTime(instant).toDateTime();
    }

    private static BorderPane initialize() {
        InitResult ir = initialize(TrialSelector.class.getResource("TrialSelector.fxml"));
        root = (BorderPane) ir.root;
        controller = (TrialSelectorController) ir.controller;

        controller.initAmountBox(1, 100, 50);
        controller.initToolTypeBox();
        controller.initResultTable();
        controller.initEndPicker();

        return root;
    }
}
