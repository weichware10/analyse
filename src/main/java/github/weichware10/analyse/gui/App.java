package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;

/**
 * Der Hauptbildschirm der App.
 */
public class App extends AbsScene {

    public static void start() {
        start(Main.primaryStage,  App.class.getResource("App.fxml"), "Analyse",  null, 300, 300);
    }
}
