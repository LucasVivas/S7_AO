package org.Model;

import java.util.Iterator;
import java.util.Random;

public class Character extends Vertex{
    public Character(){
        super();
    }

    public Character(int x, int y) {
        super(x, y);
    }

    public void move(Graph graph) throws PlayerReachedException, FinishedLevelException {
    		Vertex vertex = this.getVertex(Model.getInstance().getGraph()) ;
    		for (Directions dir : Directions.values()) {
	    		Vertex next = Graph.getInstance().vertexByDir(vertex, dir) ;
	    		if (Graph.getInstance().containsEdge(vertex, next)
	    			&& (next.getNbr()== vertex.getNbr()-1) ){
	    			this.move(dir);
	    		}
    		}	
    }
    public Vertex getVertex(Graph graph) {
    	return graph.getVertex(this.getX(), this.getY());
    }
    
    public boolean move(Directions direction) throws PlayerReachedException, FinishedLevelException {
        Iterator<Edge> graphEdgesIterator = Graph.getInstance().edgeSet().iterator();
        Edge E;
        int x_source = getX();
        int y_source = getY();
        int xTmp = 0;
        int yTmp = 0;

        if (!new Vertex(x_source, y_source).inBorders(direction))
            return false;

        switch (direction) {
            case NORTH:
                yTmp = -1;
                break;
            case SOUTH:
                yTmp = 1;
                break;
            case EAST:
                xTmp = 1;
                break;
            case WEST:
                xTmp = -1;
                break;
        }



        while (graphEdgesIterator.hasNext()) {
            E = graphEdgesIterator.next();
            if (((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                    && (E.getTarget().getX() == x_source + xTmp && E.getTarget().getY() == y_source + yTmp))
                    || ((E.getSource().getX() == x_source + xTmp && E.getSource().getY() == y_source + yTmp)
                    && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {

                if(getX()+xTmp == Player.getPlayer().getX() && getY()+yTmp == Player.getPlayer().getY())
                    throw new PlayerReachedException();

//                if(getX()+xTmp == Door.getDoor().getX() && getY()+yTmp == Door.getDoor().getY())
//                    throw new FinishedLevelException();

                setX(getX() + xTmp);
                setY(getY() + yTmp);
                return true;
            }
        }
        return false;
    }
}

