package org.Model;

import java.util.ArrayList;

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

    @Override
    public void register(BadGuy badGuy) {
        observers.add(badGuy);
    }

    @Override
    public void unregister(BadGuy badGuy) {
        int observerIndex = observers.indexOf(badGuy);
        observers.remove(observerIndex);
    }

    public void notifyObserver() throws PlayerReachedException, FinishedLevelException {
        for(BadGuy badGuy : observers){
            badGuy.update();
        }
    }

    public ArrayList<BadGuy> getObservers() {
        return observers;
    }
}