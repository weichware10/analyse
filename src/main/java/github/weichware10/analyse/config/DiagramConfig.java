package github.weichware10.analyse.config;

/**
 * Speicherung der Konfiguration der Diagramm-Analysemethoden.
 */
public class DiagramConfig {
    private int stepsBetween;
    private boolean barChart;

    /**
     * Speicherung der Konfiguration der Diagramm-Analysemethoden.
     */
    public DiagramConfig() {
        this.stepsBetween = 4;
        this.barChart = true;
    }

    /**
     * setzt neue Anzahl an Zwischenschritten.
     *
     * @param stepsBetween - neue Anzahl Zwischenschritte
     * @return Erfolgsboolean
     */
    public boolean setStepsBetween(int stepsBetween) {
        if (stepsBetween <= 10 && stepsBetween >= 1) {
            this.stepsBetween = stepsBetween;
            return true;
        } else {
            return false;
        }
    }

    public int getStepsBetween() {
        return stepsBetween;
    }

    public boolean isBarChart() {
        return barChart;
    }

    public void setBarChart(boolean barChart) {
        this.barChart = barChart;

    }

    @Override
    public String toString() {
        return String.format("""
                DiagramConfig: {
                    stepsBetween: %d
                    barChart: %b
                    pieChart: %b
                }
                """,
                stepsBetween,
                barChart,
                !barChart);
    }
}
