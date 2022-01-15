package github.weichware10.analyse;

import github.weichware10.analyse.gui.admin.Configurator;
import github.weichware10.analyse.gui.general.Login;
import github.weichware10.analyse.gui.util.Log;
import github.weichware10.util.Logger;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    public static final int MINHEIGHT = 800;

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
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Log.start();
        Main.primaryStage = primaryStage;
        Login.start();
        primaryStage.getIcons().add(new Image("app-icon.png"));
        // FENSTERGRÖẞE
        primaryStage.setMinWidth(MINWIDTH);
        primaryStage.setMinHeight(MINHEIGHT);
        primaryStage.show();
    }
}
