package org.Model;

import java.util.Iterator;

public class Player extends Character{
    private static Player player = null;

    private Player(int x, int y) {
        super(x, y);
    }

    public static Player getInstance(){
        if (player == null){
            player = new Player(0,0);
        }
        return player;
    }

    public boolean move(Directions directions){
        return super.move(directions);
    }
}