package core.math;

/*
Lolita's Revenge
June 30 2017

Handles randomized numbers and whatnot.
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class WeightedRandom {

    /*------------------------------------------------------------------------------------------------------------------
     Variables.
     Used to determine the function of the class.
     */

    private ArrayList<Integer> weightedArray = new ArrayList<>();
    private int low, high;
    private double sigma = 2.0;

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the class.
     */

    public WeightedRandom() {}

    public WeightedRandom(int i1, int i2) {
        low = i1;
        high = i2;
    }

    public WeightedRandom(int i1, int i2, double sig) {
        low = i1;
        high = i2;
        sigma = sig;
    }

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Can be accessed outside of the class to edit it's characteristics.
     */

    public int getLow() { return low; }
    public int getHigh() { return high; }
    public double getSigma() { return sigma; }
    public int getRange() { return high - low; }

    public void setLow(int i) { low = i; }
    public void setHigh(int i) { high = i; }
    public void setSigma(double s) { sigma = s; }

    public double getMedian() { return (low + high) / 2; }
    public double getMean() {
        int temp = 0;
        for (int i = low; i <= high; i++) { temp += i; }
        return temp / getRange();
    }

    public double getRandom() {
        Random r = new Random();
        return low + r.nextInt(high);
    }

    public int getWeightedRandom() {
        Random r = new Random();
        return weightedArray.get(r.nextInt(weightedArray.size()));
    }

    public void setWeightedArray(int decimal) {
        weightedArray.clear();
        double temp;
        for (int i = low; i <= high; i++) {
            temp = getDeviation(i, decimal) * 100;
            for (int j = 0; j < temp; j++) {
                weightedArray.add(i);
            }
            //System.out.println(temp + " at " + i);
        }
        //System.out.println("Done. " + weightedArray.size());
    }

    /*------------------------------------------------------------------------------------------------------------------
     Core methods.
     These should NOT be touched or accessed directly.
     */

    private double getDeviation(int x, int decimal) {

        if (decimal < 0) throw new IllegalArgumentException(); //We can't have less than 0 decimal points. Throw an exception if this happens.
        Random r = new Random();
        //val = Math.pow(((1 / sigma * (Math.sqrt(2 * Math.PI))) * Math.E), -(Math.pow(((((low * Math.pow(10, decimal)) + r.nextInt((high * (int)Math.pow(10, decimal)))) / Math.pow(10, decimal)) - getMean()), 2) / (2 * Math.pow(sigma, 2)))); //OLD FORMULA
        BigDecimal rounded = new BigDecimal(
                Math.pow(
                        //base
                        ((1 / sigma * (Math.sqrt(2 * Math.PI))) * Math.E),
                        //exponent
                        -(Math.pow(x - getMedian(), 2)
                                / (2 * Math.pow(sigma, 2))
                        )
                )
        );
        rounded = rounded.setScale(decimal, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }

    //------------------------------------------------------------------------------------------------------------------

}
