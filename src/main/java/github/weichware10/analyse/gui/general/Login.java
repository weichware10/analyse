package github.weichware10.analyse.gui.general;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.gui.util.AbsScene;
import github.weichware10.util.Logger;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Screen;

/**
 * Der Loginbildschirm der App.
 */
public class Login extends AbsScene {
    private static SimpleBooleanProperty connection = new SimpleBooleanProperty(false);
    public static ObservableBooleanValue hasConnection = connection;

    private static Parent root;

    /**
     * Startet die App.
     */
    public static void start() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = start(Main.primaryStage,
                Login.class.getResource("Login.fxml"),
                root,
                null,
                "Analyse",
                MainMenuBar.getMenuBar(),
                Math.max((int) screenBounds.getWidth() / 2, Main.MINWIDTH),
                Math.max((int) screenBounds.getHeight() / 2, Main.MINHEIGHT)).root;
    }

    /**
     * Loggt den Nutzer aus und kehrt zum Login-Bildschirm zurück.
     */
    public static void logOut() {
        Main.dataBaseClient = null;
        connection.set(false);
        start();
    }

    /**
     * Stellt die Verbindung zur Datenbank her.
     */
    public static void connectToDatabase(String username, String password, String schema,
            Text warnText, TextArea errorText, ProgressIndicator indicator) {
        setError(warnText, errorText, indicator, true, null);

        Thread loginThread = new Thread(new Runnable() {

            @Override
            public void run() {
                if (Main.databaseUrl == null) {
                    Dotenv dotenv = Dotenv.load();
                    Main.databaseUrl = dotenv.get("DB_URL");
                }
                try {
                    Main.dataBaseClient = new DataBaseClient(
                            Main.databaseUrl, username, password, schema);
                    connection.set(true);
                    Platform.runLater(() -> setError(warnText, errorText, indicator, false, null));
                } catch (IllegalArgumentException e) {
                    Logger.error("login:content error when loggin in", e);
                    Main.dataBaseClient = null;
                    connection.set(false);
                    Platform.runLater(() -> setError(warnText, errorText, indicator, false, e));
                }
                if (Main.dataBaseClient != null) {
                    Platform.runLater(() -> FunctionChooser.start());
                }
            }
        });
        loginThread.start();
    }

    private static void setError(Text warnText, TextArea errorText,
            ProgressIndicator indicator, boolean loading, Exception e) {
        if (e == null) {
            warnText.setVisible(false);
            errorText.setVisible(false);
            errorText.setText("");
            if (indicator != null) {
                indicator.setVisible(loading);
            }
        } else {
            warnText.setVisible(true);
            errorText.setVisible(true);
            warnText.setText("Couldn't log into database");
            errorText.setText(e.getMessage());
            errorText.setPrefHeight(50);
            errorText.setPrefWidth(Region.USE_COMPUTED_SIZE);
            indicator.setVisible(loading);
        }
    }

}
