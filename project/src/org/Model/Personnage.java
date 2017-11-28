package org.Model;

public abstract class Personnage{

    protected int x;
    protected int y;
    public enum Type{
        GOOD,
        BAD;
    }private Type type;

    protected Personnage(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

}
