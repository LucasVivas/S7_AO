package org.Model;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

/**
 * Created by lulu (project's boss) on 19/11/17.
 * Graph class represents the labyrinth graph.
 */
public class Graph {
    private static Graph graph = null;
    private DirectedAcyclicGraph<String,DefaultEdge> DAGraph = null;

    private Graph() {
        super();

    }

    private void buildLabyrinthGraph(String v){

    }
    /**
     * Return the unique instance of Graph.
     * @return null if the instance is not already created else
     * return the instance.
     */
    public static Graph getInstance(){
        if (Graph.graph == null){
            graph = new Graph();
            return graph;
        }
        return null;
    }
}
