package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.gui.general.FunctionChooser;
import github.weichware10.util.Logger;
import github.weichware10.util.gui.AbsSceneController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller für {@link TrialCreator}.
 */
public class TrialCreatorController extends AbsSceneController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    protected TextField configIdField;
    @FXML
    protected ComboBox<Integer> countBox;
    @FXML
    private Button saveButton;
    @FXML
    protected TextArea trialIdArea;
    @FXML
    protected Button trialsCreateButton;

    @FXML
    private void startFunctionChooser() {
        Logger.info("trialcreator:content Starting Function Chooser");
        FunctionChooser.start();
    }

    @FXML
    private void createTrials() {
        Logger.info("trialcreator:content Creating trials");
        TrialCreator.createTrials();
    }

    @FXML
    private void saveToTxt() {
        Logger.info("trialcreator:content Saving to text file");
        TrialCreator.saveToTxt();
    }

    @Override
    protected void initialize() {
        assert configIdField != null
                : "fx:id=\"configIdField\" not injected: check 'TrialCreator.fxml'.";
        assert countBox != null
                : "fx:id=\"countBox\" not injected: check 'TrialCreator.fxml'.";
        assert saveButton != null
                : "fx:id=\"saveButton\" not injected: check 'TrialCreator.fxml'.";
        assert trialIdArea != null
                : "fx:id=\"trialIdArea\" not injected: check 'TrialCreator.fxml'.";
        assert trialsCreateButton != null
                : "fx:id=\"trialsCreateButton\" not injected: check 'TrialCreator.fxml'.";

        for (int i = 0; i < 10; i++) {
            countBox.getItems().add((int) Math.pow(2, i));
        }
        countBox.setValue(1);

        trialIdArea.textProperty().addListener(
                (o, s, newValue) -> saveButton.setDisable(newValue.length() == 0));
        configIdField.textProperty().addListener(
                (o) -> new Thread(TrialCreator.configAvailabiltyChecker).start());
    }

}
