package github.weichware10.analyse;

import github.weichware10.analyse.gui.general.Login;
import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.db.DataBaseClient;
import github.weichware10.util.gui.Log;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.joda.time.DateTime;

/**
 * Analyse GUI.
 */
public class Main extends Application {

    public static Stage primaryStage;
    public static DataBaseClient dataBaseClient;
    public static String databaseUrl;
    public static final int MINWIDTH = 800;
    public static final int MINHEIGHT = 850;

    /**
     * Einstiegspunkt der Analyse.
     *
     * @param args - args
     */
    public static void main(String[] args) {
        // in Datei und Konsole loggen
        String logfile = String.format(
                Dotenv.load().get("LOGS") + "/%s.log", DateTime.now().toString("yMMdd-HHmmss"));
        Logger.setLogfile(logfile);
        // Temp Ordner löschen
        Runtime.getRuntime().addShutdownHook(Files.deleteTempDir());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Log.start("app-icon.png");
        Main.primaryStage = primaryStage;
        Login.start();
        primaryStage.getIcons().add(new Image("app-icon.png"));
        // FENSTERGRÖẞE
        primaryStage.setMinWidth(MINWIDTH);
        primaryStage.setMinHeight(MINHEIGHT);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        primaryStage.setWidth(Math.max((int) screenBounds.getWidth() / 2, Main.MINWIDTH));
        primaryStage.setHeight(Math.max((int) screenBounds.getHeight() / 2, Main.MINHEIGHT));
        primaryStage.show();
    }
}
