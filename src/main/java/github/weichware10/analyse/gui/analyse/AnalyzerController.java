package github.weichware10.analyse.gui.analyse;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.enums.AnalyseType;
import github.weichware10.analyse.gui.general.FunctionChooser;
import github.weichware10.util.Logger;
import github.weichware10.util.gui.AbsSceneController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Kontroller für {@link Analyzer}.
 */
public class AnalyzerController extends AbsSceneController {

    @FXML
    protected Button analyseButton;
    @FXML
    protected StackPane analysePane;
    @FXML
    protected MenuButton analyseTypMenuButton;
    @FXML
    protected Button backButton;
    @FXML
    protected Button configButton;
    @FXML
    protected Label errorLabel;
    @FXML
    protected Button exportButton;
    @FXML
    protected Button exportRawButton;
    @FXML
    private Hyperlink fileLink;
    @FXML
    protected Button selectCompTrialButton;
    @FXML
    private HBox statusBox;
    @FXML
    private Text statusTextLeft;
    @FXML
    private Text statusTextRight;

    private String filename = null;
    private ProgressIndicator pi;

    /**
     * Setzt Status.
     *
     * @param statusLeft -  Nachricht
     * @param filename -  Dateiname
     * @param statusRight -  Nachricht
     */
    protected void setStatus(String statusLeft, String filename, String statusRight) {
        setStatusIndicator(false);
        if (statusLeft == null) {
            statusTextLeft.setText("");
            statusTextLeft.setVisible(false);
        } else {
            statusTextLeft.setText(statusLeft);
            statusTextLeft.setVisible(true);
        }
        this.filename = filename;
        if (filename == null) {
            fileLink.setText("");
            fileLink.setVisible(false);
        } else {
            fileLink.setText(filename);
            fileLink.setVisible(true);
        }
        if (statusRight == null) {
            statusTextRight.setText("");
            statusTextRight.setVisible(false);
        } else {
            statusTextRight.setText(statusRight);
            statusTextRight.setVisible(true);
        }
    }

    /**
     * stellt den Status-Indikator an / aus.
     *
     * @param active - ob der Indikator aktiv sein soll oder nicht
     */
    protected void setStatusIndicator(boolean active) {
        if (active) {
            setStatus(null, null, null);
            statusBox.getChildren().add(pi);
            Main.primaryStage.setOnCloseRequest(e -> e.consume());
        } else {
            statusBox.getChildren().remove(pi);
            Main.primaryStage.setOnCloseRequest(e -> Platform.exit());
        }
    }

    @FXML
    protected void analyze(ActionEvent event) {
        Analyzer.analyse();
    }

    @FXML
    protected void back(ActionEvent event) {
        FunctionChooser.start();
    }

    @FXML
    protected void export(ActionEvent event) {
        Analyzer.export();
    }

    @FXML
    protected void exportRaw(ActionEvent event) {
        Analyzer.exportRaw();
    }

    @FXML
    protected void openFile(ActionEvent event) {
        if (filename == null) {
            return;
        }
        if (Desktop.isDesktopSupported()
                && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            try {
                Desktop.getDesktop().open(new File(filename));
            } catch (IOException e) {
                Logger.error("error occured while opening documentation", e);
            }
        }
    }

    @FXML
    protected void selectTrial(ActionEvent event) {
        Analyzer.setTrialId();
    }

    @FXML
    protected void selectTrialForComp(ActionEvent event) {
        Analyzer.setTrialIdComp();
    }

    @FXML
    protected void setCompHeatmap(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(true);
        selectCompTrialButton.setVisible(true);
        Analyzer.setAnalyseType(AnalyseType.COMPHEATMAP);
        analyseTypMenuButton.setText("Heatmap Vergleich");
    }

    @FXML
    protected void setCompVerlauf(ActionEvent event) {
        configButton.setDisable(true);
        analyseButton.setDisable(true);
        selectCompTrialButton.setVisible(true);
        Analyzer.setAnalyseType(AnalyseType.COMPVERLAUF);
        analyseTypMenuButton.setText("Verlauf Vergleich");
    }

    @FXML
    protected void setConfig(ActionEvent event) {
        Analyzer.setConfig();
    }

    @FXML
    protected void setHeatmap(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.HEATMAP);
        analyseTypMenuButton.setText("Heatmap");
    }

    @FXML
    protected void setRelDepthDistr(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.RELDEPTHDISTR);
        analyseTypMenuButton.setText("Verteilung Relative Tiefe");
    }

    @FXML
    protected void setVerlauf(ActionEvent event) {
        configButton.setDisable(true);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.VERLAUF);
        analyseTypMenuButton.setText("Verlauf");
    }

    @FXML
    protected void initialize() {
        assert analyseButton != null
                : "fx:id=\"analyseButton\" not injected: check 'Analyzer.fxml'.";
        assert analyseTypMenuButton != null
                : "fx:id=\"analyseTypMenuButton\" not injected: check 'Analyzer.fxml'.";
        assert backButton != null
                : "fx:id=\"backButton\" was injected: check 'Analyzer.fxml'.";
        assert analysePane != null
                : "fx:id=\"chartPane\" was injected: check 'Analyzer.fxml'.";
        assert configButton != null
                : "fx:id=\"configButton\" not injected: check 'Analyzer.fxml'.";
        assert errorLabel != null
                : "fx:id=\"errorLabel\" not injected: check 'Analyzer.fxml'.";
        assert exportButton != null
                : "fx:id=\"exportButton\" not injected: check 'Analyzer.fxml'.";
        assert exportRawButton != null
                : "fx:id=\"exportRawButton\" not injected: check 'Analyzer.fxml'.";
        assert fileLink != null
                : "fx:id=\"fileLink\" not injected: check 'Analyzer.fxml'.";
        assert selectCompTrialButton != null
                : "fx:id=\"selectCompTrialButton\" not injected: check 'Analyzer.fxml'.";
        assert statusBox != null
                : "fx:id=\"statusBox\" not injected: check 'Analyzer.fxml'.";
        assert statusTextLeft != null
                : "fx:id=\"statusTextLeft\" not injected: check 'Analyzer.fxml'.";
        assert statusTextRight != null
                : "fx:id=\"statusTextRight\" not injected: check 'Analyzer.fxml'.";

        pi = new ProgressIndicator();
        pi.setPrefHeight(statusBox.getHeight());
    }

}
