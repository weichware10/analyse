package github.weichware10.analyse.gui;

import github.weichware10.analyse.gui.util.AbsScene;
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
            root = (MenuBar) initialize(MainMenuBar.class.getResource("MainMenuBar.fxml"));
        }
        return root;
    }
}
