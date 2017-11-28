package org.Model;

import java.util.ArrayList;

public class StockGrabber implements Subject{

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

    public void notifyObserver() {
        for(Observer observer : observers){
            observer.update(newX, newY);
        }
    }

    public void hasMoved(int x, int y){
        newX = x;
        newY = y;
        //manhattanDistance(x,y);
        notifyObserver();
    }
}
