package org.Model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Candy extends Vertex{

    private String name;
    private int nbrPoints;

    public String getName() { return name; }

    public void setName(String newName) { name = newName; }

    public int getNbrPoints() { return nbrPoints; }



    public void setNbrPoints(int nbrPoints){
        this.nbrPoints = nbrPoints;
    }

}
