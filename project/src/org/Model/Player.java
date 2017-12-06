package org.Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Character implements Subject{
    private static Player player = null;
    private static ArrayList<BadGuy> observers;

    private Player(int x, int y) {
        super(x, y);
    }

    public static Player getInstance(){
        if (player == null){
            player = new Player(0,0);
            observers = new ArrayList<>();
        }
        return player;
    }

    public static Character getPlayer() {
        return player;
    }

    @Override
    public void register(BadGuy badGuy) {
        observers.add(badGuy);
    }

    @Override
    public void unregister(BadGuy badGuy) {
        int observerIndex = observers.indexOf(badGuy);
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver(int x, int y) throws PlayerReachedException, FinishedLevelException {
        for(BadGuy badGuy : observers){
            badGuy.update(x, y);
        }
    }

    public ArrayList<BadGuy> getObservers() {
        return observers;
    }
}