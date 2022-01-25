package github.weichware10.analyse.logic;

import github.weichware10.analyse.Main;
import github.weichware10.analyse.config.DiagramConfig;
import github.weichware10.util.Logger;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.data.DataPoint;
import github.weichware10.util.data.TrialData;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;

/**
 * verantwortlich für die Erstellung der Diagramm-Diagramme.
 */
public class Diagram {

    private static DataPointComparator comparator = new DataPointComparator();

    /**
     * Erstellt Balkendiagramm über die Relative Tiefenverteilung der Datenpunkte.
     *
     * @param trial - Versuch
     * @param diaConfig - Konfiguration
     * @return erstelltes Balkendiagramm
     */
    public static BarChart<String, Number> createDiagramBarChart(
            TrialData trial, DiagramConfig diaConfig) {
        Configuration config = Main.dataBaseClient.configurations.get(trial.configId);

        // Bild laden für Bildbreite und -höhe
        String imageUrl = Analyse.saveImage(config.getImageUrl());
        Image image = null;
        try {
            image = new Image(Paths.get(imageUrl).toUri().toString());
        } catch (Exception e) {
            Logger.error("diagram:content Failed to save the image", e);
            return null;
        }

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        double[][] pixels = new double[width][height];

        List<DataPoint> sortedDataPoints = trial.getData().stream()
                .sorted(comparator).collect(Collectors.toList());

        fillPixelsWithRelDepth(width, height, pixels, sortedDataPoints);

        int amountSteps = diaConfig.getStepsBetween();
        double stepWidth = 1.0f / (double) amountSteps;
        List<Double> diagramData = new ArrayList<Double>();

        allocatePixels(pixels, amountSteps, stepWidth, diagramData);

        calcRelFreqPerStep(width, height, diagramData);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle(String.format("Balkendiagramm %s", trial.toolType.toString()));
        xAxis.setLabel("relative Tiefe");
        yAxis.setLabel("relative Häufigkeit in %");

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName(trial.getTrialId());

        for (int step = 0; step < diagramData.size(); step++) {
            double min = step * stepWidth;
            double max = (step + 1) * stepWidth;
            String area = String.format(Locale.US, "[%.2f - %.2f]: %.2f %%",
                    min, max, diagramData.get(step) * 100);
            series.getData().add(
                    new XYChart.Data<String, Number>(area, diagramData.get(step) * 100));
        }

        barChart.getData().add(series);

        return barChart;
    }

    /**
     * Erstellt Kreisdiagramm über die Relative Tiefenverteilung der Datenpunkte.
     *
     * @param trial - Versuch
     * @param diaConfig - Konfiguration
     * @return erstelltes Kreisdiagramm
     */
    public static PieChart createDiagramPieChart(TrialData trial, DiagramConfig diaConfig) {
        Configuration config = Main.dataBaseClient.configurations.get(trial.configId);

        // Bild laden für Bildbreite und -höhe
        String imageUrl = Analyse.saveImage(config.getImageUrl());
        Image image = null;
        try {
            image = new Image(Paths.get(imageUrl).toUri().toString());
        } catch (Exception e) {
            Logger.error("diagram:content Failed to save the image", e);
            return null;
        }

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        double[][] pixels = new double[width][height];

        List<DataPoint> sortedDataPoints = trial.getData().stream()
                .sorted(comparator).collect(Collectors.toList());

        fillPixelsWithRelDepth(width, height, pixels, sortedDataPoints);

        int amountSteps = diaConfig.getStepsBetween();
        double stepWidth = 1.0f / (double) amountSteps;
        List<Double> diagramData = new ArrayList<Double>();

        allocatePixels(pixels, amountSteps, stepWidth, diagramData);

        calcRelFreqPerStep(width, height, diagramData);

        final PieChart pieChart = new PieChart();
        pieChart.setTitle(String.format("Kreisdiagramm %s (relative Häufigkeit in %%)",
                trial.toolType.toString()));

        for (int step = 0; step < diagramData.size(); step++) {
            double min = step * stepWidth;
            double max = (step + 1) * stepWidth;
            String area = String.format(Locale.US, "[%.2f - %.2f]: %.2f %%",
                    min, max, diagramData.get(step) * 100);
            PieChart.Data slice = new PieChart.Data(area, diagramData.get(step) * 100);
            pieChart.getData().add(slice);
        }

        return pieChart;
    }

    private static void fillPixelsWithRelDepth(int width, int height,
            double[][] pixels, List<DataPoint> sortedDataPoints) {
        DataPoint minDataPoint = sortedDataPoints.get(0);
        DataPoint maxDataPoint = sortedDataPoints.get(sortedDataPoints.size() - 1);

        double minDepth = Analyse.calculateDepth(minDataPoint, width, height, null, null);
        double maxDepth = Analyse.calculateDepth(maxDataPoint, width, height, null, null);

        // Für jedes Pixel des Bildes endgültige relative Tiefe berechnen
        for (DataPoint dataPoint : sortedDataPoints) {
            int xmin = (int) dataPoint.viewport.getMinX();
            int xmax = (int) dataPoint.viewport.getMaxX();
            for (; xmin < xmax; xmin++) {
                int ymin = (int) dataPoint.viewport.getMinY();
                int ymax = (int) dataPoint.viewport.getMaxY();
                for (; ymin < ymax; ymin++) {
                    pixels[xmin][ymin] = Analyse.calculateDepth(
                            dataPoint, width, height, minDepth, maxDepth);
                }
            }
        }
    }

    private static void allocatePixels(double[][] pixels, int amountSteps,
            double stepWidth, List<Double> diagramData) {
        // Initialisieren
        for (int i = 0; i < amountSteps; i++) {
            diagramData.add(0.0);
        }

        // Pixel nach ihrer relative Tiefe zuordnen
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {
                for (int i = 1; i <= amountSteps; i++) {
                    if (pixels[x][y] <= stepWidth * i) {
                        diagramData.set(i - 1, diagramData.get(i - 1) + 1);
                        break;
                    }
                }
            }
        }
    }

    private static void calcRelFreqPerStep(int width, int height, List<Double> diagramData) {
        long amountPixel = width * height;

        for (int step = 0; step < diagramData.size(); step++) {
            double relFreq = diagramData.get(step) / amountPixel;
            diagramData.set(step, relFreq);
        }
    }
}
