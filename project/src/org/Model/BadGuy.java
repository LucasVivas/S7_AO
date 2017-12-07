package org.Model;

import org.Controller.Controller;

public class BadGuy extends Character{

    public BadGuy(){
        super();
    }

    public void seekPath() throws PlayerReachedException, FinishedLevelException {
        Vertex vertex = Model.getInstance().getGraph().getVertex(getX(), getY());
        for (Directions dir : Directions.values()) {
            Vertex next = Graph.getInstance().vertexByDir(vertex, dir);
            if (next != null) {
                if (Graph.getInstance().containsEdge(vertex, next)
                        && (next.getNbr() == vertex.getNbr() - 1)) {
                    super.move(dir);
                    break;
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