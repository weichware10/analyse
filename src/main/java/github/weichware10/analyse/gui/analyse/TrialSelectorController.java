package github.weichware10.analyse.gui.analyse;

import github.weichware10.util.ToolType;
import github.weichware10.util.data.TrialData;
import github.weichware10.util.gui.AbsSceneController;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joda.time.DateTime;

/**
 * Kontroller für {@link TrialSelector}.
 */
public class TrialSelectorController extends AbsSceneController {

    private int minAmount = 1;
    private int maxAmount = 100;
    private int defaultAmount = 50;

    @FXML
    protected ComboBox<Integer> amountBox;
    @FXML
    protected TextField configIdField;
    @FXML
    protected DatePicker endPicker;
    @FXML
    protected TableView<TrialData> resultTable;
    @FXML
    protected DatePicker startPicker;
    @FXML
    protected ComboBox<ToolType> toolTypeBox;
    @FXML
    protected Button searchButton;
    @FXML
    protected Label warnLabel;
    @FXML
    protected ProgressIndicator indicator;
    @FXML
    protected TextField trialIdField;
    @FXML
    protected Label jsonLabel;

    @FXML
    protected Integer checkAmountInput() {
        Integer value;
        try {
            value = Integer.valueOf(amountBox.getEditor().getText());
        } catch (NumberFormatException e) {
            value = null;
        }

        if (value == null) {
            value = defaultAmount;
        } else if (value > maxAmount) {
            value = maxAmount;
        } else if (value < minAmount) {
            value = minAmount;
        }

        amountBox.setValue(value);
        return value;
    }

    @FXML
    private void search() {
        TrialSelector.search();
    }

    @FXML
    private void reset() {
        TrialSelector.reset();
    }

    protected void setWarn(String warn) {
        warnLabel.setText(warn);
    }

    protected void initEndPicker() {
        endPicker.setValue(LocalDate.now());
    }

    protected void initResultTable() {
        TableColumn<TrialData, String> trialIdColumn = new TableColumn<>("Versuchs-ID");
        trialIdColumn.setCellValueFactory(new PropertyValueFactory<>("trialId"));
        resultTable.getColumns().add(trialIdColumn);
        TableColumn<TrialData, String> configIdColumn = new TableColumn<>("Konfigurations-ID");
        configIdColumn.setCellValueFactory(new PropertyValueFactory<>("configId"));
        resultTable.getColumns().add(configIdColumn);
        TableColumn<TrialData, String> toolTypeColumn = new TableColumn<>("Tool-Typ");
        toolTypeColumn.setCellValueFactory(new PropertyValueFactory<>("toolType"));
        resultTable.getColumns().add(toolTypeColumn);
        TableColumn<TrialData, DateTime> startTimeColumn = new TableColumn<>("Zeit");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        resultTable.getColumns().add(startTimeColumn);
        TableColumn<TrialData, String> answerColumn = new TableColumn<>("Antwort");
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        resultTable.getColumns().add(answerColumn);
    }

    protected void initAmountBox(int minAmount, int maxAmount, int defaultAmount) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.defaultAmount = defaultAmount;
        for (Integer i = minAmount; i <= maxAmount; i++) {
            amountBox.getItems().add(i);
        }
        initAmountBox();
    }

    protected void initAmountBox() {
        amountBox.setValue(defaultAmount);
    }

    protected void initToolTypeBox() {
        for (ToolType toolType : ToolType.values()) {
            toolTypeBox.getItems().add(toolType);
        }
        toolTypeBox.getItems().add(null);
    }

    @Override
    protected void initialize() {
        assert amountBox != null
                : "fx:id=\"amountBox\" not injected: check 'TrialSelector.fxml'.";
        assert configIdField != null
                : "fx:id=\"configIdField\" not injected: check 'TrialSelector.fxml'.";
        assert endPicker != null
                : "fx:id=\"endPicker\" not injected: check 'TrialSelector.fxml'.";
        assert resultTable != null
                : "fx:id=\"resultTable\" not injected: check 'TrialSelector.fxml'.";
        assert startPicker != null
                : "fx:id=\"startPicker\" not injected: check 'TrialSelector.fxml'.";
        assert toolTypeBox != null
                : "fx:id=\"toolTypeBox\" not injected: check 'TrialSelector.fxml'.";
        assert searchButton != null
                : "fx:id=\"searchButton\" not injected: check 'TrialSelector.fxml'.";
        assert warnLabel != null
                : "fx:id=\"warnLabel\" not injected: check 'TrialSelector.fxml'.";
        assert indicator != null
                : "fx:id=\"indicator\" not injected: check 'TrialSelector.fxml'.";
        assert trialIdField != null
                : "fx:id=\"trialIdField\" not injected: check 'TrialSelector.fxml'.";
        assert jsonLabel != null
                : "fx:id=\"jsonLabel\" not injected: check 'TrialSelector.fxml'.";
    }

}
