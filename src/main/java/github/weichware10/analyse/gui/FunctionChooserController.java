package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
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
    private Button adminButton;


    @FXML
    void logOut() {
        Logger.info(":FunctionChooser Logging out");
        Login.logOut();
    }

    @FXML
    void startAnalyzer(ActionEvent event) {
        Analyzer.start();
    }

    @Override
    protected void initialize() {
        assert adminButton != null
        : "fx:id=\"adminButton\" was not injected: check your FXML file 'FunctionChooser.fxml'.";
        adminButton.setDisable(!Main.dataBaseClient.permissions.isAdmin);
    }
}
