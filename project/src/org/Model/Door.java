package org.Model;

public class Door extends Vertex {
    private static Door door = null;

    public Door() {
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

    public static Door getDoor(){return door;}

}
