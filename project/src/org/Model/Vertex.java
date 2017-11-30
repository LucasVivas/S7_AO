package org.Model;

import java.util.Random;

/**
 * Created by lulu on 21/11/17.
 */
public class Vertex implements Comparable<Vertex>, Cloneable{

    private int x;
    private int y;
    private int nbr;

    public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}

    public Vertex(int x, int y, int nbr) {
        super();
        this.x = x;
        this.y = y;
        this.nbr = nbr;
    }

    public Vertex(Vertex vertex){
        this.x = vertex.getX();
        this.y = vertex.getY();
        this.nbr = vertex.getNbr();
    }
    public int getX(){
        return x;
    }

	public int getY(){
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
                return getY()-1>=0;
            case SOUTH:
                return getY()+1<Model.getHEIGHT();
            case EAST:
                return getX()+1<Model.getWIDTH();
            case WEST:
                return getX()-1>=0;
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



    @Override
    public String toString(){
        return "("+getX()+", "+getY()+ ")";
    }

    @Override
    public boolean equals(Object obj){
        if (obj.getClass() != Vertex.class)
            return false;
        Vertex v = (Vertex)obj;
        return this.getX() == v.getX() && this.getY() == v.getY();
    }

}