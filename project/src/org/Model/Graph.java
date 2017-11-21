package org.Model;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

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

    private Graph buildLabyrinthGraph(Vertex origin){
        return null;
    }

}
