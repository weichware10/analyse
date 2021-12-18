package github.weichware10.analyse;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Analyse GUI.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Analyse");
        primaryStage.getIcons().add(new Image("app-icon.png"));
        //Zeigt das Fenster
        primaryStage.show();
    }
}
