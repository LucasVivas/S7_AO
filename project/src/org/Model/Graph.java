package org.Model;

import org.jgrapht.graph.SimpleGraph;
import java.util.*;

/**
 *
 */
public class Graph extends SimpleGraph<Vertex,Edge>{

    private static Graph mInstance;
    private Random random = new Random();

    private Graph(Class<? extends Edge> edgeClass) {
        super(edgeClass);
    }

    public static Graph getInstance() {
        if (mInstance == null) {
            mInstance = new Graph(Edge.class);
        }
        return mInstance;
    }

    public void buildRandomPath(){
        Vertex v = new Vertex(0,0);
        addVertex(v);
        v.setNbr(0);
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
            if(vertex.inBorders(dir) && !doesExist(vertex,dir)){
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
    
    public Vertex vertexByDir(Vertex vertex, Directions dir) {
    	Vertex res;
    	switch(dir) {
    		case NORTH:
    			if(doesExist(vertex, Directions.NORTH)) {
    				res = getVertex(vertex.getX(),vertex.getY()-1);
    				return res;
    			}
    		case SOUTH:
    			if(doesExist(vertex, Directions.NORTH)) {
    				res = getVertex(vertex.getX(),vertex.getY()+1);
    				return res;
    			}
    		case EAST:
    			if(doesExist(vertex, Directions.NORTH)) {
    				res = getVertex(vertex.getX()+1,vertex.getY());
    				return res;
    			}
    		case WEST:
    			if(doesExist(vertex, Directions.NORTH)) {
    				res = getVertex(vertex.getX()-1,vertex.getY());
    				return res;
    			}
    		
    	}
		return null;
    }

    private void calculateManhattanDistance(Vertex source, Vertex target){
        Queue<Vertex> fifo = new ArrayDeque<>();
        target.setNbr(1);
        fifo.add(target);
        while(!fifo.isEmpty()){
            Vertex actual = fifo.remove();
            for(Directions dir : Directions.values()){
                if(this.doesExist(actual,dir)){
                    Vertex next = mInstance.vertexByDir(actual,dir);
                    if(next.getNbr() == 0){
                        next.setNbr(actual.getNbr()+1);
                        if(next != source)
                            fifo.add(next);
                    }
                }
            }
        }
    }

    public void launchManhattan(Vertex source,Vertex target){
        for(Vertex vertex : mInstance.vertexSet())
            vertex.setNbr(0);
        calculateManhattanDistance(source, target);
    }

    public Vertex furthestVertex(Vertex source) throws VertexNotInGraphException {
        if (!this.containsVertex(source)){
            throw new VertexNotInGraphException(source);
        }
        Vertex target;
        int max = 0;
        Vertex maxVertex = null;
        for (int x = 0; x < Model.getWIDTH(); x++) {
            for (int y = 0; y < Model.getHEIGHT(); y++) {
                target = new Vertex(x,y);
                launchManhattan(source,target);
                if(max < source.getNbr()){
                    maxVertex = target;
                }
            }
        }
        return maxVertex;
    }

    public boolean doesExist(Vertex vertex, Directions dir){
        Vertex target;
        Vertex v_tmp;
        switch(dir){
            case NORTH:
                target = new Vertex(vertex.getX(),vertex.getY()-1);
                break;
            case SOUTH:
                target = new Vertex(vertex.getX(),vertex.getY()+1);
                break;
            case EAST:
                target = new Vertex(vertex.getX()+1,vertex.getY());
                break;
            case WEST:
                target = new Vertex(vertex.getX()-1,vertex.getY());
                break;
            default:
                target = new Vertex(vertex.getX(), vertex.getY());
                break;
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
    
	public String toString(){
		Iterator<Vertex> graphSIterator = this.vertexSet().iterator();
		Iterator<Vertex> graphTIterator = this.vertexSet().iterator();
        Vertex S,T;		
		while (graphSIterator.hasNext()) {
            S = graphSIterator.next();
            while (graphTIterator.hasNext()) {
                T = graphTIterator.next();
    	        if(this.getEdge(S, T) != null) 
    	        	System.out.println(S.toString()+"-->"+T.toString());
            }    	   
        }
		return null;
	}

	public Vertex getVertex (int x, int y){
	    Set<Vertex> vertexSet = vertexSet();
	    Iterator<Vertex> iterator = vertexSet.iterator();
	    while (iterator.hasNext()){
	        Vertex v = iterator.next();
	        if (v.getX() == x && v.getY() == y){
	            return v;
            }
        }
        return null;
    }

    public int getRandomInt (int max){
	    return random.nextInt(max);
    }
}
