package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {


    private TemperatureSeriesAnalysis seriesAnalysis;
    private TemperatureSeriesAnalysis seriesAnalysisWithOneElement;
    private TemperatureSeriesAnalysis seriesAnalysisWithEmptyArray;
    @Before
    public void setUp() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        double[] temperatureSeriesWithOneElement = {-1.0};
        double[] temperatureSeriesWithEmptyArray = {};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysisWithOneElement = new TemperatureSeriesAnalysis(temperatureSeriesWithOneElement);
        seriesAnalysisWithEmptyArray = new TemperatureSeriesAnalysis(temperatureSeriesWithEmptyArray);
    }

    @Test(expected = InputMismatchException.class)
    public void testBadInit() {
        double[] temperatureSeries = {-273, -275, 0};
        TemperatureSeriesAnalysis badSeriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testAverageWithOneElementArray() {

        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysisWithOneElement.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {

        // expect exception here
        seriesAnalysisWithEmptyArray.average();
    }


    @Test
    public void testAverage() {
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }


    @Test
    public void testDeviationWithOneElementArray() {
        double expResult = 0.0;

        double actualResult = seriesAnalysisWithOneElement.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {


        double actualResult = seriesAnalysisWithEmptyArray.deviation();

    }


    @Test
    public void testDeviation() {
        double expResult = 3.7416573867739413;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testMinWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesAnalysisWithOneElement.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {


        double actualResult = seriesAnalysisWithEmptyArray.min();

    }


    @Test
    public void testMin() {
        double expResult = -5.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testMaxWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesAnalysisWithOneElement.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {


        double actualResult = seriesAnalysisWithEmptyArray.max();

    }


    @Test
    public void testMax() {
        double expResult = 5.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValueWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesAnalysisWithOneElement.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {

        double value = 3.0;
        double actualResult = seriesAnalysisWithEmptyArray.findTempClosestToValue(value);

    }


    @Test
    public void testFindTempClosestToValue() {
        double expResult = 3.0;

        double value = 2.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);


        double[] newSeries = {-2.0, 2.0, 4.0};
        TemperatureSeriesAnalysis newAnalysis = new TemperatureSeriesAnalysis(newSeries);


        double newExpResult = 4.0;

        double newValue = 3.0;
        double newActualResult = newAnalysis.findTempClosestToValue(newValue);


        assertEquals(newExpResult, newActualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesAnalysisWithOneElement.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {


        double actualResult = seriesAnalysisWithEmptyArray.findTempClosestToZero();

    }


    @Test
    public void testFindTempClosestToZero() {
        double expResult = 1.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);


        double[] newSeries = {-2.0, 2.0, 5.0};
        TemperatureSeriesAnalysis newAnalysis = new TemperatureSeriesAnalysis(newSeries);

        double newExpResult = 2.0;
        double newActualResult = newAnalysis.findTempClosestToZero();


        assertEquals(newExpResult, newActualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThenWithOneElementArray() {

        double value = -2.0;

        double[] expResult = {};

        double[] actualResult = seriesAnalysisWithOneElement.findTempsLessThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThenWithEmptyArray() {

        double value = 5.0;

        double[] expArray = {};
        double[] actualResult = seriesAnalysisWithEmptyArray.findTempsLessThen(value);

        assertArrayEquals(expArray, actualResult, 0.0001);

    }


    @Test
    public void testFindTempsLessThen() {
        double[] expResult = {3.0, -5.0, 1.0};

        double value = 5.0;

        double[] actualResult = seriesAnalysis.findTempsLessThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThenWithOneElementArray() {

        double value = -2.0;

        double[] expResult = {-1.0};

        double[] actualResult = seriesAnalysisWithOneElement.findTempsGreaterThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThenWithEmptyArray() {

        double value = 5.0;
        double[] expArray = {};
        double[] actualResult = seriesAnalysisWithEmptyArray.findTempsGreaterThen(value);
        assertArrayEquals(expArray, actualResult, 0.0001);

    }


    @Test
    public void testFindTempsGreaterThen() {
        double[] expResult = {5.0};

        double value = 3.0;

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void testSummaryStatisticsWithOneElementArray() {

        TempSummaryStatistics expResult = new TempSummaryStatistics
                (
                -1.0,
                0.0,
                -1.0,
                -1.0
                );

        TempSummaryStatistics actualResult = seriesAnalysisWithOneElement.summaryStatistics();

        assertEquals(expResult, actualResult);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptyArray() {


        TempSummaryStatistics actualResult = seriesAnalysisWithEmptyArray.summaryStatistics();

    }


    @Test
    public void testSummaryStatistics() {
        TempSummaryStatistics expResult = new TempSummaryStatistics
                (
                1.0,
                3.7416573867739413,
                -5.0,
                5.0
                );

        TempSummaryStatistics actualResult = seriesAnalysis.summaryStatistics();

        assertEquals(expResult, actualResult);
    }

    @Test
    public void testAddTempsWithOneElement() {
        int expResult = 3;
        int actualResult = seriesAnalysisWithOneElement.addTemps(6.0, 7.0);

        assertEquals(expResult, actualResult);

        double[] expArray = {-1.0, 6.0, 7.0};

        double[] actualArray = seriesAnalysisWithOneElement.getTemperatureSeries();

        assertArrayEquals(expArray, actualArray, 0.0001);
    }

    @Test
    public void testAddTempsWithEmptyArray() {
        int expResult = 2;
        int actualResult = seriesAnalysisWithEmptyArray.addTemps(6.0, 7.0);

        assertEquals(expResult, actualResult);

        double[] expArray = {6.0, 7.0};
        double[] actualArray = seriesAnalysis.getTemperatureSeries();

        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testAddTemps() {
        int expResult = 6;
        int actualResult = seriesAnalysis.addTemps(6.0, 7.0);

        assertEquals(expResult, actualResult);

        double[] expArray = {3.0, -5.0, 1.0, 5.0, 6.0, 7.0};
        double[] actualArray = seriesAnalysis.getTemperatureSeries();

        assertEquals(expResult, actualResult, 0.0001);
    }
}
