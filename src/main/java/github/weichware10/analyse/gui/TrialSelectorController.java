package github.weichware10.analyse.gui;

import github.weichware10.analyse.gui.util.AbsSceneController;
import github.weichware10.util.ToolType;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Kontroller für {@link TrialSelector}.
 */
public class TrialSelectorController extends AbsSceneController {

    private final int minAmount = 1;
    private final int maxAmount = 100;

    @FXML
    private ComboBox<Integer> amountBox;
    @FXML
    private TextField configIdField;
    @FXML
    private DatePicker endPicker;
    @FXML
    private TableView<?> resultTable;
    @FXML
    private DatePicker startPicker;
    @FXML
    private ComboBox<ToolType> toolTypeBox;

    @FXML
    private void checkAmountInput() {
        Integer value;
        try {
            value = Integer.valueOf(amountBox.getEditor().getText());
        } catch (NumberFormatException e) {
            value = null;
        }
        if (value == null) {
            amountBox.setValue(minAmount);
        } else if (value > maxAmount) {
            amountBox.setValue(maxAmount);
        } else if (value < minAmount) {
            amountBox.setValue(minAmount);
        }
    }

    private void initAmountBox() {
        for (Integer i = minAmount; i <= maxAmount; i++) {
            amountBox.getItems().add(i);
        }
    }

    private void initToolTypeBox() {
        for (ToolType toolType : ToolType.values()) {
            toolTypeBox.getItems().add(toolType);
        }
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
        initAmountBox();
        initToolTypeBox();
    }

}
