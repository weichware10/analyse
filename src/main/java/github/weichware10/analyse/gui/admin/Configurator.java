package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.general.MainMenuBar;
import github.weichware10.util.gui.AbsScene;
import javafx.scene.Parent;

/**
 * Konfigurator zum Anzeigen und Erstellen von Konfigurationen.
 */
public class Configurator extends AbsScene {
    private static Parent root;

    /**
     * startet den Konfigurator.
     */
    public static void start() {
        root = start(Main.primaryStage,
                Configurator.class.getResource("Configurator.fxml"),
                root,
                null,
                "Analyse - Konfigurator",
                MainMenuBar.getMenuBar(),
                null,
                null,
                null).root;
    }
}
