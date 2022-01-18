package github.weichware10.analyse.logic;

import github.weichware10.analyse.Main;
import github.weichware10.util.Logger;
import github.weichware10.util.ToolType;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.data.DataPoint;
import github.weichware10.util.data.TrialData;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;

/**
 * verantwortlich für die Erstellung des Verlauf-Diagramms.
 */
public class Verlauf {

    /**
     * Erstellt Verlauf-Liniendiagramm.
     *
     * @param trial - Versuch
     */
    public static LineChart<Number, Number> createVerlauf(TrialData trial) {
        Configuration config = Main.dataBaseClient.configurations.get(trial.configId);

        // Bild laden für Bildbreite und -höhe
        String imageUrl = Analyse.saveImage(config.getImageUrl());
        Image image = null;
        try {
            image = new Image(imageUrl);
        } catch (Exception e) {
            Logger.error("Failed to save the image", e, true);
            return null;
        }

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Liniendiagramm erstellen
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        if (trial.toolType == ToolType.ZOOMMAPS) {
            xAxis.setLabel("Zeit in ms");
        } else {
            xAxis.setLabel("Zeit in s");
        }
        yAxis.setLabel("relDepth");
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle(String.format("Verlauf %s", trial.toolType.toString()));

        // Graph für Liniendiagramm erstellen
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        series.setName(trial.getTrialId());

        if (trial.toolType == ToolType.ZOOMMAPS) {
            lineChart.setCreateSymbols(false);
            // Maximale Tiefe ermitteln
            double maxDepth = Analyse.findMaxDepth(trial.getData(), width, height);
            for (DataPoint dataPoint : trial.getData()) {
                // Relative Tiefe berechnen
                double relDepth = Analyse.calcRelDepthZm(dataPoint.viewport, width,
                        height, maxDepth);

                // Punkt im Diagramm setzen
                series.getData().add(
                        new XYChart.Data<Number, Number>(dataPoint.timeOffset, relDepth));
            }
        } else if (trial.toolType == ToolType.CODECHARTS) {
            for (DataPoint dataPoint : trial.getData()) {
                // Relative Tiefe berechnen
                double relDepth = Analyse.calcRelDepthCc((double) dataPoint.depth,
                        (double) config.getCodeChartsConfiguration().getMaxDepth());

                // Punkt im Diagramm setzen
                series.getData().add(
                        new XYChart.Data<Number, Number>(dataPoint.timeOffset / 1000, relDepth));
            }
        }
        lineChart.getData().add(series);
        return lineChart;
    }
}
