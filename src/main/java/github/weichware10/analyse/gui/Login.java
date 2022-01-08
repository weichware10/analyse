package github.weichware10.analyse.gui;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
import github.weichware10.util.Logger;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Screen;

/**
 * Der Loginbildschirm der App.
 */
public class Login extends AbsScene {
    public static DataBaseClient dataBaseClient;
    private static SimpleBooleanProperty connection = new SimpleBooleanProperty(false);
    public static ObservableBooleanValue hasConnection = connection;
    public static String databaseUrl;

    private static Parent root;

    /**
     * Startet die App.
     */
    public static void start() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = start(Main.primaryStage,
                Login.class.getResource("Login.fxml"),
                root,
                "Analyse",
                MainMenuBar.getMenuBar(),
                (int) screenBounds.getWidth() / 2,
                (int) screenBounds.getHeight() / 2);
    }

    /**
     * Loggt den Nutzer aus und kehrt zum Login-Bildschirm zurück.
     */
    public static void logOut() {
        dataBaseClient = null;
        connection.set(false);
        start();
    }

    /**
     * Stellt die Verbindung zur Datenbank her.
     */
    public static void connectToDatabase(String username, String password, String schema,
            Text warnText, TextArea errorText) {
        if (databaseUrl == null) {
            Dotenv dotenv = Dotenv.load();
            databaseUrl = dotenv.get("DB_URL");
        }
        try {
            dataBaseClient = new DataBaseClient(databaseUrl, username, password, schema);
            connection.set(true);
            warnText.setVisible(false);
            errorText.setVisible(false);
            errorText.setText("");
        } catch (IllegalArgumentException e) {
            Logger.error("error when loading env", e);
            dataBaseClient = null;
            connection.set(false);
            warnText.setVisible(true);
            errorText.setVisible(true);
            warnText.setText("Couldn't log into database");
            errorText.setText(e.getMessage());
            errorText.setPrefHeight(50);
            errorText.setPrefWidth(Region.USE_COMPUTED_SIZE);
        }
        if (dataBaseClient != null) {
            FunctionChooser.start();
        }
    }

}
