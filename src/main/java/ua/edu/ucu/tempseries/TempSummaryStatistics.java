package ua.edu.ucu.tempseries;


import java.util.Objects;

public class TempSummaryStatistics {

    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;

    /*
    Constructor
    params: double, double, double,double

     */
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

    /*
    Compare two TSS
    params: Object
    return: boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        TempSummaryStatistics that = (TempSummaryStatistics) o;
        return Double.compare(that.avgTemp, avgTemp) == 0
                && Double.compare(that.devTemp, devTemp) == 0
                && Double.compare(that.minTemp, minTemp) == 0
                && Double.compare(that.maxTemp, maxTemp) == 0;
    }

    /*
    Hash Code
    return: int
     */
    @Override
    public int hashCode() {
        return Objects.hash(avgTemp, devTemp, minTemp, maxTemp);
    }
}
