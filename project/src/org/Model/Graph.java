package org.Model;

import org.jgrapht.graph.SimpleGraph;

import java.util.Random;
import java.util.Vector;

/**
*Created by lulu (project's boss) on 19/11/17.
 * Graphgh class represents the labyrinth graph.
 */
public class Graph extends SimpleGraph<Vertex,Edge>{
    private int nbVertices;
    private int nbEdges;
    private Vertex[][] VerticiesMatrix;
    private static Graph graph = null;
    private Random random = new Random();

    private Graph(Class<? extends Edge> edgeClass) {
        super(edgeClass);
        this.nbVertices = 0;
        this.VerticiesMatrix = new Vertex[Vertex.BOTTOM_BORDER][Vertex.RIGHT_BORDER];
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

        graph.addVertex(vertex);
        //pour chacune de ces directions,on avance en profondeur d’abord
        for(int i=0;i<4;++i){
            Directions dir=directions[i];
            if(vertex.inBorders(dir) && graph.doesntExist(vertex,dir)){
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
                graph.addVertex(next);
                nbVertices++;
                graph.addEdge(vertex,next);
                nbEdges++;
                buildRandomPath(next);
            }
        }
    }

    public boolean addVertex(Vertex v){
        if(graph.VerticiesMatrix[v.getX()][v.getY()] == null){
            graph.VerticiesMatrix[v.getX()][v.getY()] = v;
            return true;
        }
        return false;
    }

    public boolean containsVertex(Vertex v){
        return graph.VerticiesMatrix[v.getX()][v.getY()] != null;
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
