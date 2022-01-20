package github.weichware10.analyse.logic;

import github.weichware10.analyse.Main;
import github.weichware10.util.Logger;
import github.weichware10.util.ToolType;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.data.DataPoint;
import github.weichware10.util.data.TrialData;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;

/**
 * verantwortlich für die Erstellung des Verlauf-Diagramme.
 */
public class Verlauf {

    private static DataPointComparator comparator = new DataPointComparator();

    /**
     * Erstellt Verlauf-Liniendiagramm.
     *
     * @param trials - Versuch(e)
     * @return erstelltes Liniendiagramm
     */
    public static LineChart<Number, Number> createVerlauf(List<TrialData> trials) {
        Configuration config = Main.dataBaseClient.configurations.get(trials.get(0).configId);

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
        if (trials.get(0).toolType == ToolType.ZOOMMAPS) {
            xAxis.setLabel("Zeit in ms");
        } else {
            xAxis.setLabel("Zeit in s");
        }
        yAxis.setLabel("relDepth");

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        if (trials.size() == 0) {
            lineChart.setTitle(String.format("Verlauf %s", trials.get(0).toolType.toString()));
        } else {
            lineChart.setTitle(String.format("Verlauf Vergleich %s",
                    trials.get(0).toolType.toString()));
        }

        // Graph(en) für Liniendiagramm erstellen
        for (TrialData trial : trials) {
            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            series.setName(trial.getTrialId());

            if (trial.toolType == ToolType.ZOOMMAPS) {
                lineChart.setCreateSymbols(false);
                fillSeries(trial.getData(), width, height, series);
            } else if (trial.toolType == ToolType.CODECHARTS) {
                fillSeries(trial.getData(),
                        config.getCodeChartsConfiguration().getMaxDepth(), series);
            }

            lineChart.getData().add(series);
        }
        return lineChart;
    }

    /**
     * Setzt die einzelnen Datenpunkte im Graph (CodeCharts).
     *
     * @param dataPoints - Versuchs-Daten
     * @param maxDepth - maximale Tiefe
     * @param series - Graph
     */
    private static void fillSeries(List<DataPoint> dataPoints, double maxDepth,
            XYChart.Series<Number, Number> series) {
        for (DataPoint dataPoint : dataPoints) {
            // Relative Tiefe berechnen
            double relDepth = (double) dataPoint.depth
                    / (double) maxDepth;

            // Punkt im Diagramm setzen
            series.getData().add(
                    new XYChart.Data<Number, Number>(dataPoint.timeOffset / 1000, relDepth));
        }
    }

    /**
     * Setzt die einzelnen Datenpunkte im Graph (ZoomMaps).
     *
     * @param dataPoints - Versuchs-Daten
     * @param width - Bildbreite
     * @param height - Bildhöhe
     * @param series - Graph
     */
    private static void fillSeries(List<DataPoint> dataPoints, int width, int height,
            XYChart.Series<Number, Number> series) {
        // so sortieren, dass immer weiter reingezoomt wird
        List<DataPoint> sortedDataPoints = dataPoints.stream()
                .sorted(comparator).collect(Collectors.toList());
        // Maximale Tiefe ermitteln
        DataPoint minDataPoint = sortedDataPoints.get(0);
        DataPoint maxDataPoint = sortedDataPoints.get(sortedDataPoints.size() - 1);

        double minDepth = Analyse.calculateDepth(minDataPoint, width, height, null, null);
        double maxDepth = Analyse.calculateDepth(maxDataPoint, width, height, null, null);
        for (DataPoint dataPoint : dataPoints) {
            // Relative Tiefe berechnen
            double relDepth = Analyse.calculateDepth(
                    dataPoint, width, height, minDepth, maxDepth);

            // Punkt im Diagramm setzen
            series.getData().add(
                    new XYChart.Data<Number, Number>(dataPoint.timeOffset, relDepth));
        }
    }
}
