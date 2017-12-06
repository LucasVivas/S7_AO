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
        int x_tmp = 0;
        int y_tmp = 0;

        if (new Vertex(x_source, y_source).inBorders(direction))
            return false;

        switch (direction) {
            case NORTH:
                y_tmp = -1;
                break;
            case SOUTH:
                y_tmp = 1;
                break;
            case EAST:
                x_tmp = 1;
                break;
            case WEST:
                x_tmp = -1;
                break;
        }

        while (graphEdgesIterator.hasNext()) {
            E = graphEdgesIterator.next();
            if (((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                    && (E.getTarget().getX() == x_source + x_tmp && E.getTarget().getY() == y_source + y_tmp))
                    || ((E.getSource().getX() == x_source + x_tmp && E.getSource().getY() == y_source + y_tmp)
                    && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {

                if(getX()+x_tmp == Player.getPlayer().getX() && getY()+y_tmp == Player.getPlayer().getY())
                    throw new PlayerReachedException();

                if(getX()+x_tmp == Door.getDoor().getX() && getY()+y_tmp == Door.getDoor().getY())
                    throw new FinishedLevelException();

                setX(getX() + x_tmp);
                setY(getY() + y_tmp);
                return true;
            }
        }
        return false;
    }
}

