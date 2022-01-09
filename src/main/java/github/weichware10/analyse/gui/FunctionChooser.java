package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Screen;

/**
 * Der Auswahlbildschirm der App.
 */
public class FunctionChooser extends AbsScene {
    private static Parent root;

    /**
     * Startet die App.
     */
    public static void start() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = start(Main.primaryStage,
                Login.class.getResource("FunctionChooser.fxml"),
                root,
                null,
                "Analyse",
                MainMenuBar.getMenuBar(),
                (int) screenBounds.getWidth() / 2,
                (int) screenBounds.getHeight() / 2).root;
    }
}
