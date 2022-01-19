package github.weichware10.analyse.gui.analyse;

import github.weichware10.util.gui.AbsSceneController;
import java.text.DecimalFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * Kontroller für {@link  DiagramConfigurator}.
 */
public class DiagramConfiguratorController extends AbsSceneController {

    private double maxTime;
    private double minTime;
    private int steps;
    @FXML
    protected Slider maxTimeSlider;
    @FXML
    protected Label maxTimeLabel;
    @FXML
    protected Slider minTimeSlider;
    @FXML
    protected Label minTimeLabel;
    @FXML
    protected Slider stepsSlider;
    @FXML
    protected Label stepsLabel;

    protected void initMaxTimeSlider(double current) {
        maxTimeSlider.adjustValue(current);
    }

    protected void initMinTimeSlider(double current) {
        minTimeSlider.adjustValue(current);
    }

    protected void initStepsSlider(int current) {
        stepsSlider.adjustValue(current);
    }

    public double getMaxTime() {
        return maxTime;
    }

    public double getMinTime() {
        return minTime;
    }

    public int getSteps() {
        return steps;
    }

    @FXML
    protected void initialize() {
        assert maxTimeSlider != null
                : "fx:id=\"maxTime\" not injected: check 'DiagramConfigurator.fxml'.";
        assert maxTimeLabel != null
                : "fx:id=\"maxTimeLabel\" not injected: check 'DiagramConfigurator.fxml'.";
        assert minTimeSlider != null
                : "fx:id=\"minTime\" not injected: check 'DiagramConfigurator.fxml'.";
        assert minTimeLabel != null
                : "fx:id=\"minTimeLabel\" not injected: check 'DiagramConfigurator.fxml'.";
        assert stepsSlider != null
                : "fx:id=\"steps\" not injected: check 'DiagramConfigurator.fxml'.";
        assert stepsLabel != null
                : "fx:id=\"stepsLabel\" not injected: check 'DiagramConfigurator.fxml'.";

        DecimalFormat df = new DecimalFormat("0.00");

        maxTimeSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                maxTime = maxTimeSlider.getValue();
                maxTimeLabel.setText(df.format(maxTime));
                minTimeSlider.setMax(maxTime);
                if (maxTime == 0.0f) {
                    minTimeSlider.setDisable(true);
                } else {
                    minTimeSlider.setDisable(false);
                }
            }

        });

        minTimeSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                minTime = minTimeSlider.getValue();
                minTimeLabel.setText(df.format(minTime));
                maxTimeSlider.setMin(minTime);
                if (minTime == 10.0f) {
                    maxTimeSlider.setDisable(true);
                } else {
                    maxTimeSlider.setDisable(false);
                }
            }

        });

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
