package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.Main;
import github.weichware10.util.Logger;
import github.weichware10.util.gui.AbsScene;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Interface zum Erstellen von Trials (auf Basis von configIds).
 */
public class TrialCreator extends AbsScene {
    private static Parent root;
    private static TrialCreatorController controller;

    /**
     * Startet den TrialCreator.
     */
    public static void start() {
        InitResult ir = start(Main.primaryStage,
                TrialCreator.class.getResource("TrialCreator.fxml"),
                root,
                controller,
                "Analyse - Trialerstellung",
                null,
                null,
                null,
                null);
        root = ir.root;
        controller = (TrialCreatorController) ir.controller;
    }

    protected static void createTrials() {
        final String configId = controller.configIdField.getText();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(() -> controller.trialsCreateButton.setDisable(true));
                List<String> trialIds = Main.dataBaseClient.trials.add(
                        configId, controller.countBox.getValue());
                Platform.runLater(
                        () -> controller.trialIdArea.setText(String.join("\n", trialIds)));
                Platform.runLater(
                        () -> controller.trialsCreateButton.setDisable(false));
            }
        }).start();
    }

    protected static void saveToTxt() {
        String content = controller.trialIdArea.getText();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("JSON Config Speicherort auswählen");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("Text Dateien",
                        "*.txt"));
        File txtFile = fileChooser.showSaveDialog(Main.primaryStage);

        if (txtFile != null) {
            FileWriter fw;
            try {
                fw = new FileWriter(txtFile.getAbsolutePath(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                Logger.error("An error occured while saving trialId List", e);
            }
        }
    }

    protected static Runnable configAvailabiltyChecker = new Runnable() {
        @Override
        public void run() {
            String configId = controller.configIdField.getText();
            boolean availability = Main.dataBaseClient.configurations.getAvailability(configId);
            Platform.runLater(() -> controller.trialsCreateButton.setDisable(!availability));
        }
    };
}
