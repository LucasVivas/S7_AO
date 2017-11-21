package org.Model;

/**
 * Created by lulu on 21/11/17.
 */
public class Vertex implements Comparable<Vertex>{
    private int x;
    private int y;

    public Vertex(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean areNeighbor(Vertex coordinates){
        int dx = Math.abs(this.x - coordinates.x);
        int dy = Math.abs(this.y - coordinates.y);
        return (dx == 0 && dy == 1) || (dx == 1 && dy == 0);
    }

    @Override
    public int compareTo(Vertex v) {
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
}
