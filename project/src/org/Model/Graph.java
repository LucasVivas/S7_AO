package org.Model;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

/**
 * Created by lulu (project's boss) on 19/11/17.
 * Graph class represents the labyrinth graph.
 */
public class Graph {
    private static Graph graph = null;
    private DirectedAcyclicGraph<int[],DefaultEdge> DAGraph = null;

    private Graph(Coordinates coordinates) {
        super();
        graph = buildLabyrinthGraph(coordinates);
    }

    private Graph buildLabyrinthGraph(Coordinates coordinates){
        return null;
    }
    /**
     * Return the unique instance of Graph.
     * @return null if the instance is not already created else
     * return the instance.
     */
    public static Graph getInstance(Coordinates coordinates){
        if (Graph.graph == null){
            graph = new Graph(coordinates);
            return graph;
        }
        return null;
    }
}
