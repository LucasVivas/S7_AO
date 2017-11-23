package org.Model;

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

    public Vertex(int x, int y, int nbr){
        this.x = x;
        this.y = y;
        this.nbr = nbr;
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

    public boolean areNeighbor(Vertex v){
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
        else {
            if (dy < 0)
                return -1;
            else if (dy > 0)
                return 1;
        }
        return 0;
    }

    public boolean inBorders(Directions dir){
        return false;
    }

    protected Vertex clone(){
        return new Vertex(this.getX(), this.getY(), this.getNbr());
    }

}
