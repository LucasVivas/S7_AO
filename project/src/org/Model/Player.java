package org.Model;

import java.util.ArrayList;

/**
 * Class <b>Player</b> represent the player
 */
public class Player extends Character implements Subject{
    private static Player player = null;
    private static ArrayList<BadGuy> observers;

    private Player(int x, int y) {
        super(x, y);
    }

    /**
     * Singleton
     * @return Return the instance of <b>player</b> and create one if it doesn't exist
     */
    public static Player getInstance(){
        if (player == null){
            player = new Player(0,0);
            observers = new ArrayList<>();
        }
        return player;
    }

    /**
     * Add a <b>Badguy</b> to the observer list
     * @param badGuy the badguy to be add
     */
    @Override
    public void register(BadGuy badGuy) {
        observers.add(badGuy);
    }

    /**
     * Remove a <b>Badguy</b> to the observer list
     * @param badGuy the badguy to be remove
     */
    @Override
    public void unregister(BadGuy badGuy) {
        int observerIndex = observers.indexOf(badGuy);
        observers.remove(observerIndex);
    }

    /**
     * Update all the observer (badguy) position
     * @throws PlayerReachedException if a bad guy meet the player
     */
    public void notifyObserver() throws PlayerReachedException {
        for(BadGuy badGuy : observers){
            badGuy.update();
        }
    }

    /**
     *  Return the list of the observers
     * @return Return the list of the observers
     */
    public ArrayList<BadGuy> getObservers() {
        return observers;
    }
}