package github.weichware10.analyse.gui.analyse;

import github.weichware10.analyse.enums.AnalyseType;
import github.weichware10.analyse.gui.general.FunctionChooser;
import github.weichware10.util.gui.AbsSceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Kontroller für {@link Analyzer}.
 */
public class AnalyzerController extends AbsSceneController {

    @FXML
    protected Button analyseButton;
    @FXML
    protected MenuButton analyseTypMenuButton;
    @FXML
    protected ImageView analysedImage;
    @FXML
    protected Button backButton;
    @FXML
    protected Pane chartPane;
    @FXML
    protected Button configButton;
    @FXML
    protected Label errorLabel;
    @FXML
    protected Button exportButton;
    @FXML
    protected Button exportRawButton;
    @FXML
    protected Button selectCompTrialButton;

    @FXML
    protected void analyze(ActionEvent event) {
        Analyzer.analyse();
    }

    @FXML
    void back(ActionEvent event) {
        FunctionChooser.start();
    }

    @FXML
    void export(ActionEvent event) {
        Analyzer.export();
    }

    @FXML
    void exportRaw(ActionEvent event) {
        // TODO: ExportRaw (Was sollte hier ausgegeben werden?)
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
    protected void setConfig(ActionEvent event) {
        Analyzer.setConfig();
    }

    @FXML
    protected void setHeatmap(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.HEATPMAP);
        analyseTypMenuButton.setText("Heatmap");
    }

    @FXML
    protected void setRelFrqImgArea(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.RELFRQIMGAREA);
        analyseTypMenuButton.setText("Häufigkeitsverteilung Bildbereiche");
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
    protected void setViewTimeDistr(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.VIEWTIMEDISTR);
        analyseTypMenuButton.setText("Verteilung Betrachtungszeit");
    }

    protected void setExportStatus(String text) {
        errorLabel.setText(text);
        errorLabel.setVisible(true);
    }

    @FXML
    protected void initialize() {
        assert analyseButton != null
                : "fx:id=\"analyseButton\" not injected: check 'Analyzer.fxml'.";
        assert analyseTypMenuButton != null
                : "fx:id=\"analyseTypMenuButton\" not injected: check 'Analyzer.fxml'.";
        assert analysedImage != null
                : "fx:id=\"analysedImage\" not injected: check 'Analyzer.fxml'.";
        assert backButton != null
                : "fx:id=\"backButton\" was injected: check 'Analyzer.fxml'.";
        assert chartPane != null
                : "fx:id=\"chartPane\" was injected: check 'Analyzer.fxml'.";
        assert configButton != null
                : "fx:id=\"configButton\" not injected: check 'Analyzer.fxml'.";
        assert errorLabel != null
                : "fx:id=\"errorLabel\" not injected: check 'Analyzer.fxml'.";
        assert exportButton != null
                : "fx:id=\"exportButton\" not injected: check 'Analyzer.fxml'.";
        assert exportRawButton != null
                : "fx:id=\"exportRawButton\" not injected: check 'Analyzer.fxml'.";
        assert selectCompTrialButton != null
                : "fx:id=\"selectCompTrialButton\" not injected: check 'Analyzer.fxml'.";
    }

}
