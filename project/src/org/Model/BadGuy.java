package org.Model;

import java.util.Random;

public class BadGuy extends Character{
    public BadGuy() {//TODO: probleme normalement posibilite de le faire que dans characters et pas dans vertex
        super(0,0);
        Player player = Player.getInstance();
        Vertex v;
        try {
            v = Graph.getInstance().furthestVertex(player);
        }catch (VertexNotInGraphException e){
            System.err.println("The player is not in the graph");
            v = new Vertex(0,0);
        }
        super.setX(v.getX());
        super.setY(v.getY());
    }

}