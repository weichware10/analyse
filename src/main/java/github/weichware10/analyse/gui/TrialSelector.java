package github.weichware10.analyse.gui;

import github.weichware10.analyse.gui.util.AbsScene;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

/**
 * Auswahldialog für Trial.
 */
public class TrialSelector extends AbsScene {

    private static BorderPane root;

    /**
     * Auswahldialog für Trial.
     */
    public static String getTrialId() {

        Dialog<String> selectorDialog = new Dialog<>();
        selectorDialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

        if (root == null) {
            root = (BorderPane) initialize(TrialSelector.class.getResource("TrialSelector.fxml"));
        }
        selectorDialog.getDialogPane().setContent(root);

        Optional<String> optResult = selectorDialog.showAndWait();

        if (optResult.isPresent()) {
            return optResult.get();
        } else {
            return null;
        }
    }
}
