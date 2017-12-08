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
     * @throws PlayerReachedException Throws if the bad guy meet the player.
     */
    private void seekPath() throws PlayerReachedException {
        Vertex vertex = getVertex();
        boolean b;
        for (Directions dir : Directions.values()) {
            b = false;
            Vertex next = Graph.getInstance().vertexByDir(vertex, dir);
            if (next != null) {
                for (BadGuy observer:Player.getInstance().getObservers()) {
                    if (next.equals(observer.getVertex()))
                        b = true;
                }
                if (b)
                    continue;
                if (next.equals(Player.getInstance().getVertex()) || equals(Player.getInstance().getVertex()))
                    throw new PlayerReachedException();
                if (Graph.getInstance().containsEdge(vertex, next)
                        && (next.getNbr() == vertex.getNbr() - 1)) {
                    try {
                        super.move(dir);
                    }catch (FinishedLevelException e){
                    }
                    break;
                }
            }
        }
    }

    /**
     * Called to update all observers.
     * @throws PlayerReachedException Throws if the bad guy meet the player.
     */
    public void update() throws PlayerReachedException {
        Vertex source = new Vertex(this.getX(), this.getY());
        Vertex target = new Vertex(Player.getInstance().getX(),Player.getInstance().getY());
        Graph.getInstance().launchManhattan(source,target);
        seekPath();
    }

}