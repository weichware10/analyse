package github.weichware10.analyse.gui.admin;

import github.weichware10.util.gui.AbsSceneController;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Controller für {@link StringsDialog}.
 */
public class StringsDialogController extends AbsSceneController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Label countLabel;
    @FXML
    protected TextArea stringsArea;
    @FXML
    protected Label titleLabel;

    protected List<String> strings = new ArrayList<>();

    @FXML
    @Override
    protected void initialize() {
        assert countLabel != null
                : "fx:id=\"countLabel\" not injected: check 'StringsDialog.fxml'.";
        assert stringsArea != null
                : "fx:id=\"stringsArea\" not injected: check 'StringsDialog.fxml'.";
        assert titleLabel != null
                : "fx:id=\"titleLabel\" not injected: check 'StringsDialog.fxml'.";
        stringsArea.textProperty().addListener((o) -> updateStrings());
    }

    private void updateStrings() {
        String text = stringsArea.getText();
        strings = Arrays.asList(text.split("\n"));
        countLabel.setText(String.format("Anzahl Strings: %d", strings.size()));
    }

}
