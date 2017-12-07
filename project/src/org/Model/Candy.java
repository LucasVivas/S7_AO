package org.Model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Candy extends Vertex{

    private String name;
    private double nbrPoints;

    public String getName() { return name; }

    public void setName(String newName) { name = newName; }

    public double getNbrPoints() { return nbrPoints; }

    public void setNbrPoints(int nbrPoints){
        this.nbrPoints = nbrPoints;
    }

/*    public ArrayList<Candy> randomCandyList(){
        int nbCandies = new Random().nextInt(5);
        ArrayList<Candy> candyList = new ArrayList<>(nbCandies);
        CandyFactory candyFactory = new CandyFactory();
        for(int i=0 ; i<nbCandies ; i++){
            int typeOfCandy = new Random().nextInt(4);
            Candy candy = candyFactory.makeCandy(typeOfCandy);
            candyList.add(candy);
        }
        return candyList;
    }*/

    public void collectedCandy() {
        System.out.println(getName() + " is a candy and gives " + getNbrPoints() + " points when collecting it !");
    }

}
