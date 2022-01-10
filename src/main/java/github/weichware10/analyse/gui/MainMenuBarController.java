package github.weichware10.analyse.gui;

import github.weichware10.analyse.gui.util.AbsSceneController;
import github.weichware10.analyse.gui.util.Log;
import github.weichware10.util.Logger;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * Controller für {@link MainMenuBar}.
 */
public class MainMenuBarController extends AbsSceneController {
    @FXML
    private MenuItem logOutMenu;
    @FXML
    private MenuItem logsMenu;

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
    protected void resetDataBaseUrl() {
        MainMenuBar.resetDataBaseUrl();
    }

    @FXML
    protected void setDataBaseUrl() {
        MainMenuBar.changeDataBaseUrl();
    }

    @FXML
    void toggleLogs() {
        Logger.info("app:menu Toggling logs");
        if (Log.visible.get()) {
            Log.hide();
        } else {
            Log.show();
        }
    }

    void setLogText(ObservableBooleanValue visible) {
        if (visible.get()) {
            logsMenu.setText("hide logs");
        } else {
            logsMenu.setText("show logs");
        }
    }

    @Override
    protected void initialize() {
        assert logOutMenu != null
                : "fx:id=\"logOutMenu\" not injected: check 'MainMenuBar.fxml'.";
        assert logsMenu != null
                : "fx:id=\"logsMenu\" not injected: check 'MainMenuBar.fxml'.";

        Login.hasConnection.addListener(obs -> setLogOutDisable((ObservableBooleanValue) obs));
        Log.visible.addListener(obs -> setLogText((ObservableBooleanValue) obs));
    }
}
