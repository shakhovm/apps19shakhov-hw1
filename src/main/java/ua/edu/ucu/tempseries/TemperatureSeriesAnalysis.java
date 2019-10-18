package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;




public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {

        for(double temperature: temperatureSeries)
        {
            if(temperature < -273)
            {
                throw new InputMismatchException();
            }
        }

        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    private void errorRaiser()
    {
        if(temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
    }

    public double average() {

        errorRaiser();
        double sum = 0.0;
        for(double temperature: temperatureSeries)
        {
            sum += temperature;
        }

        return sum / temperatureSeries.length;
    }

    public double deviation() {
        errorRaiser();
        double sigma = 0.0;
        double averageTemperature = average();
        for(double temperature: temperatureSeries)
        {
            sigma += Math.pow((temperature - averageTemperature), 2);

        }

        return Math.sqrt(sigma/temperatureSeries.length);
    }

    public double min() {
        errorRaiser();
        double min_temp = temperatureSeries[0];
        for (int i = 1; i < temperatureSeries.length; i++) {
            if(temperatureSeries[i] < min_temp)
            {
                min_temp = temperatureSeries[i];
            }
        }

        return min_temp;
    }

    public double max() {
        if(temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double max_temp = temperatureSeries[0];
        for (int i = 1; i < temperatureSeries.length; i++) {
            if(temperatureSeries[i] > max_temp)
            {
                max_temp = temperatureSeries[i];
            }
        }

        return max_temp;
    }

    public double findTempClosestToZero() {


        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        errorRaiser();
        double closestToValue = temperatureSeries[0];

        double delta = Math.abs(closestToValue - tempValue);
        for (int i = 1; i < temperatureSeries.length; i++) {
            if(Math.abs(temperatureSeries[i] - tempValue) == delta)
            {
                closestToValue = Math.max(temperatureSeries[i], closestToValue);

            }
            else if(Math.abs(temperatureSeries[i] - tempValue) < delta)
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
        for(double temperature: temperatureSeries)
        {
            newTemps[i++] = temperature;
        }


        for(double temperature: temps)
        {
            newTemps[i++] = temperature;
        }


        temperatureSeries = newTemps;


        return temperatureSeries.length;
    }

    public double[] getTemperatureSeries() {
        return temperatureSeries;
    }

    public void setTemperatureSeries(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
    }
}
