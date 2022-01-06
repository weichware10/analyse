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
     * URL der FXML-Datei.
     */
    protected static URL fxml;
    private static Scene scene = null;
    private static Parent root = null;
    private static boolean initialized = false;

    /**
     * lädt die Szene intern und gibt die Instanz der Controller-Klasse zurück.
     * Falls diese benötigt wird, kann also manuell initialize() aufgerufen werden.
     * Das sollte jedoch in der Regel nicht nötig sein.
     *
     * @param fxml - URL der FXML-Datei
     * @return das Controller-Objekt.
     */
    protected static AbsSceneController initialize(URL fxml) {

        FXMLLoader loader = new FXMLLoader(fxml);

        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading " + fxml, e, true);
            System.exit(-1);
        }

        scene = new Scene(root);

        initialized = true;
        return loader.getController();
    }

    /**
     * Setzt die MenuBar, falls das in initialize geladene root-Objekt BorderPane ist.
     *
     * @param menuBar - die zu setzende Menüleiste
     */
    public static void setMenuBar(MenuBar menuBar) {
        if (root != null && root.getClass().isInstance(BorderPane.class)) {
            BorderPane borderPane = (BorderPane) root;
            borderPane.setTop(menuBar);
        }
    }

    /**
     * Zeigt die Szene an.
     *
     * @param primaryStage - das Hauptfenster
     */
    public static void start(Stage primaryStage, URL fxml, String title, MenuBar menuBar,
            Integer width, Integer height) {
        if (primaryStage == null || title == null || fxml == null) {
            throw new NullPointerException(
                    String.format("Stage primaryStage (%s), ",
                            (primaryStage != null) ? primaryStage.toString() : "null")
                    + String.format("String title (%s) ",
                            (title != null) ? title : "null")
                    + String.format("and URL fxml (%s) are required",
                            (fxml != null) ? fxml.toString() : "null"));
        }
        // aus FXML laden
        if (!initialized) {
            initialize(fxml);
        }
        // Menüleiste setzen
        if (menuBar != null) {
            setMenuBar(menuBar);
        }
        // Größe einstellen
        if (width != null && height != null) {
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
        }
        // auf primaryStage setzen
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
    }
}
