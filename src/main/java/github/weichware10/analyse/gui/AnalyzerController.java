package github.weichware10.analyse.gui;

import github.weichware10.analyse.enums.AnalyseType;
import github.weichware10.analyse.gui.util.AbsSceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;

/**
 * Kontroller für {@link Analyzer}.
 */
public class AnalyzerController extends AbsSceneController {

    @FXML
    private Button analyseButton;
    @FXML
    private MenuButton analyseTypMenuButton;
    @FXML
    private Button configButton;
    @FXML
    private Button selectCompTrialButton;

    @FXML
    void analyze(ActionEvent event) {
        // TODO: Start Analyse
    }

    @FXML
    void selectTrial(ActionEvent event) {
        analyseTypMenuButton.setDisable(true);
        configButton.setDisable(true);
        analyseButton.setDisable(true);
        selectCompTrialButton.setVisible(false);
        Analyzer.setTrialId(analyseTypMenuButton);
    }

    @FXML
    void selectTrialForComp(ActionEvent event) {
        Analyzer.setTrialIdComp(analyseButton);
    }

    @FXML
    void setCompHeatmap(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(true);
        selectCompTrialButton.setVisible(true);
        Analyzer.setAnalyseType(AnalyseType.COMPHEATMAP);
    }

    @FXML
    void setConfig(ActionEvent event) {
        // TODO: KonfigDialog erstellen
    }

    @FXML
    void setHeapmap(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.HEATPMAP);
    }

    @FXML
    void setRelFrqImgArea(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.RELFRQIMGAREA);
    }

    @FXML
    void setVerlauf(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.VERLAUF);
    }

    @FXML
    void setViewTimeDistr(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.VIEWTIMEDISTR);
    }

    @FXML
    protected void initialize() {
        assert analyseButton != null :
            "fx:id=\"analyseButton\" not injected: check  'Analyzer.fxml'.";
        assert analyseTypMenuButton != null :
            "fx:id=\"analyseTypMenuButton\" not injected: check  'Analyzer.fxml'.";
        assert configButton != null :
            "fx:id=\"configButton\" not injected: check  'Analyzer.fxml'.";
        assert selectCompTrialButton != null :
            "fx:id=\"selectCompTrialButton\" not injected: check  'Analyzer.fxml'.";
    }

}
