package org.Model;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.Model.CardinalsPoints;

/**
 * Created by lulu (project's boss) on 19/11/17.
 * Graph class represents the labyrinth graph.
 */
public class Graph {
    private SimpleGraph<Vertex,DefaultEdge> graph = null;

    private Graph(Vertex origin) {
        graph = new SimpleGraph<>(DefaultEdge.class);
        buildLabyrinthGraph(origin);
    }

    private Graph buildLabyrinthGraph(Vertex v){
        CardinalsPoints c = new CardinalsPoints(4);
        for (int i = 0; i < c.getSize(); i++) {
            switch (c.getArrayInd(i)){
                case NORTH:
                    //if ()
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
