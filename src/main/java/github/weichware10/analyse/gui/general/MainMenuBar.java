package github.weichware10.analyse.gui.general;

import github.weichware10.analyse.Main;
import github.weichware10.util.Logger;
import github.weichware10.util.gui.AbsScene;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;

/**
 * Haupt-Menüleiste.
 */
public class MainMenuBar extends AbsScene {

    private static MenuBar root;

    /**
     * Initialisiert die MenuBar-Instanz, falls noch nicht vorhanden.
     *
     * @return die MenuBar-Instanz
     */
    public static MenuBar getMenuBar() {
        if (root == null) {
            root = (MenuBar) initialize(MainMenuBar.class.getResource("MainMenuBar.fxml")).root;
        }
        return root;
    }

    /**
     * öffnet die Dokumentation im Browser.
     */
    protected static void openDocs() {
        if (Desktop.isDesktopSupported()
                && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                //TODO: Dokumentation richtig setzt bei help drücken
                Desktop.getDesktop().browse(new URI("https://weichware10.github.io/dokumente/"));
            } catch (IOException | URISyntaxException e) {
                Logger.error("error occured while opening documentation", e);
            }
        }
    }

    protected static void changeDataBaseUrl() {
        TextInputDialog tid = new TextInputDialog();
        tid.setTitle("DATABASE URL CHANGE");
        tid.setHeaderText("DATABASE URL CHANGE");
        tid.setContentText("Please input a new URL");
        Optional<String> res = tid.showAndWait();
        if (res.isPresent() && res.get().length() > 0) {
            Main.databaseUrl = res.get();
        }
    }

    protected static void resetDataBaseUrl() {
        Main.databaseUrl = null;
    }
}
