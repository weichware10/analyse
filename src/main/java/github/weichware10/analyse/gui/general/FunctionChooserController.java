package github.weichware10.analyse.gui.general;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.admin.Configurator;
import github.weichware10.analyse.gui.admin.TrialCreator;
import github.weichware10.analyse.gui.analyse.Analyzer;
import github.weichware10.util.Logger;
import github.weichware10.util.gui.AbsSceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller für {@link FunctionChooser}.
 */
public class FunctionChooserController extends AbsSceneController {

    @FXML
    private Button trialCreatorButton;
    @FXML
    private Button configuratorButton;

    @FXML
    void logOut() {
        Logger.info("analyzer:content Resetting");
        Analyzer.reset();
        Logger.info("functionchooser:content Logging out");
        Login.logOut();
    }

    @FXML
    void startAnalyzer() {
        Logger.info("functionchooser:content Starting analzyzer");
        Analyzer.start();
    }

    @FXML
    void startConfigurator() {
        Logger.info("functionchooser:content Starting Configurator");
        Configurator.start();
    }

    @FXML
    void startTrialCreator() {
        Logger.info("functionchooser:content Starting TrialCreator");
        TrialCreator.start();
    }

    @Override
    protected void initialize() {
        assert trialCreatorButton != null
                : "fx:id=\"trialCreatorButton\" not injected: check 'FunctionChooser.fxml'.";
        assert configuratorButton != null
                : "fx:id=\"configuratorButton\" not injected: check 'FunctionChooser.fxml'.";
        trialCreatorButton.setDisable(!Main.dataBaseClient.permissions.isAuthor);
        configuratorButton.setDisable(!Main.dataBaseClient.permissions.isAuthor);
    }
}
