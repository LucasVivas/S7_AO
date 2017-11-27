package org.Model;

import org.jgrapht.graph.SimpleGraph;

import java.util.*;

/**
*Created by lulu (project's boss) on 19/11/17.
 * Graph class represents the labyrinth graph.
 */
public class Graph extends SimpleGraph<Vertex,Edge>{
    private Vertex[][] VerticesMatrix;
    private HashSet<Edge> graphEdges = new HashSet<>();
    private static Graph graph = null;
    private Random random = new Random();

    private Graph(Class<? extends Edge> edgeClass) {
        super(edgeClass);
        this.VerticesMatrix = new Vertex[Model.getHEIGHT()][Model.getWIDTH()];
    }

    public static Graph getInstance(){
        if (Graph.graph == null){
            graph = new Graph(Edge.class);
            return graph;
        }
        return null;
    }

    public HashSet<Edge> getGraphEdges() {
        return graphEdges;
    }

    public void buildRandomPath(){
        Vertex v = new Vertex(0,0,0);
        graph.addVertex(v);
        graph.setVerticesMatrix(v);
        buildRandomPathRec(v);
    }

    private void buildRandomPathRec(Vertex vertex){
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
                setNewEdge(vertex,next,1);
                buildRandomPathRec(next);
            }
        }
    }

    public void setNewEdge(Vertex source, Vertex target, int weight){
        graphEdges.add(new Edge(source,target,1));
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

    public HashSet<Edge> getAllEdges(){
       HashSet<Edge> allEdges = new HashSet<>();
        for (int y = 0; y < Model.getHEIGHT(); y++){
            for (int x = 0; x < Model.getWIDTH(); x++){
                if (VerticesMatrix[y][x].inBorders(Directions.NORTH)){
                    if(!allEdges.contains((getEdge(VerticesMatrix[y][x],VerticesMatrix[y-1][x])))){
                        allEdges.add(new Edge(VerticesMatrix[y][x],VerticesMatrix[y-1][x], 1));
                    }
                }
                if (VerticesMatrix[y][x].inBorders(Directions.SOUTH)){
                    if(!allEdges.contains((getEdge(VerticesMatrix[y][x],VerticesMatrix[y+1][x])))){
                        allEdges.add(new Edge(VerticesMatrix[y][x],VerticesMatrix[y+1][x],1));
                    }
                }

                if (VerticesMatrix[y][x].inBorders(Directions.EAST)){
                    if(!allEdges.contains((getEdge(VerticesMatrix[y][x],VerticesMatrix[y][x+1])))){
                        allEdges.add(new Edge(VerticesMatrix[y][x],VerticesMatrix[y][x+1],1));
                    }
                }

                if (VerticesMatrix[y][x].inBorders(Directions.WEST)){
                    if(!allEdges.contains((getEdge(VerticesMatrix[y][x],VerticesMatrix[y][x-1])))){
                        allEdges.add(new Edge(VerticesMatrix[y][x],VerticesMatrix[y][x-1],1));
                    }
                }
            }
        }
        return allEdges;
    }

/*    public Set<Edge> notContainedEdges() {
        HashSet<Edge> wallSet = getAllEdges();
        Iterator<Edge> edgeIterator = wallSet.iterator();
        while (edgeIterator.hasNext()){
            Edge E = edgeIterator.next();
            if(!graphEdges.contains(E)){
                wallSet.remove(E);
            }
        }
//        if (wallSet.removeAll(graphEdges)){
//        System.out.println("wallSet");
        return wallSet;
    }*/

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
