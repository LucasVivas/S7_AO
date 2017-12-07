package org.Model;

import java.util.Random;

/**
 * Class of vertices.
 * @author Lucas VIVAS, Gauthier Lamarque {@literal &} Co.
 * @version 1.0.0
 */
public class Vertex implements Comparable<Vertex>, Cloneable{

    private int x;
    private int y;
    private int nbr;

    /**
     * @return The number of the vertex.
     */
    public int getNbr() {
		return nbr;
	}

    /**
     * Set the param nbr to nbr.
     * @param nbr new number value.
     */
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

    /**
     * Build a <b>Vertex</b> with a random position and the number equals to 0.
     */
    public Vertex(){
        Random random = new Random();
        this.x = random.nextInt(MConsts.WIDTH);
        this.y = random.nextInt(MConsts.HEIGHT);
    }

    /**
     * Build a Vertex and set the position to (x,y) with the number equals to 0.
     * @param x New x position.
     * @param y New y position.
     */
	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
		this.nbr = 0;
	}

    /**
     * Build a Vertex and set the position to (x,y) with the number equals to nbr.
     * @param x New x value
     * @param y New y value
     * @param nbr New number value
     */
    public Vertex(int x, int y, int nbr) {
        super();
        this.x = x;
        this.y = y;
        this.nbr = nbr;
    }

    /**
     * Build a Vertex with the parameter of another Vertex.
     * @param vertex Vertex with all the new parameter
     */
    public Vertex(Vertex vertex){
        this.x = vertex.getX();
        this.y = vertex.getY();
        this.nbr = vertex.getNbr();
    }


    /**
     * @return The value of the parameter x.
     */
    public int getX(){
        return x;
    }

    /**
     * @return The value of the parameter y.
     */
	public int getY(){
        return y;
    }

    /**
     * Set the parameter x to x.
     * @param x New x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the parameter y to y.
     * @param y New y value
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * @param v Another vertex
     * @return True if this Vertex and v are neighbors.
     */
    public boolean areNeighbors(Vertex v){
        int dx = Math.abs(this.getX() - v.getX());
        int dy = Math.abs(this.getY() - v.getY());
        return (dx == 0 && dy == 1) || (dx == 1 && dy == 0);
    }

    /**
     * @param v Another vertex
     * @return 0 If the vertex have the same position.
     */
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

    /**
     * @param dir The direction to the new vertex
     * @return True if the vertex to the direction is in the border of the maze.
     */
    public boolean inBorders(Directions dir){
        switch (dir){
            case NORTH:
                return getY()-1<0;
            case SOUTH:
                return getY()+1>=Model.getHEIGHT();
            case EAST:
                return getX()+1>=Model.getWIDTH();
            case WEST:
                return getX()-1<0;
        }
        return false;
    }

    /**
     *
     * @return A copy of the vertex.
     */
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


    /**
     * To string
     * @return The <b>Vertex</b> in string in the format : "(x,y)"
     */
    @Override
    public String toString(){
        return "("+getX()+", "+getY()+ ") = " +getNbr();
    }


    /**
     * Equals
     * @return True if the position (x,y) of the both vertex are equals, else return False.
     */
    @Override
    public boolean equals(Object obj){
        if (obj.getClass() != Vertex.class)
            return false;
        Vertex v = (Vertex)obj;
        return this.getX() == v.getX() && this.getY() == v.getY();
    }
}