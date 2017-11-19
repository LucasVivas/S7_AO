package org.Model;

/**
 * Created by lulu on 19/11/17.
 */
public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean areNeighbor(Coordinates coordinates){
        int dx = Math.abs(this.x - coordinates.x);
        int dy = Math.abs(this.y - coordinates.y);
        return (dx == 0 && dy == 1) || (dx == 1 && dy == 0);
    }

}
