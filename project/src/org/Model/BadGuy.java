package org.Model;

public class BadGuy extends Character{
    public BadGuy(int x, int y){
        super(x,y);
    }

    @Override
    public boolean move(Directions direction) {
        return false;
    }

}