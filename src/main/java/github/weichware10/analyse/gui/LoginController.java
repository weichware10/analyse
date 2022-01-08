package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsSceneController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    @Override
    protected void initialize() {
        assert benutzernameId != null
        : "fx:id=\"benutzernameID\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwortId != null
        : "fx:id=\"passwortID\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert schemaId != null
        : "fx:id=\"schemaID\" was not injected: check your FXML file 'LoginWindow.fxml'.";
    }

    @FXML
    private void loginToDatabase() {
        Login.connectToDatabase(benutzernameId.getText(),
                                      passwortId.getText(),
                                      schemaId.getText());
    }

    @FXML
    private void closeProgramm() {
        Main.primaryStage.close();
    }

}
