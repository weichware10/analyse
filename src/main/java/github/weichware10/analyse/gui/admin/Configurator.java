package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.general.MainMenuBar;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.gui.AbsScene;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

/**
 * Konfigurator zum Anzeigen und Erstellen von Konfigurationen.
 */
public class Configurator extends AbsScene {
    private static Parent root;
    protected static SimpleObjectProperty<Mode> mode = new SimpleObjectProperty<>(Mode.NEW);
    protected static String configId = null;

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

    private static void fillGui(Configuration configuration) {
        ;
    }

    /**
     * mode of configurator.
     */
    protected enum Mode {
        JSONVIEW, JSONEDIT, DBVIEW, DBEDIT, NEW
    }
}
