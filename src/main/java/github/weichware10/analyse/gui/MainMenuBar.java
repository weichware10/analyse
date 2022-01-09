package github.weichware10.analyse.gui;

import github.weichware10.analyse.gui.util.AbsScene;
import github.weichware10.util.Logger;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.control.MenuBar;

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
}
