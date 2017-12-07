package org.Model;

import org.Controller.Controller;

public class BadGuy extends Character{

    public BadGuy(){
        super();
    }

    public void seekPath() throws PlayerReachedException, FinishedLevelException {
        Vertex vertex = getVertex();
        for (Directions dir : Directions.values()) {
            Vertex next = Graph.getInstance().vertexByDir(vertex, dir);
            if (next != null) {
                if (Graph.getInstance().containsEdge(vertex, next)
                        && (next.getNbr() == vertex.getNbr() - 1)) {
                    super.move(dir);
                }
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
        seekPath();
    }

}