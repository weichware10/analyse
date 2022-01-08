package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
import github.weichware10.util.Logger;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.Parent;

/**
 * Der Hauptbildschirm der App.
 */
public class Login extends AbsScene {
    public static DataBaseClient dataBaseClient;

    private static Parent root;

    /**
     * Startet die App.
     */
    public static void start() {
        root = start(Main.primaryStage,
                Login.class.getResource("Login.fxml"),
                root,
                "Analyse",
                MainMenuBar.getMenuBar(),
                300,
                300);
    }

    /**
     * Stellt die Verbindung zur Datenbank her.
     */
    public static void connectToDatabase(String username, String password, String schema) {
        try {
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("DB_URL");
            dataBaseClient = new DataBaseClient(url, username, password, schema);
        } catch (IllegalArgumentException e) {
            Logger.error("error when loading env", e);
            // auf null setzen, falls die Verbindung vorher angepasst wurde und dies
            // gewünscht ist
            // zurücksetzen auf dataBaseClient-freien Zustand
            dataBaseClient = null;
        }
    }

}
