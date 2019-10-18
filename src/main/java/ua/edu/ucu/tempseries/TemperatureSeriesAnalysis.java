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

    private interface CompareTwoDoubles {
        boolean compare(double a, double b);
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

    Return temp by comparing with other temps
    params: CompareTwoDoubles
    return: double
     */
    private double findValueByComparing(CompareTwoDoubles comparator)
    {
        errorRaiser();
        double value = temperatureSeries[0];
        for (int i = 1; i < temperatureSeries.length; i++) {
            if (comparator.compare(temperatureSeries[i], value))
            {
                value = temperatureSeries[i];
            }
        }

        return value;
    }

    /*
    Get Min of temperatures

    return: double
     */
    public double min() {
        return findValueByComparing((a, b) -> a < b);
    }

    /*
    Get Max of temperatures

    return: double
     */
    public double max() {
        return findValueByComparing((a, b) -> a < b);
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
    Get Array of temperatures by comparing with certain temperature

    params: double
    return: double[]
     */
    public double[] findTempsByComparing(
            CompareTwoDoubles comparator,
            double tempValue)
    {
        double[] newTemps = new double[temperatureSeries.length];
        int i = 0;
        for (double temperature: temperatureSeries) {
            if (comparator.compare(temperature, tempValue))
            {
                newTemps[i++] = temperature;
            }
        }

        return Arrays.copyOf(newTemps, i);
    }

    /*
    Get Array of temperatures Less Then certain temperature

    params: double
    return: double[]
     */
    public double[] findTempsLessThen(double tempValue) {
        return findTempsByComparing((a, b) -> a < b, tempValue);
    }

    /*
    Get Array of temperatures Greater Then certain temperature

    params: double
    return: double[]
     */
    public double[] findTempsGreaterThen(double tempValue) {
        return findTempsByComparing((a, b) -> a > b, tempValue);
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
