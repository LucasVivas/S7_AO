package org.Model;

/**
 * Class of the final door
 * @author Lucas VIVAS, Gauthier Lamarque {@literal &} Co.
 * @version 1.0.0
 */
public class Door extends Vertex {
    private static Door door = null;

    private Door(int x, int y) {
        super(x, y);
    }

    /**
     * get the single instance of door
     * @return A new door if it doesn't exist, else the only instance of door.
     * The door's position is the furthest vertex from the player.
     */
    public static Door getInstance(){
        if (door == null){
            Player player = Player.getInstance();
            Vertex v = Graph.getInstance().furthestVertex(player);
            door = new Door(v.getX(),v.getY());
        }
        return door;
    }

}

