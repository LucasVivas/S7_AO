package org.Model;

import org.jgrapht.graph.SimpleGraph;

/**
 * Created by lulu (project's boss) on 19/11/17.
 * Graph class represents the labyrinth graph.
 */
public class Graph {
    public static final int TOP_BORDER = 0; //to move
    public static final int BOTTOM_BORDER = 4; //to move
    public static final int LEFT_BORDER = 0; //to move
    public static final int RIGHT_BORDER = 4; //to move

    private SimpleGraph<Vertex,Edge> graph = null;

    private Graph(Vertex origin) {
        graph = new SimpleGraph<>(Edge.class);
        buildLabyrinthGraph(origin);
    }

    private void buildLabyrinthGraph(Vertex v){
        CardinalsPoints c = new CardinalsPoints(4);
        Vertex u;
        for (int i = 0; i < c.getSize(); i++) {
            switch (c.getArrayInd(i)){
                case NORTH:
                    u = new Vertex(v.getX(),v.getY()+1);
                    if (!graph.containsVertex(u) && u.getY() > TOP_BORDER) {
                        graph.addVertex(u);
                        graph.addEdge(v, u);
                        buildLabyrinthGraph(u);
                    }
                    break;
                case EAST:
                    u = new Vertex(v.getX()+1,v.getY());
                    if (!graph.containsVertex(u) && u.getX() > RIGHT_BORDER) {
                        graph.addVertex(u);
                        graph.addEdge(v, u);
                        buildLabyrinthGraph(u);
                    }
                    break;
                case SOUTH:
                    u = new Vertex(v.getX(),v.getY()-1);
                    if (!graph.containsVertex(u) && u.getY() > BOTTOM_BORDER) {
                        graph.addVertex(u);
                        graph.addEdge(v,u);
                        buildLabyrinthGraph(u);
                    }
                    break;
                case WEST:
                    u = new Vertex(v.getX()-1,v.getY());
                    if (!graph.containsVertex(u) && u.getX() > LEFT_BORDER) {
                        graph.addVertex(u);
                        graph.addEdge(v,u);
                        buildLabyrinthGraph(u);
                    }
                    break;
            }
        }
    }

}
