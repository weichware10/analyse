package github.weichware10.analyse;

import github.weichware10.analyse.gui.Login;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Analyse GUI.
 */
public class Main extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Login.start();
        primaryStage.getIcons().add(new Image("app-icon.png"));
        primaryStage.show();
    }
}
