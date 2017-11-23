package org.Model;

/**
 * Created by lulu on 21/11/17.
 */
public class Vertex implements Comparable<Vertex>{
    public static final int TOP_BORDER = 0;
    public static final int BOTTOM_BORDER = 4;
    public static final int LEFT_BORDER = 0;
    public static final int RIGHT_BORDER = 4;

    private Position pos;
    private int nbr;

    public Vertex(int x, int y, int nbr){
        pos = new Position(x,y);
        this.nbr = nbr;
    }

    public Vertex(Position position, int nbr){
        pos = new Position(position.getX(),position.getY());
        this.nbr = nbr;
    }

    public void setX(int x) {
        pos.setX(x);
    }

    public void setY(int y) { pos.setY(y); }

    public int getX() {
        return pos.getX();
    }

    public int getY() {
        return pos.getY();
    }

    public int getNbr() { return nbr; }

    public Position getPos() { return pos.clone(); }

    public boolean areNeighbor(Vertex coordinates){
        int dx = Math.abs(this.getX() - coordinates.getX());
        int dy = Math.abs(this.getY() - coordinates.getY());
        return (dx == 0 && dy == 1) || (dx == 1 && dy == 0);
    }

    @Override
    public int compareTo(Vertex v) {
        return this.pos.compareTo(v.getPos());

    }

    public boolean inBorders(Directions dir){
        return false;
    }

}
