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
    protected Button analyseButton;
    @FXML
    protected MenuButton analyseTypMenuButton;
    @FXML
    protected Button configButton;
    @FXML
    protected Button selectCompTrialButton;

    @FXML
    protected void analyze(ActionEvent event) {
        // TODO: Start Analyse
        Analyzer.analyse();
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
    }

    @FXML
    protected void setConfig(ActionEvent event) {
        Analyzer.setConfig();
    }

    @FXML
    protected void setHeapmap(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.HEATPMAP);
    }

    @FXML
    protected void setRelFrqImgArea(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.RELFRQIMGAREA);
    }

    @FXML
    protected void setVerlauf(ActionEvent event) {
        configButton.setDisable(true);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.VERLAUF);
    }

    @FXML
    protected void setViewTimeDistr(ActionEvent event) {
        configButton.setDisable(false);
        analyseButton.setDisable(false);
        selectCompTrialButton.setVisible(false);
        Analyzer.setAnalyseType(AnalyseType.VIEWTIMEDISTR);
    }

    @FXML
    protected void initialize() {
        assert analyseButton != null :
            "fx:id=\"analyseButton\" not injected: check 'Analyzer.fxml'.";
        assert analyseTypMenuButton != null :
            "fx:id=\"analyseTypMenuButton\" not injected: check 'Analyzer.fxml'.";
        assert configButton != null :
            "fx:id=\"configButton\" not injected: check 'Analyzer.fxml'.";
        assert selectCompTrialButton != null :
            "fx:id=\"selectCompTrialButton\" not injected: check 'Analyzer.fxml'.";
    }

}
