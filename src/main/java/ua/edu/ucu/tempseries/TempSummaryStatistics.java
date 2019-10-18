package ua.edu.ucu.tempseries;


public class TempSummaryStatistics {

    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;

    public TempSummaryStatistics(double avgTemp,
                                 double devTemp,
                                 double minTemp,
                                 double maxTemp)
    {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;

    }

    @Override
    public String toString() {
        return "TempSummaryStatistics{"
                + "avgTemp=" + avgTemp
                + ", devTemp=" + devTemp
                + ", minTemp=" + minTemp
                + ", maxTemp=" + maxTemp + '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
        {
            return true;
        }

        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        TempSummaryStatistics that = (TempSummaryStatistics) o;
        return Double.compare(that.avgTemp, avgTemp) == 0
                && Double.compare(that.devTemp, devTemp) == 0
                && Double.compare(that.minTemp, minTemp) == 0
                && Double.compare(that.maxTemp, maxTemp) == 0;
    }

}
