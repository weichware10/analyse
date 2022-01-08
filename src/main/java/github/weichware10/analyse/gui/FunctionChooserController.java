package github.weichware10.analyse.gui;

import github.weichware10.analyse.gui.util.AbsSceneController;
import github.weichware10.util.Logger;
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

    @Override
    protected void initialize() {
        assert adminButton != null
        : "fx:id=\"adminButton\" was not injected: check your FXML file 'FunctionChooser.fxml'.";
        adminButton.setDisable(!Login.dataBaseClient.permissions.isAdmin);
    }
}
