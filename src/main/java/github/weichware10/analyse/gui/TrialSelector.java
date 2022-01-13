package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
import github.weichware10.util.ToolType;
import github.weichware10.util.data.TrialData;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.joda.time.DateTime;

/**
 * Auswahldialog für Trial.
 */
public class TrialSelector extends AbsScene {

    private static BorderPane root;
    private static TrialSelectorController controller;
    private static String trialId;
    private static TrialData trialData;
    private static String fixedConfigId = null;
    private static Dialog<Void> selectorDialog;
    private static Button selectButton;

    /**
     * Auswahldialog für Trial.
     */
    public static TrialData getTrialData(String fixedConfigId) {
        selectorDialog = new Dialog<>();
        ButtonType select = new ButtonType("Trial auswählen", ButtonData.APPLY);
        ButtonType json = new ButtonType("load from JSON", ButtonData.APPLY);
        selectorDialog.getDialogPane().getButtonTypes().addAll(json, select, ButtonType.CANCEL);

        selectorDialog.setTitle("Auswahl Trial");

        Stage stage = (Stage) selectorDialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image(TrialSelector.class.getResource("app-icon.png").toString()));

        selectorDialog.getDialogPane().setContent((root != null) ? root : initialize());

        if (fixedConfigId != null) {
            controller.jsonLabel.setText("""
                    Beim Laden von JSON-Dateien kann nicht überprüft werden,
                    ob der Vergleich Sinn ergibt.""");
            if (Main.dataBaseClient.configurations.getAvailability(fixedConfigId)) {
                TrialSelector.fixedConfigId = fixedConfigId;
            } else {
                TrialSelector.fixedConfigId = null;
            }
        } else {
            controller.jsonLabel.setText("");
        }

        reset();

        selectButton = (Button) selectorDialog.getDialogPane().lookupButton(select);
        Button jsonButton = (Button) selectorDialog.getDialogPane().lookupButton(json);

        selectButton.addEventFilter(ActionEvent.ACTION, e -> selectEventFilter(e));
        jsonButton.addEventFilter(ActionEvent.ACTION, e -> loadFromJson(e));

        selectorDialog.showAndWait();

        return trialData;
    }

    protected static void search() {
        if (controller.trialIdField.getText().length() == 0) {
            searchForList();
        } else {
            searchForTrial();
        }
    }

    protected static void searchForList() {
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

    protected static void searchForTrial() {
        final String trialId = controller.trialIdField.getText();

        controller.indicator.setVisible(true);

        Thread searchThread = new Thread(new Runnable() {

            @Override
            public void run() {
                List<TrialData> result = new ArrayList<>();
                result.add(Main.dataBaseClient.trials.getTrial(trialId));

                Platform.runLater(() -> {
                    controller.indicator.setVisible(false);
                    controller.resultTable.getItems().clear();
                    controller.resultTable.getItems().addAll(result);
                });
            }
        });

        searchThread.start();
    }

    protected static void loadFromJson(ActionEvent jsonEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("JSON Config auswählen");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("JSON Dateien", "*.json"));
        File jsonFile = fileChooser.showOpenDialog(
                selectorDialog.getDialogPane().getScene().getWindow());
        String location = (jsonFile != null) ? jsonFile.getAbsolutePath() : null;

        if (location != null) {
            trialData = TrialData.fromJson(location);
        } else {
            jsonEvent.consume();
        }
        if (trialData == null) {
            controller.warnLabel.setText("Could not load from JSON");
            jsonEvent.consume();
        }
    }

    protected static void reset() {
        controller.resultTable.getItems().clear();
        controller.toolTypeBox.setValue(null);
        controller.initAmountBox();
        controller.startPicker.setValue(null);
        controller.initEndPicker();
        controller.trialIdField.setText("");
        if (fixedConfigId == null) {
            controller.configIdField.setText("");
            controller.configIdField.setDisable(false);
            controller.warnLabel.setText("");
        } else {
            controller.configIdField.setText(fixedConfigId);
            controller.configIdField.setDisable(true);
            controller.warnLabel.setText("");
        }
        controller.warnLabel.setText("");
        search();
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

    private static void selectEventFilter(ActionEvent selectEvent) {
        TrialData selected = controller.resultTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            controller.setWarn("Bitte wählen Sie ein Trial aus.");
            selectEvent.consume();
            return;
        }
        trialId = selected.trialId;
        trialData = (trialId != null) ? Main.dataBaseClient.trials.getTrial(trialId) : null;
    }
}
