package org.Model;

import java.util.Iterator;

import org.View.View;

/**
 * Created by lulu on 19/11/17.
 */
public class Model {
    private static Model model = null;
    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;

    private Graph graph = Graph.getInstance();
    private Personnage player;
    private Personnage villain;
		
	

    private Model() {
        super();
        graph.buildRandomPath();
        player = new Personnage(0, 0);
        villain = new Personnage(0, 0, Personnage.Type.Bad);
    }

    public static Model getInstance(){
        if (Model.model == null){
            model = new Model();
        }
        return model;
    }

    public Graph getGraph() {
        return graph;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

	public Personnage getPlayer() {
		return player;
	}
	public boolean checkMove(Directions direction) {
		Iterator<Edge> graphEdgesIterator = graph.getAllEdges().iterator();
        Edge E;
		int x_source = player.getX();
		int y_source = player.getY();
		switch (direction) {
		case NORTH :
			if (y_source == 0) {
				return false;
			} else {
				while (graphEdgesIterator.hasNext()) {
		            E = graphEdgesIterator.next();
		            if((E.getSource().getX() == x_source && E.getSource().getY() == y_source) && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source-1)) {
		            	player.setY(player.getY()-1);
		            	return true;
		            }		            	   
		        }
				return false;
			}
		case SOUTH:
			if (y_source == HEIGHT) {
				return false;
			} else {				
		        while (graphEdgesIterator.hasNext()) {
		            E = graphEdgesIterator.next();
		            if((E.getSource().getX() == x_source && E.getSource().getY() == y_source) && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source+1)) {
		            	player.setY(player.getY()+1);
		            	return true;
		            }		            	   
		        }
				return false;				
			}
		case EAST:
			if (x_source == WIDTH) {
				return false;
			} else {
				while (graphEdgesIterator.hasNext()) {
		            E = graphEdgesIterator.next();
		            if((E.getSource().getX() == x_source && E.getSource().getY() == y_source) && (E.getTarget().getX() == x_source+1 && E.getTarget().getY() == y_source)) {
		            	player.setY(player.getX()+1);
		            	return true;
		            }		            	   
		        }
				return false;
			}
		case WEST:
			if (x_source == 0) {
				return false;
			} else {
				while (graphEdgesIterator.hasNext()) {
		            E = graphEdgesIterator.next();
		            if((E.getSource().getX() == x_source && E.getSource().getY() == y_source) && (E.getTarget().getX() == x_source-1 && E.getTarget().getY() == y_source)) {
		            	player.setY(player.getX()-1);
		            	return true;
		            }		            	   
		        }
				return false;
			}
		}
		return true;
	}
}
