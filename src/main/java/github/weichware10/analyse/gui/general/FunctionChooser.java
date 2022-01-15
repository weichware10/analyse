package github.weichware10.analyse.gui.general;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
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
                null).root;
    }
}
