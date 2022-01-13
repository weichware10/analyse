package github.weichware10.analyse.gui.analyse;

import github.weichware10.analyse.gui.util.AbsSceneController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * Konfigurator für {@link HeatmapConfigurator}.
 */
public class HeatmapConfiguratorController extends AbsSceneController {

    @FXML
    protected ColorPicker maxColorDiff;
    @FXML
    protected ColorPicker minColorDiff;
    @FXML
    protected CheckBox showGrid;
    @FXML
    protected CheckBox showImage;

    protected Color getMaxColorDiff() {
        return maxColorDiff.getValue();
    }

    protected Color getMinColorDiff() {
        return minColorDiff.getValue();
    }

    protected boolean getShowGrid() {
        return showGrid.isSelected();
    }

    protected boolean getShowImage() {
        return showImage.isSelected();
    }

    protected void setMaxColorDiff(Color maxColorDiff) {
        this.maxColorDiff.setValue(maxColorDiff);
    }

    protected void setMinColorDiff(Color minColorDiff) {
        this.minColorDiff.setValue(minColorDiff);
    }

    protected void setShowGrid(boolean showGrid) {
        this.showGrid.setSelected(showGrid);
    }

    protected void setShowImage(boolean showImage) {
        this.showImage.setSelected(showImage);
    }

    @Override
    protected void initialize() {
        assert maxColorDiff != null :
            "fx:id=\"maxColorDIff\" not injected: check 'HeatmapConfig.fxml'.";
        assert minColorDiff != null :
            "fx:id=\"minColorDiff\" not injected: check 'HeatmapConfig.fxml'.";
        assert showGrid != null :
            "fx:id=\"showGrid\" not injected: check 'HeatmapConfig.fxml'.";
        assert showImage != null :
            "fx:id=\"showImage\" not injected: check 'HeatmapConfig.fxml'.";

    }

}
