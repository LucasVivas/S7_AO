package org.Model;

import org.View.VPlayer;

public class Player extends Personnage {

    private  int x;
    private  int y;
    private Type type;


    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = Type.GOOD;
    }

}