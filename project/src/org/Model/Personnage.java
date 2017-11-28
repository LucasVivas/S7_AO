package org.Model;

public abstract class Personnage{

    private int x;
    private int y;

    public enum Type{
        GOOD,
        BAD;
    }private Type type;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Type getType() {
        return type;
    }

}
