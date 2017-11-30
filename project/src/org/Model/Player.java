package org.Model;

import java.util.Iterator;

public class Player extends Character{
    public Player(int x, int y) {
        super(x, y);
    }

    public boolean move(Directions directions){
        return super.move(directions);
    }
}