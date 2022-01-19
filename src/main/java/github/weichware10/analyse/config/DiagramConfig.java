package github.weichware10.analyse.config;

/**
 * Speicherung der Konfiguration der Diagramm-Analysemethoden.
 */
public class DiagramConfig {
    private double minTime; // * >= 0 Länge | Zoomstufe
    private double maxTime; // * <= 10 Länge | Zoomstufe
    private int stepsBetween; // * [1,10], minTime=maxTime: steps = 0

    /**
     * Speicherung der Konfiguration der Diagramm-Analysemethoden.
     */
    public DiagramConfig() {
        this.minTime = 0.0f;
        this.maxTime = 10.0f;
        this.stepsBetween = 4;
    }

    /**
     * setzt neue minimale und maximale Zeit.
     *
     * @param minTime - neue minimale Zeit
     * @param maxTime - neue maximale Zeit
     * @return
     *         true, falls neue min. und max. Zeit erfolgreich gesetzt wurden;
     *         false, falls neue min. und max. Zeit nicht gesetzt wurden
     */
    public boolean setNewTime(double minTime, double maxTime) {
        if (minTime >= 0.0f && maxTime <= 10.0f && minTime <= maxTime) {
            this.minTime = minTime;
            this.maxTime = maxTime;
            if (this.maxTime == this.minTime) {
                this.stepsBetween = 0;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * gibt die minimale Zeit zurück.
     *
     * @return minimale Zeit
     */
    public double getMinTime() {
        return minTime;
    }

    /**
     * gibt die maximale Zeit zurück.
     *
     * @return maximale Zeit
     */
    public double getMaxTime() {
        return maxTime;
    }

    /**
     * setzt neue Anzahl an Zwischenschritten.
     *
     * @param stepsBetween - neue Anzahl Zwischenschritte
     * @return
     *         true, falls neue Anzahl an Zwischenschritten erfolgreich gesetzt
     *         wurde;
     *         false, falls neue Anzahl an Zwischenschritten nicht gesetzt
     *         wurde
     */
    public boolean setStepsBetween(int stepsBetween) {
        if (this.getMinTime() == this.getMaxTime()) {
            this.stepsBetween = 0;
            return true;
        } else if (stepsBetween <= 10 && stepsBetween >= 1) {
            this.stepsBetween = stepsBetween;
            return true;
        } else {
            return false;
        }
    }

    /**
     * gibt die Anzahl der Zwischenschritte zurück.
     *
     * @return Anzahl der Zwischenschritte
     */
    public int getStepsBetween() {
        return stepsBetween;
    }

    @Override
    public String toString() {
        return String.format("""
                DiagramConfig: {
                    minTime: %f
                    maxTime: %f
                    stepsBetween: %d
                }
                """,
                minTime,
                maxTime,
                stepsBetween);
    }
}
