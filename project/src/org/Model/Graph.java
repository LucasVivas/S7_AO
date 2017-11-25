package org.Model;

import org.jgrapht.graph.SimpleGraph;

import java.util.*;

/**
*Created by lulu (project's boss) on 19/11/17.
 * Graph class represents the labyrinth graph.
 */
public class Graph extends SimpleGraph<Vertex,Edge>{
    private int nbVertices;
    private int nbEdges;
    private Vertex[][] VerticesMatrix;
    private static Graph graph = null;
    private Random random = new Random();

    private Graph(Class<? extends Edge> edgeClass) {
        super(edgeClass);
        this.nbVertices = 0;
        this.VerticesMatrix = new Vertex[Model.getHEIGHT()][Model.getWIDTH()];
    }

    public static Graph getInstance(){
        if (Graph.graph == null){
            graph = new Graph(Edge.class);
            return graph;
        }
        return null;
    }

    public int getNbVertices(){
        return nbVertices;
    }

    public void setNbVertices(int v){
        nbVertices = v;

    public int getNbEdges(){
        return nbEdges;
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
                nbVertices++;
                addEdge(vertex,next);
                nbEdges++;
                buildRandomPath(next);
            }
        }
    }

    public boolean addVertex(Vertex v){
        if(VerticesMatrix[v.getX()][v.getY()] == null){
            VerticesMatrix[v.getX()][v.getY()] = v;
            return true;
        }
        return false;
    }

    public boolean containsVertex(Vertex v){
        return VerticesMatrix[v.getX()][v.getY()] != null;
    }


    public Set<Edge> getAllEdges(){
        Set<Edge> allEdges = new HashSet<>();
        int num = 0;
        for (int y = 0; y < Model.getHEIGHT(); y++){
            for (int x = 0; y < Model.getHEIGHT(); x++){
                if (VerticesMatrix[x][y].inBorders(Directions.NORTH)){
                    if(!allEdges.contains((graph.getEdge(graph.VerticesMatrix[x][y],graph.VerticesMatrix[x][y-1])))){
                        allEdges.add(new Edge(graph.VerticesMatrix[x][y],graph.VerticesMatrix[x][y-1], num));
                        num++;
                    }
                }
                if (VerticesMatrix[x][y].inBorders(Directions.SOUTH)){
                    if(!allEdges.contains((graph.getEdge(graph.VerticesMatrix[x][y],graph.VerticesMatrix[x][y+1])))){
                        allEdges.add(new Edge(graph.VerticesMatrix[x][y],graph.VerticesMatrix[x][y+1], num));
                        num++;
                    }
                }

                if (VerticesMatrix[x][y].inBorders(Directions.EAST)){
                    if(!allEdges.contains((graph.getEdge(graph.VerticesMatrix[x][y],graph.VerticesMatrix[x+1][y])))){
                        allEdges.add(new Edge(graph.VerticesMatrix[x][y],graph.VerticesMatrix[x+1][y], num));
                        num++;
                    }
                }

                if (VerticesMatrix[x][y].inBorders(Directions.WEST)){
                    if(!allEdges.contains((graph.getEdge(graph.VerticesMatrix[x][y],graph.VerticesMatrix[x-1][y])))){
                        allEdges.add(new Edge(graph.VerticesMatrix[x][y],graph.VerticesMatrix[x-1][y], num));
                        num++;
                    }
                }
            }
        }
        return allEdges;
    }

    public Set<Edge> notContainedEdges() {
        Set<Edge> graphEdges = edgeSet();
        Set<Edge> graphAllEdges = getAllEdges();
        Iterator<Edge> edgeIterator = graphAllEdges.iterator();
        Set<Edge> wallTab = new HashSet<>();
        while (edgeIterator.hasNext()){
            Edge E = edgeIterator.next();
            if (!graphEdges.contains(E)){
                wallTab.add(E);
            }
        }
        return wallTab;
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
