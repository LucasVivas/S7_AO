package org.Model;

import org.jgrapht.graph.SimpleGraph;

import java.util.*;

/**
*Created by lulu (project's boss) on 19/11/17.
 * Graph class represents the labyrinth graph.
 */
public class Graph extends SimpleGraph<Vertex,Edge>{
    private Vertex[][] VerticesMatrix;
    private Set<Edge> graphEdges;
    private static Graph graph = null;
    private Random random = new Random();

    private Graph(Class<? extends Edge> edgeClass) {
        super(edgeClass);
        this.VerticesMatrix = new Vertex[Model.getHEIGHT()][Model.getWIDTH()];
        this.graphEdges = new HashSet<>();
    }

    public static Graph getInstance(){
        if (Graph.graph == null){
            graph = new Graph(Edge.class);
            return graph;
        }
        return null;
    }

    public void buildRandomPath(Vertex vertex){
        // une liste aleatoire des 4 directions
        Vector<Directions> v = new Vector<>();
        for(int i=0;i<4;++i) {
            v.add(Directions.values()[i]);
        }
        Directions directions[]=new Directions[4];
        for(int i=0;i<directions.length;++i){
            int index=random.nextInt(v.size());
            directions[i]=v.get(index);
            v.remove(index);
        }

        //pour chacune de ces directions,on avance en profondeur d’abord
        for(int i=0;i<4;++i){
            Directions dir=directions[i];
            if(vertex.inBorders(dir) && doesntExist(vertex,dir)){
                int x=vertex.getX();
                int y=vertex.getY();
                int xt=0,yt=0;
                switch(dir){
                    case NORTH:
                        xt=x;
                        yt=y-1;
                        break;
                    case SOUTH:
                        xt=x;
                        yt=y+1;
                        break;
                    case EAST:
                        xt=x+1;
                        yt=y;
                        break;
                    case WEST:
                        xt=x-1;
                        yt=y;
                        break;
                }
                Vertex next = new Vertex(xt,yt,vertex.getNbr()+1);
                addVertex(next);
                setVerticesMatrix(next);
                addEdge(vertex,next);
                setNewEdge(vertex,next);
                buildRandomPath(next);
            }
        }
    }

    public void setNewEdge(Vertex source, Vertex target){
        graphEdges.add(new Edge(source,target));
    }

    public Vertex getVerticesMatrix(int y, int x){
        return VerticesMatrix[y][x];
    }

    public void setVerticesMatrix(Vertex v) {
        VerticesMatrix[v.getY()][v.getX()] = v;
    }

    public boolean containsVertex(Vertex v){
        return VerticesMatrix[v.getY()][v.getX()] != null;
    }


    public Set<Edge> getAllEdges(){
        Set<Edge> allEdges = new HashSet<>();
        for (int y = 0; y < Model.getHEIGHT(); y++){
            for (int x = 0; x < Model.getWIDTH(); x++){
                if (VerticesMatrix[y][x].inBorders(Directions.NORTH)){
                    if(!allEdges.contains((graph.getEdge(graph.VerticesMatrix[y][x],graph.VerticesMatrix[y-1][x])))){
                        allEdges.add(new Edge(graph.VerticesMatrix[y][x],graph.VerticesMatrix[y-1][x]));
                    }
                }
                if (VerticesMatrix[y][x].inBorders(Directions.SOUTH)){
                    if(!allEdges.contains((graph.getEdge(graph.VerticesMatrix[y][x],graph.VerticesMatrix[y+1][x])))){
                        allEdges.add(new Edge(graph.VerticesMatrix[y][x],graph.VerticesMatrix[y+1][x]));
                    }
                }

                if (VerticesMatrix[y][x].inBorders(Directions.EAST)){
                    if(!allEdges.contains((graph.getEdge(graph.VerticesMatrix[y][x],graph.VerticesMatrix[y][x+1])))){
                        allEdges.add(new Edge(graph.VerticesMatrix[y][x],graph.VerticesMatrix[y][x+1]));
                    }
                }

                if (VerticesMatrix[y][x].inBorders(Directions.WEST)){
                    if(!allEdges.contains((graph.getEdge(graph.VerticesMatrix[y][x],graph.VerticesMatrix[y][x-1])))){
                        allEdges.add(new Edge(graph.VerticesMatrix[y][x],graph.VerticesMatrix[y][x-1]));
                    }
                }
            }
        }
        return allEdges;
    }

    public Set<Edge> notContainedEdges() {
        Set<Edge> wallSet = getAllEdges();
        wallSet.removeAll(graphEdges);
/*        while (edgeIterator.hasNext()){
            Edge E = edgeIterator.next();
            if (!graphEdges.contains(E)){
                wallTab.add(E);
            }
        }*/
        return wallSet;
    }

    public boolean doesntExist(Vertex vertex, Directions dir){
        Vertex tmp;
        switch(dir){
            case NORTH:
                tmp = new Vertex(vertex.getX(),vertex.getY()-1,vertex.getNbr());
                if (graph.containsVertex(tmp)){
                    return false;
                }
                break;
            case SOUTH:
                tmp = new Vertex(vertex.getX(),vertex.getY()+1,vertex.getNbr());
                if (graph.containsVertex(tmp)){
                    return false;
                }
                break;
            case EAST:
                tmp = new Vertex(vertex.getX()+1,vertex.getY(),vertex.getNbr());
                if (graph.containsVertex(tmp)){
                    return false;
                }
                break;
            case WEST:
                tmp = new Vertex(vertex.getX()-1,vertex.getY(),vertex.getNbr());
                if (graph.containsVertex(tmp)){
                    return false;
                }
                break;
        }
        return true;
    }

}
