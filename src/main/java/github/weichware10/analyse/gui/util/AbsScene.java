package github.weichware10.analyse.gui.util;

import github.weichware10.util.Logger;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Abstrakte Klasse, von der alle Szenen erben. Diese Klasse lädt vorallem FXML-Dateien.
 */
public abstract class AbsScene {

    /**
     * lädt die Szene intern und gibt die root-Instanz zurück.
     *
     * @param fxml - URL der FXML-Datei
     * @return das Controller-Objekt.
     */
    protected static Parent initialize(URL fxml) {

        FXMLLoader loader = new FXMLLoader(fxml);

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading " + fxml, e, true);
            System.exit(-1);
        }

        return root;
    }

    /**
     * Setzt die MenuBar, falls das in initialize geladene root-Objekt BorderPane ist.
     *
     * @param menuBar - die zu setzende Menüleiste
     */
    public static void setMenuBar(MenuBar menuBar, Parent root) {
        if (root != null && root instanceof BorderPane) {
            BorderPane borderPane = (BorderPane) root;
            borderPane.setTop(menuBar);
        }
    }

    /**
     * Zeigt die Szene an.
     *
     * @param primaryStage - das Hauptfenster
     */
    public static Parent start(Stage primaryStage, URL fxml, Parent root,
            String title, MenuBar menuBar,
            Integer width, Integer height) {
        if (primaryStage == null || title == null || fxml == null) {
            throw new NullPointerException(
                    String.format("Stage primaryStage (%s), ",
                            (primaryStage != null) ? primaryStage.toString() : "null")
                            + String.format("String title (%s), ",
                                    (title != null) ? title : "null")
                            + String.format("and URL fxml (%s) are required",
                                    (fxml != null) ? fxml.toString() : "null"));
        }
        if (root == null) {
            root = initialize(fxml);
        }
        // Menüleiste setzen
        if (menuBar != null) {
            setMenuBar(menuBar, root);
        }
        // Größe einstellen
        if (width != null && height != null) {
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
        }
        // auf primaryStage setzen
        Scene existingScene = root.getScene();
        if (existingScene != null) {
            primaryStage.setScene(existingScene);
        } else {
            primaryStage.setScene(new Scene(root));
        }
        primaryStage.setTitle(title);
        return root;
    }
}
