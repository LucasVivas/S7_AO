package org.Model;

public class Door extends Vertex {
    private static Door door = null;

    private Door(int x, int y) {
        super(x, y);
    }

    public static Door getInstance(){
        if (door == null){
            Player player = Player.getInstance();
            Vertex v = Graph.getInstance().furthestVertex(player);
            door = new Door(v.getX(),v.getY());
        }
        return door;
    }

}