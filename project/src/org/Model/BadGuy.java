package org.Model;

/**
 * Class which represent a bad guy.
 * @author Lucas Vivas, Gauthier Lamarque {@literal &} Co.
 * @version 1.0.0
 */
public class BadGuy extends Character{

    /**
     * Create a bad guy with a random position.
     * @see Vertex
     */
    public BadGuy(){
        super();
    }

    /**
     * Move a bad guy to a neighbor vertex which is the closest vertex from the player.
     * @throws PlayerReachedException Throws if the bad guy meet the player
     * @throws FinishedLevelException PAS BON DU TOUT !!!!!!!!!!!!!!
     */
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
     * @param x New x value
     * @param y New y value
     * @throws PlayerReachedException //TODO:casa
     * @throws FinishedLevelException //TODO:casa
     */
    public void update(int x, int y) throws PlayerReachedException, FinishedLevelException {
        Vertex source = new Vertex(this.getX(), this.getY());
        Vertex target = new Vertex(x,y);
        Graph.getInstance().launchManhattan(source,target);
        seekPath();
    }

}