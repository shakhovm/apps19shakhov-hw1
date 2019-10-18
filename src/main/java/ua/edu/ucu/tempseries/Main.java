package ua.edu.ucu.tempseries;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        double[] temp = {1.0, 2.0, 3.0, -1.0};
        TemperatureSeriesAnalysis t = new TemperatureSeriesAnalysis(temp);
        System.out.println(t.average());
        System.out.println(t.max());
        System.out.println(t.min());
        System.out.println(t.findTempClosestToZero());
        System.out.println(t.deviation());

    }
}
