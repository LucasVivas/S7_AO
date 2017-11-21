package org.Model;

import java.util.Arrays;
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
        CARDINALS_POINTS card;
        int i = 0;
        while (i < size){
             card = randomCardinalsPoints();
            if (Arrays.asList(array).contains(card)) {
                array[i] = card;
                i++;
            }
        }
    }


    private static final CARDINALS_POINTS [] cardinalsPointsArray = CARDINALS_POINTS.values();
    private static Random random = new Random();

    private static CARDINALS_POINTS randomCardinalsPoints(){
        return cardinalsPointsArray[random.nextInt(cardinalsPointsArray.length)];
    }
}
