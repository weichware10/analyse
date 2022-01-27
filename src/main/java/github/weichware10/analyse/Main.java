package github.weichware10.analyse;

import github.weichware10.analyse.gui.admin.Configurator;
import github.weichware10.analyse.gui.general.Login;
import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.db.DataBaseClient;
import github.weichware10.util.gui.Log;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        // delete temp dir
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
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, e -> keyBindings(e));
        primaryStage.sceneProperty().addListener((o) -> refresh());
    }

    private static void refresh() {
        Logger.debug("refreshing");
        ObservableList<Screen> screens = Screen.getScreensForRectangle(
                primaryStage.getX(),
                primaryStage.getY(),
                primaryStage.getWidth(),
                primaryStage.getHeight());

        List<Screen> weirdDpiScreens = screens.stream().filter(
                p -> (p.getOutputScaleX() * p.getOutputScaleY()) != 1).collect(Collectors.toList());

        if (weirdDpiScreens.size() == 0) {
            Logger.debug("size return");
            return;
        }

        boolean maximized = primaryStage.isMaximized();
        Runnable r;
        if (maximized) {
            primaryStage.setMaximized(false);
            r = () -> Platform.runLater(() -> primaryStage.setMaximized(true));
        } else {
            // primaryStage.setWidth(primaryStage.getWidth() + 1);
            primaryStage.hide();
            // r = () -> Platform.runLater(() ->
            //         primaryStage.setWidth(primaryStage.getWidth() - 1));
            r = () -> Platform.runLater(() -> primaryStage.show());
        }
        new Thread(r).start();
    }

    private void keyBindings(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.K) && keyEvent.isControlDown()) {
            Configurator.start();
        }
    }
}
