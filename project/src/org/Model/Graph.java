package org.Model;

import org.jgrapht.graph.SimpleGraph;
import java.util.*;

/**
 *  @author Lucas Vivas, Gauthier Lamarque, Alexandre Casanova-Franger, Zakaria Mallouky
 *  The graph class is used to create the graph for the maze.
 *
 */
public class Graph extends SimpleGraph<Vertex,Edge>{

    private static Graph mInstance;

    private Graph(Class<? extends Edge> edgeClass) {
        super(edgeClass);
    }

    /**
     * Singleton
     * @return Instance of graph and create an instance if it do not exist.
     */
    public static Graph getInstance() {
        if (mInstance == null) {
            mInstance = new Graph(Edge.class);
        }
        return mInstance;
    }

    /**
     * This is the starting function to generate a random graph from the (0,0) vertex.
     * Launches the recursive function.
     */
    public void buildRandomPath(){
        Vertex v = new Vertex(0,0);
        addVertex(v);
        v.setNbr(0);
        buildRandomPathRec(v);
    }

    /**
     * This recursive function generates a graph where edges are randomly set.
     * @param vertex vertex used to the generation.
     */
    private void buildRandomPathRec(Vertex vertex){
        // une liste aleatoire des 4 directions
        Vector<Directions> v = new Vector<>();
        for(int i=0;i<4;++i) {
            v.add(Directions.values()[i]);
        }
        Directions directions[]=new Directions[4];
        for(int i=0; i<directions.length; ++i){
            int index = getRandomInt(v.size());
            directions[i]=v.get(index);
            v.remove(index);
        }

        //pour chacune de ces directions,on avance en profondeur d’abord
        for(int i=0;i<4;++i){
            Directions dir=directions[i];
            if(!vertex.inBorders(dir) && !doesExist(vertex,dir)){
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
                Vertex next = new Vertex(xt,yt);
                next.setNbr(vertex.getNbr()+1);
                addVertex(next);
                Edge e = new Edge(vertex, next, Edge.Type.CORRIDOR);
                addEdge(vertex, next, e);
                buildRandomPathRec(next);
            }
        }
    }

    /**
     * @param vertex The vertex
     * @param dir The direction to check
     * @return The vertex which is at the direction <i>dir</i> from the vertex <i>vertex</i>
     * return null if the vertex doesn't exist.
     */
    public Vertex vertexByDir(Vertex vertex, Directions dir) {
    	Vertex res;
    	int x = vertex.getX();
    	int y = vertex.getY();
    	switch(dir) {
    		case NORTH:
    			if(doesExist(vertex, dir)) {
                    res = getVertex(x,(y-1));
                    return res;
    			}
    		case SOUTH:
    			if(doesExist(vertex, dir)) {
    				res = getVertex(x,(y+1));
    				return res;
    			}
    		case EAST:
    			if(doesExist(vertex, dir)) {
    				res = getVertex((x+1),y);
    				return res;
    			}
    		case WEST:
    			if(doesExist(vertex, dir)) {
    				res = getVertex((x-1),y);
    				return res;
    			}
    	}
		return null;
    }

    /**
     * This function calculate the distance between the arguments using Manhattan algorithm.
     * The Vertex class member nbr represents the distance from the source.
     * @param source The vertex which is seeking for the path to the target.
     * @param target The vertex which nbr is 0.
     */
    private void calculateManhattanDistance(Vertex source, Vertex target){
        Queue<Vertex> fifo = new ArrayDeque<>();
        target.setNbr(1);
        fifo.add(target);
        while(!fifo.isEmpty()){
            Vertex actual = fifo.remove();
            for(Directions dir : Directions.values()){
                if(doesExist(actual,dir) && containsEdge(actual,vertexByDir(actual,dir))){
                    Vertex next = mInstance.vertexByDir(actual,dir);
                    if(next.getNbr() == 0){
                        next.setNbr(actual.getNbr()+1);
                        if(next != source)
                            fifo.add(next);
                        else
                            source.setNbr(actual.getNbr()+1);
                    }
                }
            }
        }
    }

