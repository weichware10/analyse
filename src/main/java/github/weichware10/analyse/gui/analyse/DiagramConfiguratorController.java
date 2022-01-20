package github.weichware10.analyse.gui.analyse;

import github.weichware10.util.gui.AbsSceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;

/**
 * Kontroller für {@link  DiagramConfigurator}.
 */
public class DiagramConfiguratorController extends AbsSceneController {

    private int steps;

    @FXML
    private RadioButton barChart;
    @FXML
    private RadioButton pieChart;
    @FXML
    protected Slider stepsSlider;
    @FXML
    protected Label stepsLabel;

    @FXML
    private void selectBarChart(ActionEvent event) {
        barChart.setSelected(true);
        pieChart.setSelected(false);
    }

    @FXML
    private void selectPieChart(ActionEvent event) {
        barChart.setSelected(false);
        pieChart.setSelected(true);
    }

    protected boolean isBarChart() {
        return barChart.isSelected();
    }

    protected void setBarChart(boolean selected) {
        barChart.setSelected(selected);
    }

    protected void setPieChart(boolean selected) {
        pieChart.setSelected(selected);
    }

    protected void initStepsSlider(int current) {
        stepsSlider.adjustValue(current);
    }

    protected int getSteps() {
        return steps;
    }

    @FXML
    protected void initialize() {
        assert barChart != null
                : "fx:id=\"barChart\" not injected: check 'DiagramConfigurator.fxml'.";
        assert pieChart != null
                : "fx:id=\"pieChart\" not injected: check 'DiagramConfigurator.fxml'.";
        assert stepsSlider != null
                : "fx:id=\"steps\" not injected: check 'DiagramConfigurator.fxml'.";
        assert stepsLabel != null
                : "fx:id=\"stepsLabel\" not injected: check 'DiagramConfigurator.fxml'.";

        stepsSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                steps = (int) stepsSlider.getValue();
                stepsLabel.setText(Integer.toString(steps));
            }

        });
    }
}
