package github.weichware10.analyse.gui.general;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.admin.TrialCreator;
import github.weichware10.analyse.gui.analyse.Analyzer;
import github.weichware10.analyse.gui.util.AbsSceneController;
import github.weichware10.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller für {@link FunctionChooser}.
 */
public class FunctionChooserController extends AbsSceneController {

    @FXML
    private Button trialCreatorButton;

    @FXML
    void logOut() {
        Logger.info("functionchooser:content Logging out");
        Login.logOut();
    }

    @FXML
    void startAnalyzer(ActionEvent event) {
        Logger.info("functionchooser:content Logging out");
        Analyzer.start();
    }

    @FXML
    void startTrialCreator(ActionEvent event) {
        Logger.info("functionchooser:content Starting TrialCreator");
        TrialCreator.start();
    }

    @Override
    protected void initialize() {
        assert trialCreatorButton != null
                : "fx:id=\"adminButton\" not injected: check 'FunctionChooser.fxml'.";
        trialCreatorButton.setDisable(!Main.dataBaseClient.permissions.isAuthor);
    }
}
