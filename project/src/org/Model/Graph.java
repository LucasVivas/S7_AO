package org.Model;

import org.jgrapht.graph.DefaultEdge;
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

    private SimpleGraph<Vertex,DefaultEdge> graph = null;

    private Graph(Vertex origin) {
        graph = new SimpleGraph<>(DefaultEdge.class);
        buildLabyrinthGraph(origin);
    }

    private Graph buildLabyrinthGraph(Vertex v){
        CardinalsPoints c = new CardinalsPoints(4);
        Vertex tmp;
        for (int i = 0; i < c.getSize(); i++) {
            switch (c.getArrayInd(i)){
                case NORTH:
                    tmp = new Vertex(v.getX(),v.getY()+1);
                    if (!graph.containsVertex(tmp))
                    //graph.addVertex();
                    break;
                case EAST:
                    break;
                case SOUTH:
                    break;
                case WEST:
                    break;
            }
        }
        return null;
    }

}
