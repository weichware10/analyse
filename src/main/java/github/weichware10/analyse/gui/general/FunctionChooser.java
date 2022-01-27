package github.weichware10.analyse.gui.general;

import github.weichware10.analyse.Main;
import github.weichware10.util.gui.AbsScene;
import github.weichware10.util.gui.Window;
import javafx.application.Platform;
import javafx.scene.Parent;

/**
 * Der Auswahlbildschirm der App.
 */
public class FunctionChooser extends AbsScene {
    private static Parent root;

    /**
     * Startet die App.
     */
    public static void start() {
        root = start(Main.primaryStage,
                Login.class.getResource("FunctionChooser.fxml"),
                root,
                null,
                "Analyse",
                MainMenuBar.getMenuBar(),
                null,
                null,
                null).root;

        // Window.refresh(Main.primaryStage);
    }
}
