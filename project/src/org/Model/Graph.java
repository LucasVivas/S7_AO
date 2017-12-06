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
    	int x = vertex.getX();
    	int y = vertex.getY();
    	switch(dir) {
    		case NORTH:
    			if(doesExist(vertex, Directions.NORTH)) {
    				res = getVertex(x,y-1);
    				return res;
    			}
    		case SOUTH:
    			if(doesExist(vertex, Directions.SOUTH)) {
    				res = getVertex(x,y+1);
    				return res;
    			}
    		case EAST:
    			if(doesExist(vertex, Directions.EAST)) {
    				res = getVertex(x+1,y);
    				return res;
    			}
    		case WEST:
    			if(doesExist(vertex, Directions.WEST)) {
    				res = getVertex(x-1,y);
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

    public void launchManhattan(Vertex source,Vertex target){
        for(Vertex vertex : mInstance.vertexSet())
            vertex.setNbr(0);
        calculateManhattanDistance(source, target);
    }

    public Vertex furthestVertex(Vertex source) throws VertexNotInGraphException {
        //if (!this.containsVertex(source)){
        //    throw new VertexNotInGraphException(source);
        //}
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
