package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.gui.general.FunctionChooser;
import github.weichware10.analyse.gui.util.AbsSceneController;
import github.weichware10.util.Logger;
import github.weichware10.util.ToolType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

/**
 * Controller für {@link Configurator}.
 */
public class ConfiguratorController extends AbsSceneController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    protected TitledPane codeChartsConfigPane;
    @FXML
    protected TextField configIdField;
    @FXML
    protected Label configIdLabel;
    @FXML
    protected Button dataBaseSaveButton;
    @FXML
    protected TitledPane generalConfigPane;
    @FXML
    protected TextField horizontalSplitField;
    @FXML
    protected TextField imageViewHeightField;
    @FXML
    protected TextField imageViewWidthField;
    @FXML
    protected TextField imgUrlField;
    @FXML
    protected TextField initialSizeFieldX;
    @FXML
    protected TextField initialSizeFieldY;
    @FXML
    protected TextField introField;
    @FXML
    protected TextField iterationsField;
    @FXML
    protected Button jsonSaveButton;
    @FXML
    protected TextField maxDepthField;
    @FXML
    protected TextField outroField;
    @FXML
    protected CheckBox randomStringsBox;
    @FXML
    protected CheckBox relativeSizeBox;
    @FXML
    protected CheckBox shoGridBox;
    @FXML
    protected TextField speedField;
    @FXML
    protected Button stringIdButton;
    @FXML
    protected TextField stringIdField;
    @FXML
    protected TextField timingsGridField;
    @FXML
    protected TextField timingsImgField;
    @FXML
    protected ComboBox<ToolType> toolTypeBox;
    @FXML
    protected CheckBox tutorialBox;
    @FXML
    protected TextField verticalSplitField;
    @FXML
    protected TextField questionField;
    @FXML
    protected TitledPane zoomMapsConfigPane;

    @FXML
    private void startFunctionChooser() {
        Logger.info("configurator:content Returning to FunctionChooser");
        FunctionChooser.start();
    }

    @FXML
    protected void initialize() {
        assert codeChartsConfigPane != null
                : "fx:id=\"codeChartsConfigPane\" not injected: check 'Configurator.fxml'.";
        assert configIdField != null
                : "fx:id=\"configIdField\" not injected: check 'Configurator.fxml'.";
        assert configIdLabel != null
                : "fx:id=\"configIdLabel\" not injected: check 'Configurator.fxml'.";
        assert dataBaseSaveButton != null
                : "fx:id=\"dataBaseSaveButton\" not injected: check 'Configurator.fxml'.";
        assert generalConfigPane != null
                : "fx:id=\"generalConfigPane\" not injected: check 'Configurator.fxml'.";
        assert horizontalSplitField != null
                : "fx:id=\"horizontalSplitField\" not injected: check 'Configurator.fxml'.";
        assert imageViewHeightField != null
                : "fx:id=\"imageViewHeightField\" not injected: check 'Configurator.fxml'.";
        assert imageViewWidthField != null
                : "fx:id=\"imageViewWidthField\" not injected: check 'Configurator.fxml'.";
        assert imgUrlField != null
                : "fx:id=\"imgUrlField\" not injected: check 'Configurator.fxml'.";
        assert initialSizeFieldX != null
                : "fx:id=\"initialSizeFieldX\" not injected: check 'Configurator.fxml'.";
        assert initialSizeFieldY != null
                : "fx:id=\"initialSizeFieldY\" not injected: check 'Configurator.fxml'.";
        assert introField != null
                : "fx:id=\"introField\" not injected: check 'Configurator.fxml'.";
        assert iterationsField != null
                : "fx:id=\"iterationsField\" not injected: check 'Configurator.fxml'.";
        assert jsonSaveButton != null
                : "fx:id=\"jsonSaveButton\" not injected: check 'Configurator.fxml'.";
        assert maxDepthField != null
                : "fx:id=\"maxDepthField\" not injected: check 'Configurator.fxml'.";
        assert outroField != null
                : "fx:id=\"outroField\" not injected: check 'Configurator.fxml'.";
        assert randomStringsBox != null
                : "fx:id=\"randomStringsBox\" not injected: check 'Configurator.fxml'.";
        assert relativeSizeBox != null
                : "fx:id=\"relativeSizeBox\" not injected: check 'Configurator.fxml'.";
        assert shoGridBox != null
                : "fx:id=\"shoGridBox\" not injected: check 'Configurator.fxml'.";
        assert speedField != null
                : "fx:id=\"speedField\" not injected: check 'Configurator.fxml'.";
        assert stringIdButton != null
                : "fx:id=\"stringIdButton\" not injected: check 'Configurator.fxml'.";
        assert stringIdField != null
                : "fx:id=\"stringIdField\" not injected: check 'Configurator.fxml'.";
        assert timingsGridField != null
                : "fx:id=\"timingsGridField\" not injected: check 'Configurator.fxml'.";
        assert timingsImgField != null
                : "fx:id=\"timingsImgField\" not injected: check 'Configurator.fxml'.";
        assert toolTypeBox != null
                : "fx:id=\"toolTypeBox\" not injected: check 'Configurator.fxml'.";
        assert tutorialBox != null
                : "fx:id=\"tutorialBox\" not injected: check 'Configurator.fxml'.";
        assert verticalSplitField != null
                : "fx:id=\"verticalSplitField\" not injected: check 'Configurator.fxml'.";
        assert questionField != null
                : "fx:id=\"questionField\" not injected: check 'Configurator.fxml'.";
        assert zoomMapsConfigPane != null
                : "fx:id=\"zoomMapsConfigPane\" not injected: check 'Configurator.fxml'.";

        toolTypeBox.getItems().addAll(ToolType.CODECHARTS, ToolType.ZOOMMAPS);
    }

}
