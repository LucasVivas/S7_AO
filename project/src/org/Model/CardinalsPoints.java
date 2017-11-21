package org.Model;

import java.util.Random;

/**
 * Created by lulu on 19/11/17.
 * Class for CardinalsPoints
 */
public class CardinalsPoints {
    public enum CARDINALS_POINTS {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
    private CARDINALS_POINTS[] array;

    public CardinalsPoints() {
        array = new CARDINALS_POINTS[4];
        this.shuffle();
    }

    public CARDINALS_POINTS[] getArray() {
        return array;
    }

    public void shuffle(){
        int i = 0;
        boolean bool = true;
        while (i < 4){
            array[i] = randomCardinalsPoints();
            for (int j = 0; j < i; j++) {
                if (array[i].compareTo(array[j]) == 0)
                    bool = false;
            }
            if (bool)
                i++;
        }
    }


    private static final CARDINALS_POINTS [] cardinalsPointsArray = CARDINALS_POINTS.values();
    private static Random random = new Random();

    private static CARDINALS_POINTS randomCardinalsPoints(){
        return cardinalsPointsArray[random.nextInt(cardinalsPointsArray.length)];
    }
}
