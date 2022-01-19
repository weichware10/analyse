package github.weichware10.analyse.gui.admin;

import github.weichware10.analyse.gui.admin.Configurator.Mode;
import github.weichware10.analyse.gui.general.FunctionChooser;
import github.weichware10.util.Logger;
import github.weichware10.util.ToolType;
import github.weichware10.util.gui.AbsSceneController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Controller für {@link Configurator}.
 */
public class ConfiguratorController extends AbsSceneController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    protected GridPane codeChartsConfigPane;
    @FXML
    protected Label configHeading;
    @FXML
    protected Label configSubHeading;
    @FXML
    protected Button dataBaseSaveButton;
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
    protected CheckBox showGridBox;
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
    protected GridPane zoomMapsConfigPane;
    @FXML
    protected TextArea problemArea;

    @FXML
    private void startFunctionChooser() {
        Logger.info("configurator:content Returning to FunctionChooser");
        FunctionChooser.start();
    }

    @FXML
    private void loadFromJson() {
        Logger.info("configurator:content Loading from JSON");
        Configurator.loadFromJson();
    }

    @FXML
    private void checkInt(ActionEvent event) {
        if (!(event.getSource() instanceof TextField)) {
            Logger.debug("wrong");
            return;
        }
        checkInt((TextField) event.getSource(), true);
    }

    private void checkInt(TextField intField, boolean force) {
        if ((intField.isFocused() && !force) || intField.getText().length() == 0) {
            return;
        }
        try {
            String possibleInt = intField.getText().replaceAll("[^\\d.,-]", "");
            intField.setText(Integer.toString(
                    (int) Math.round(Double.valueOf(possibleInt))));
            intField.setText(Integer.toString(Integer.valueOf(intField.getText())));
        } catch (NumberFormatException e) {
            intField.setText("1");
        }
    }

    @FXML
    private void checkDouble(ActionEvent event) {
        if (!(event.getSource() instanceof TextField)) {
            return;
        }
        checkDouble((TextField) event.getSource(), true);
    }

    private void checkDouble(TextField doubleField, boolean force) {
        if ((doubleField.isFocused() && !force) || doubleField.getText().length() == 0) {
            return;
        }
        try {
            String possibleDouble = doubleField.getText().replaceAll("[^\\d.,-]", "");
            doubleField.setText(Double.toString(Double.valueOf(possibleDouble)));
        } catch (NumberFormatException e) {
            doubleField.setText(Double.toString(1.0));
        }
    }

    @FXML
    protected void initialize() {
        assert codeChartsConfigPane != null
                : "fx:id=\"codeChartsConfigPane\" not injected: check 'Configurator.fxml'.";
        assert configHeading != null
                : "fx:id=\"configHeading\" not injected: check 'Configurator.fxml'.";
        assert configSubHeading != null
                : "fx:id=\"configSubHeading\" not injected: check 'Configurator.fxml'.";
        assert dataBaseSaveButton != null
                : "fx:id=\"dataBaseSaveButton\" not injected: check 'Configurator.fxml'.";
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
        assert showGridBox != null
                : "fx:id=\"showGridBox\" not injected: check 'Configurator.fxml'.";
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
        assert problemArea != null
                : "fx:id=\"problemArea\" not injected: check 'Configurator.fxml'.";

        toolTypeBox.getItems().addAll(ToolType.CODECHARTS, ToolType.ZOOMMAPS, null);
        toolTypeBox.valueProperty().addListener((o) -> setConfigType());
        Configurator.mode.addListener((o) -> setTitle());

        // VALIDIERUNG
        // Integer fields
        TextField[] intFields = new TextField[] {
            initialSizeFieldX,
            initialSizeFieldY,
            timingsImgField,
            timingsGridField,
            iterationsField,
            maxDepthField,
            horizontalSplitField,
            verticalSplitField
        };
        for (TextField intField : intFields) {
            intField.focusedProperty().addListener((o) -> checkInt(intField, false));
        }
        // Double Fields
        TextField[] doubleFields = new TextField[] {
            speedField,
            imageViewWidthField,
            imageViewHeightField
        };
        for (TextField doubleField : doubleFields) {
            doubleField.focusedProperty().addListener((o) -> checkDouble(doubleField, false));
        }

        // ÄNDERN VON VIEW ZU EDIT
        TextField[] changeableFields = new TextField[] {
            horizontalSplitField,
            imageViewHeightField,
            imageViewWidthField,
            imgUrlField,
            initialSizeFieldX,
            initialSizeFieldY,
            introField,
            iterationsField,
            maxDepthField,
            outroField,
            speedField,
            stringIdField,
            timingsGridField,
            timingsImgField,
            verticalSplitField,
            questionField
        };
        CheckBox[] changeableBoxes = new CheckBox[] {
            showGridBox,
            relativeSizeBox,
            randomStringsBox,
            tutorialBox
        };
        toolTypeBox.valueProperty().addListener((o) -> Configurator.changeToEdit());
        for (TextField field : changeableFields) {
            field.textProperty().addListener((o) -> Configurator.changeToEdit());
        }
        for (CheckBox box : changeableBoxes) {
            box.selectedProperty().addListener((o) -> Configurator.changeToEdit());
        }
    }

    private void setConfigType() {
        if (toolTypeBox.getValue() == ToolType.ZOOMMAPS) {
            zoomMapsConfigPane.setVisible(true);
            zoomMapsConfigPane.setDisable(false);
            codeChartsConfigPane.setVisible(false);
        } else if (toolTypeBox.getValue() == ToolType.CODECHARTS) {
            codeChartsConfigPane.setVisible(true);
            codeChartsConfigPane.setDisable(false);
            zoomMapsConfigPane.setVisible(false);
        } else {
            codeChartsConfigPane.setDisable(true);
            zoomMapsConfigPane.setDisable(true);
        }
    }

    private void setTitle() {
        switch (Configurator.mode.get()) {
            case JSONVIEW:
                configHeading.setText("Konfiguration");
                configSubHeading.setText("aus JSON geladen");
                break;
            case JSONEDIT:
                configHeading.setText("neue Konfiguration");
                configSubHeading.setText("basierend auf JSON");
                break;
            case DBVIEW:
                configHeading.setText("Konfiguration");
                configSubHeading.setText(
                        String.format("aus Datenbank, ID: %s", Configurator.configId));
                break;
            case DBEDIT:
                configHeading.setText("neue Konfiguration");
                configSubHeading.setText(
                        String.format("basierend auf Datenbank, (ID: %s)", Configurator.configId));
                break;
            default: // NEW
                configHeading.setText("neue Konfiguration");
                configSubHeading.setText("");
                break;
        }
    }

    @FXML
    private void reset() {
        toolTypeBox.setValue(null);
        introField.setText(null);
        outroField.setText(null);
        imgUrlField.setText(null);
        stringIdField.setText(null);
        stringIdButton.setText("...");
        stringIdButton.setDisable(true);
        initialSizeFieldX.setText(null);
        initialSizeFieldY.setText(null);
        iterationsField.setText(null);
        maxDepthField.setText(null);
        horizontalSplitField.setText(null);
        verticalSplitField.setText(null);
        questionField.setText(null);
        speedField.setText(null);
        imageViewWidthField.setText(null);
        imageViewHeightField.setText(null);
        tutorialBox.setSelected(false);
        showGridBox.setSelected(false);
        relativeSizeBox.setSelected(false);
        randomStringsBox.setSelected(false);
        timingsImgField.setText(null);
        timingsGridField.setText(null);

        Configurator.mode.set(Mode.NEW);
        Configurator.updateStringList(null);
    }
}
