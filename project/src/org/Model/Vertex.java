package org.Model;

import java.util.Random;

/**
 * Created by lulu on 21/11/17.
 */
public class Vertex implements Comparable<Vertex>{
    public static final int TOP_BORDER = 0;
    public static final int BOTTOM_BORDER = 4;
    public static final int LEFT_BORDER = 0;
    public static final int RIGHT_BORDER = 4;

    private int x;
    private int y;
    private int nbr;
    private Random rnX;
    private Random rnY;

    public Vertex(int x, int y, int nbr){
        this.x = x;
        this.y = y;
        this.nbr = nbr;
    }

    public Vertex(){
        rnX = new Random();
        this.x = rnX.nextInt(RIGHT_BORDER-1);
        rnY = new Random();
        this.y = rnY.nextInt(BOTTOM_BORDER-1);
        this.nbr = 0;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getNbr(){
        return nbr;
    }

    public boolean areNeighbors(Vertex v){
        int dx = Math.abs(this.getX() - v.getX());
        int dy = Math.abs(this.getY() - v.getY());
        return (dx == 0 && dy == 1) || (dx == 1 && dy == 0);
    }

    @Override
    public int compareTo(Vertex v){
        int dx = this.getX() - v.getX();
        int dy = this.getY() - v.getY();
        if (dx < 0)
            return -1;
        else if (dx > 0)
            return 1;
        else if (dy < 0)
            return -2;
        else if (dy > 0)
            return 2;
        return 0;
    }

    public boolean inBorders(Directions dir){
        switch (dir){
            case NORTH:
                return getY()-1>TOP_BORDER;
            case SOUTH:
                return getY()+1<BOTTOM_BORDER;
            case EAST:
                return getX()+1<RIGHT_BORDER;
            case WEST:
                return getX()-1>LEFT_BORDER;
        }
        return false;
    }

    public Vertex clone(){
        Vertex v;
        try{
            v = (Vertex) super.clone();
        }
        catch (CloneNotSupportedException e){
            throw new Error();
        }
        return v;
    }

    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (obj.getClass() != Vertex.class)
            return false;
        Vertex v = (Vertex)obj;
        return this.getX()==v.getX() && this.getY()==v.getY();
    }

}