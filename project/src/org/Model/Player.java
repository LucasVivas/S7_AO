package org.Model;

public class Player extends Character{


    private  int x;
    private  int y;
    private Type type;


    public Player(int x, int y) {
        super(x,y, Type.GOOD);
    }

}