package github.weichware10.analyse.gui;

import github.weichware10.analyse.gui.util.AbsSceneController;
import github.weichware10.util.Logger;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * Controller für {@link MainMenuBar}.
 */
public class MainMenuBarController extends AbsSceneController {
    @FXML
    private MenuItem databaseChange;

    @FXML
    private MenuItem databaseUrlReset;

    @FXML
    private MenuItem logOutMenu;

    @FXML
    void logOut() {
        Logger.info(":menubar Logging out");
        Login.logOut();
    }

    private void setLogOutDisable(ObservableBooleanValue obs) {
        logOutMenu.setDisable(!(obs.get()));
    }

    @FXML
    public void openDocumentation() {
        Logger.info(":menubar Opening Docs");
        MainMenuBar.openDocs();
    }

    @FXML
    protected static void resetDataBaseUrl() {
        Login.databaseUrl = null;
    }

    @FXML
    protected static void setDataBaseUrl() {
        ;
    }

    @Override
    protected void initialize() {
        assert databaseChange != null
                : "fx:id=\"databaseChange\" not injected: check 'MainMenuBar.fxml'.";
        assert databaseUrlReset != null
                : "fx:id=\"databaseUrlReset\" not injected: check 'MainMenuBar.fxml'.";
        assert logOutMenu != null
                : "fx:id=\"logOutMenu\" not injected: check 'MainMenuBar.fxml'.";

        Login.hasConnection.addListener(obs -> setLogOutDisable((ObservableBooleanValue) obs));
    }
}
