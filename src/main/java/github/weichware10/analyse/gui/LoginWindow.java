package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
import javafx.scene.Parent;

/**
 * Der Hauptbildschirm der App.
 */
public class LoginWindow extends AbsScene {

    private static Parent root;

    /**
     * Startet die App.
     */
    public static void start() {
        root = start(Main.primaryStage,
                LoginWindow.class.getResource("LoginWindow.fxml"),
                root,
                "Analyse",
                MainMenuBar.getMenuBar(),
                300,
                300);
    }

}
