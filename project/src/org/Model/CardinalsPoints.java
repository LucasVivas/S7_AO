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
    private int size;

    public CardinalsPoints(int size) {
        this.size = size;
        array = new CARDINALS_POINTS[size];
        this.shuffle();
    }

    public CARDINALS_POINTS getArrayInd(int i) {
        return array[i];
    }

    public int getSize() {
        return size;
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
