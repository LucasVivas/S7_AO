package org.Model;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y){
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

    @Override
    public int compareTo(Position position) {
        int dx = this.getX() - position.getX();
        int dy = this.getY() - position.getY();
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

    @Override
    protected Position clone(){
        return new Position(this.getX(), this.getY());
    }
}
