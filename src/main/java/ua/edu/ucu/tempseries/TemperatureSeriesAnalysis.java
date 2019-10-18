package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;




public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {

        for (double temperature: temperatureSeries)
        {
            int badNumber = -273;

            if (temperature < badNumber)
            {
                throw new InputMismatchException();
            }
        }

        this.temperatureSeries =
                Arrays.copyOf(temperatureSeries,
                        temperatureSeries.length);
    }

    private void errorRaiser()
    {
        if (temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
    }

    public double average() {

        errorRaiser();
        double sum = 0.0;
        for (double temperature: temperatureSeries)
        {
            sum += temperature;
        }

        return sum / temperatureSeries.length;
    }

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

    public double min() {
        errorRaiser();
        double minTemp = temperatureSeries[0];
        for (int i = 1; i < temperatureSeries.length; i++) {
            if(temperatureSeries[i] < minTemp)
            {
                minTemp = temperatureSeries[i];
            }
        }

        return minTemp;
    }

    public double max() {
        if (temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double maxTemp = temperatureSeries[0];
        for (int i = 1; i < temperatureSeries.length; i++) {
            if(temperatureSeries[i] > maxTemp)
            {
                maxTemp = temperatureSeries[i];
            }
        }

        return maxTemp;
    }

    public double findTempClosestToZero() {


        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        errorRaiser();
        double closestToValue = temperatureSeries[0];

        double delta = Math.abs(closestToValue - tempValue);
        for (int i = 1; i < temperatureSeries.length; i++) {
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

    public double[] findTempsLessThen(double tempValue) {
        double[] tempsLessThen = new double[temperatureSeries.length];
        int i = 0;
        for (double temperature: temperatureSeries) {
            if(temperature < tempValue)
            {
                tempsLessThen[i++] = temperature;
            }
        }

        return Arrays.copyOf(tempsLessThen, i);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] tempsGreaterThen = new double[temperatureSeries.length];
        int i = 0;
        for (double temperature: temperatureSeries) {
            if(temperature > tempValue)
            {
                tempsGreaterThen[i++] = temperature;
            }
        }
        return Arrays.copyOf(tempsGreaterThen, i);

    }

    public TempSummaryStatistics summaryStatistics() {
        errorRaiser();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

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

    public double[] getTemperatureSeries() {
        return temperatureSeries;
    }

}
