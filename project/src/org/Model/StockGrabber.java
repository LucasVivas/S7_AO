package org.Model;

import java.util.ArrayList;

public class StockGrabber {
    private ArrayList<Observer> observers;
    private int newX;
    private int newY;

    public StockGrabber(){
        observers = new ArrayList<>();
    }

    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    public void unregister(Observer deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);
        //System.out.println("Observer " + (observerIndex+1) + " deleted");
        observers.remove(observerIndex);
    }

}
