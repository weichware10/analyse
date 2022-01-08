package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsSceneController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller für {@link Login}.
 */
public class LoginController extends AbsSceneController {

    @FXML
    private TextField benutzernameId;
    @FXML
    private PasswordField passwortId;
    @FXML
    private TextField schemaId;
    @FXML
    private Text warnText;
    @FXML
    private TextArea errorText;
    @FXML
    private ProgressIndicator indicator;

    @Override
    protected void initialize() {
        assert benutzernameId != null
                : "fx:id=\"benutzernameID\" not injected: check 'LoginWindow.fxml'.";
        assert passwortId != null
                : "fx:id=\"passwortID\" not injected: check 'LoginWindow.fxml'.";
        assert schemaId != null
                : "fx:id=\"schemaID\" not injected: check 'LoginWindow.fxml'.";
        assert warnText != null
                : "fx:id=\"warnText\" not injected: check 'LoginWindow.fxml'.";
        assert errorText != null
                        : "fx:id=\"errorText\" not injected: check 'LoginWindow.fxml'.";
        assert indicator != null
                        : "fx:id=\"loadingIndicator\" not injected: check 'Login.fxml'.";
    }

    @FXML
    private void loginToDatabase() {
        Login.connectToDatabase(benutzernameId.getText(),
                passwortId.getText(),
                schemaId.getText(),
                warnText,
                errorText,
                indicator);
    }

    @FXML
    private void closeProgramm() {
        Main.primaryStage.close();
    }
}
