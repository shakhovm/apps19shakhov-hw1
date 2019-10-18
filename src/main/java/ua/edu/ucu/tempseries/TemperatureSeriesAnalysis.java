package ua.edu.ucu.tempseries;
import java.util.Arrays;
import java.util.InputMismatchException;

/*
Class For Temperature Series Analysis Representation
 */
public class TemperatureSeriesAnalysis {

    /*
    The array of temperatures
     */
    private double[] temperatureSeries;

    /*
    Default constructor
     */
    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[0];
    }

    /*
    Constructor

    params: double[]
     */
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        int badNumber = -273;
        for (double temperature: temperatureSeries)
        {

            if (temperature < badNumber)
            {
                throw new InputMismatchException();
            }
        }

        this.temperatureSeries =
                Arrays.copyOf(temperatureSeries,
                        temperatureSeries.length);
    }

    /*
    Error causer
     */
    private void errorRaiser()
    {
        if (temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /*
    Get Average number of temperatures

    return: double
     */
    public double average() {
        //if array is empty raise exception
        errorRaiser();
        double sum = 0.0;
        for (double temperature: temperatureSeries)
        {
            sum += temperature;
        }

        return sum / temperatureSeries.length;
    }

    /*
    Get Deviation of temperatures

    return: double
     */
    public double deviation() {
        errorRaiser();
        double sigma = 0.0;
        double averageTemperature = average();
        for (double temperature: temperatureSeries)
        {
            sigma += (temperature - averageTemperature)
                    *(temperature - averageTemperature);

        }

        return Math.sqrt(sigma/temperatureSeries.length);
    }

    /*
    Get Min of temperatures

    return: double
     */
    public double min() {
        errorRaiser();
        double minTemp = temperatureSeries[0];
        for (int i = 1; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < minTemp)
            {
                minTemp = temperatureSeries[i];
            }
        }

        return minTemp;
    }

    /*
    Get Max of temperatures

    return: double
     */
    public double max() {
        if (temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double maxTemp = temperatureSeries[0];
        for (int i = 1; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > maxTemp)
            {
                maxTemp = temperatureSeries[i];
            }
        }

        return maxTemp;
    }

    /*
    Get Temperature closest to zero

    return: double
     */
    public double findTempClosestToZero() {

        return findTempClosestToValue(0.0);
    }

    /*
    Get Temperature closest to value

    params: double
    return: double
     */
    public double findTempClosestToValue(double tempValue) {
        errorRaiser();
        double closestToValue = temperatureSeries[0];

        double delta = Math.abs(closestToValue - tempValue);
        for (int i = 1; i < temperatureSeries.length; i++) {
            //if difference equals delta find max of the values
            if (Double.compare(Math.abs(temperatureSeries[i]
                    - tempValue), delta) == 0)
            {
                closestToValue = Math.max(temperatureSeries[i], closestToValue);

            }
            else if (Math.abs(temperatureSeries[i] - tempValue) < delta)
            {
                closestToValue = temperatureSeries[i];
                delta = Math.abs(closestToValue - tempValue);
            }
        }
        return closestToValue;
    }

    /*
    Get Array of temperatures Less Then certain temperature

    params: double
    return: double[]
     */
    public double[] findTempsLessThen(double tempValue) {
        double[] tempsLessThen = new double[temperatureSeries.length];
        int i = 0;
        for (double temperature: temperatureSeries) {
            if (temperature < tempValue)
            {
                tempsLessThen[i++] = temperature;
            }
        }

        return Arrays.copyOf(tempsLessThen, i);
    }

    /*
    Get Array of temperatures Greater Then certain temperature

    params: double
    return: double[]
     */
    public double[] findTempsGreaterThen(double tempValue) {
        double[] tempsGreaterThen = new double[temperatureSeries.length];
        int i = 0;
        for (double temperature: temperatureSeries) {
            if (temperature > tempValue)
            {
                tempsGreaterThen[i++] = temperature;
            }
        }
        return Arrays.copyOf(tempsGreaterThen, i);

    }

    /*
    Get Temperature Summary Statistics


    return: TempSummaryStatistics
     */
    public TempSummaryStatistics summaryStatistics() {
        errorRaiser();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    /*
    Add new temperatures to our array


    return: int
     */
    public int addTemps(double... temps) {
        double[] newTemps = new double[temperatureSeries.length + temps.length];
        int i = 0;
        for (double temperature: temperatureSeries)
        {
            newTemps[i++] = temperature;
        }

        for (double temperature: temps)
        {
            newTemps[i++] = temperature;
        }

        temperatureSeries = newTemps;
        return temperatureSeries.length;
    }

    /*
    return: double[]
     */
    public double[] getTemperatureSeries() {
        return Arrays.copyOf(temperatureSeries,
                temperatureSeries.length);
    }

}
