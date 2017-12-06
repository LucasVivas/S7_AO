package org.Model;

import org.View.VBadGuy;
import org.View.View;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class BadGuy extends Character{

    Graph graph = Graph.getInstance();

    public BadGuy(){
        super();
    }

    // Called to update all observers
    public void update(int newX, int newY) throws PlayerReachedException, FinishedLevelException {
        Vertex source = new Vertex(this.getX(), this.getY());
        Vertex target = new Vertex(newX,newY);
        graph.launchManhattan(source,target);
        this.move(graph);
    }

}