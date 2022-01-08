package github.weichware10.analyse;

import github.weichware10.analyse.gui.Login;
import github.weichware10.analyse.gui.util.Log;
import github.weichware10.util.Logger;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.joda.time.DateTime;

/**
 * Analyse GUI.
 */
public class Main extends Application {

    public static Stage primaryStage;

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
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }
}
