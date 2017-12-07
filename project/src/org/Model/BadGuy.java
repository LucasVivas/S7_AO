package org.Model;

import org.View.VBadGuy;
import org.View.View;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class BadGuy extends Character{

    public BadGuy(){
        super();
    }

    public void move() throws PlayerReachedException, FinishedLevelException {
        Vertex vertex = this.getVertex(Model.getInstance().getGraph());
        for (Directions dir : Directions.values()) {
            Vertex next = Graph.getInstance().vertexByDir(vertex, dir);
            if (Graph.getInstance().containsEdge(vertex, next)
                    && (next.getNbr()== vertex.getNbr()-1) ){
                super.move(dir);
            }
        }
    }

    /**
     * Called to update all observers
     */
    public void update(int newX, int newY) throws PlayerReachedException, FinishedLevelException {
        Vertex source = new Vertex(this.getX(), this.getY());
        Vertex target = new Vertex(newX,newY);
        Graph.getInstance().launchManhattan(source,target);
        this.move();
    }

}