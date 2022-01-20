package github.weichware10.analyse.gui.admin;

import github.weichware10.util.gui.AbsScene;
import github.weichware10.util.gui.AbsScene.InitResult;
import java.util.List;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

/**
 * Dialog, welcher eine String-Liste anzeigen / setzen kann.
 */
public class StringsDialog extends Dialog<List<String>> {

    private BorderPane root;
    private StringsDialogController controller;

    /**
     * Erstellt einen neuen Dialog.
     *
     * @param strings - Die String-Liste. Auf {@code null} setzen zum Erstellen einer neuen Liste.
     */
    public StringsDialog(List<String> strings, boolean editable) {
        super();
        InitResult ir = AbsScene.initialize(getClass().getResource("StringsDialog.fxml"));
        root = (BorderPane) ir.root;
        controller = (StringsDialogController) ir.controller;

        if (editable) {
            controller.titleLabel.setText("Bearbeiten von Strings");
            setTitle("Bearbeiten von Strings");
            controller.stringsArea.setText(String.join("\n", strings));
        } else {
            controller.titleLabel.setText("Anzeigen von Strings");
            setTitle("Anzeigen von Strings");
            controller.stringsArea.setText(String.join("\n", strings));
            controller.stringsArea.setEditable(false);
        }

        setResultConverter((buttonType) -> convertResult(buttonType));
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);

    }

    private List<String> convertResult(ButtonType buttonType) {
        if (buttonType == ButtonType.FINISH) {
            return controller.strings;
        }
        return null;
    }
}
