package org.Model;

import java.util.Iterator;
import java.util.Random;

public class Character extends Vertex{
    public Character(){
        super();
    }

    public Character(int x, int y) {
        super(x, y);
    }

    public void moove() {
    	
    }
    
    public boolean move(Directions direction) {
        Iterator<Edge> graphEdgesIterator = Graph.getInstance().edgeSet().iterator();
        Edge E;
        int x_source = getX();
        int y_source = getY();
        int xTmp = 0;
        int yTmp = 0;

        if (!new Vertex(x_source, y_source).inBorders(direction))
            return false;

        switch (direction) {
            case NORTH:
                yTmp = -1;
                break;
            case SOUTH:
                yTmp = 1;
                break;
            case EAST:
                xTmp = 1;
                break;
            case WEST:
                xTmp = -1;
                break;
        }
        
        while (graphEdgesIterator.hasNext()) {
            E = graphEdgesIterator.next();
            if (((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                    && (E.getTarget().getX() == x_source + xTmp && E.getTarget().getY() == y_source + yTmp))
                    || ((E.getSource().getX() == x_source + xTmp && E.getSource().getY() == y_source + yTmp)
                    && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {
                setX(getX() + xTmp);
                setY(getY() + yTmp);
                return true;
            }
        }
        return false;
    }
}

