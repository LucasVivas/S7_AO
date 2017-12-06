package org.Model;

public class StockObserver implements Observer{

    private int newX;
    private int newY;

    // Static used as a counter
    private static int observerIDTracker = 0;

    // Used to track the observers
    private int observerID;

    // Will hold reference to the StockGrabber object
    private Subject stockGrabber;

    public StockObserver(Subject stockGrabber){
        this.stockGrabber = stockGrabber;
        this.observerID = ++observerIDTracker;
//        stockGrabber.register(this);
    }

    // Called to update all observers
    public void update(int newX, int newY){
        this.newX = newX;
        this.newY = newY;
        //launchManhattan(x,y);
//        printNewCoords();
    }

/*private void printNewCoords(){
        System.out.println(observerID + "\nnewX: " + newX + "\nnewY: " + newY + "\n");
    }*/

}
