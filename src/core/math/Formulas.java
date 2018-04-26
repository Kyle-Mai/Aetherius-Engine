package core.math;

import java.util.ArrayList;

/**
 * Lolita
 * Jan 15 2018
 * Collection of common math formulas.
 */

public final class Formulas {

    public static double sum(ArrayList<Double> e, Integer exponent) { //gets the sum of the set of integers at the specified power
        double sum = 0;
        for (Double d : e) { sum += Math.pow(d, exponent); }
        return sum;
    }

    public static double mean(ArrayList<Double> e, Integer exponent) { //gets the mean of a set of values multiplied by the specified power
        return (sum(e, exponent) / e.size());
    }

    public static double fibonacciNumber(int index) { //gets the fibonacci number specified by the index
        return Math.floor(Math.pow(1.618, index)+0.5);
    }


}