    /**
     * This function is initializing all nbr's to 0, then launches
     * calculateManhattanDistance(Vertex source, Vertex target).
     * @param source The vertex which is seeking for the path to the target.
     * @param target The vertex which nbr is 0.
     */
    public void launchManhattan(Vertex source,Vertex target){
        for(Vertex vertex : mInstance.vertexSet())
            vertex.setNbr(0);
        calculateManhattanDistance(source, target);
    }

    /**
     * This function uses the Manhattan function to look for the furthest vertex
     * from source.
     * @param source The vertex which is used as a source.
     * @return This function returns the furthest vertex from the source.
     */
    public Vertex furthestVertex(Vertex source) {
        int max = 0;
        Vertex tmp;
        Vertex maxVertex = null;
        launchManhattan(source,source);
        for (int x = 0; x < Model.getWIDTH(); x++) {
            for (int y = 0; y < Model.getHEIGHT(); y++) {
                tmp = getVertex(x,y);
                if(max < tmp.getNbr()){
                    maxVertex = tmp;
                    max = tmp.getNbr();
                }
            }
        }
        return maxVertex;
    }

    /**
     * Given a vertex and a direction, this function tells if a vertex exists or not.
     * @param vertex The source vertex.
     * @param dir The wanted direction
     * @return True if there is a vertex, false otherwise.
     */
    public boolean doesExist(Vertex vertex, Directions dir){
        Vertex target;
        Vertex v_tmp;
        int x = vertex.getX();
        int y = vertex.getY();
        switch(dir){
            case NORTH:
                if (y == 0){
                    return false;
                } else {
                    target = new Vertex(x, y - 1);
                }
                break;
            case SOUTH:
                if (y == MConsts.HEIGHT){
                    return false;
                } else {
                    target = new Vertex(x, y + 1);
                }
                break;
            case EAST:
                if (x == MConsts.WIDTH){
                    return false;
                } else {
                    target = new Vertex(x + 1, y);
                }
                break;
            case WEST:
                if (x == 0){
                    return false;
                } else {
                    target = new Vertex(x - 1, y);
                }
                break;
            default:
                target = null;
                return false;
        }
        Iterator<Vertex> vertexSetIterator = vertexSet().iterator();
        while (vertexSetIterator.hasNext()) {
            v_tmp = vertexSetIterator.next();
            if (v_tmp.compareTo(target) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a vertex with the position (x,y)
     * @param x x position
     * @param y y position
     * @return If the vertex exists, returns it, null otherwise.
     */
	public Vertex getVertex (int x, int y){
	    Set<Vertex> vertexSet = vertexSet();
        for (Vertex v : vertexSet) {
            if (v.getX() == x && v.getY() == y) {
                return v;
            }
        }
        return null;
    }

    /**
     * This function returns an random integer between 0 and max.
     * @param max The upper limit for the random generation.
     * @return A randomly generated integer.
     */
    public int getRandomInt (int max){
	    return new Random().nextInt(max);
    }

    /**
     * This function checks if the graph contains the edge between the source and the target.
     * @param source The source vertex
     * @param target The target vertex
     * @return True if the edge exists, false otherwise.
     */
    @Override
    public boolean containsEdge(Vertex source, Vertex target) {
        Iterator<Edge> graphEdgesIterator = Graph.getInstance().edgeSet().iterator();
        Edge E;
        while (graphEdgesIterator.hasNext()) {
            E = graphEdgesIterator.next();
            if (((E.getSource().getX() == source.getX() && E.getSource().getY() == source.getY())
                    && (E.getTarget().getX() == target.getX() && E.getTarget().getY() == target.getY()))
                    || ((E.getSource().getX() == target.getX() && E.getSource().getY() == target.getY())
                    && (E.getTarget().getX() == source.getX() && E.getTarget().getY() == source.getY()))) {
                return true;
            }
        }
        return false;
    }
}
