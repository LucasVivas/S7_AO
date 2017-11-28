package org.Model;

public class BadGuy extends Personnage {

    private  int x;
    private  int y;
    private Type type;

    public BadGuy(int x, int y){
        this.x = x;
        this.y = y;
        this.type = Type.BAD;
    }

}