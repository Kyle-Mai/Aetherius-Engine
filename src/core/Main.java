package core;

import core.math.WeightedRandom;

public class Main {

    public static void main(String[] args) {

        WeightedRandom wr = new WeightedRandom(5, 25, 2);

        System.out.println(wr.getWeightedRandom(4));


    }


}
