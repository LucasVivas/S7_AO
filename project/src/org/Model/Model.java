package org.Model;

import java.util.Iterator;

/**
 * Created by lulu on 19/11/17.
 */
public class Model {
    private static Model model = null;
    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;
    private static final int NB_HOLES = 16;

    private Graph graph;
    private Character player;
    private Character villain;
		
	

    private Model() {
        super();
        graph = Graph.getInstance();
        graph.buildRandomPath();

        player = new Character(0, 0);
        villain = new Character(0, 0, Character.Type.BAD);

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

	public Character getPlayer() {
		return player;
	}

	public static int getNB_HOLES() {
		return NB_HOLES;
	}

	public boolean checkMove(Directions direction) {
		Iterator<Edge> graphEdgesIterator = graph.edgeSet().iterator();
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
                    System.out.println(E.toString());
		            if(((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                            && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source-1))
                            || ((E.getSource().getX() == x_source && E.getSource().getY() == y_source-1)
                            && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {
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
                    if(((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                            && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source+1))
                            || ((E.getSource().getX() == x_source && E.getSource().getY() == y_source+1)
                            && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {
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
		            if(((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                            && (E.getTarget().getX() == x_source+1 && E.getTarget().getY() == y_source))
                            || ((E.getSource().getX() == x_source+1 && E.getSource().getY() == y_source)
                            && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {
		            	player.setX(player.getX()+1);
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
                    if(((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                            && (E.getTarget().getX() == x_source-1 && E.getTarget().getY() == y_source))
                            || ((E.getSource().getX() == x_source-1 && E.getSource().getY() == y_source)
                            && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {
		            	player.setX(player.getX()-1);
		            	return true;
		            }		            	   
		        }
				return false;
			}
		}
		return true;
	}

	public void createHoles(){
		for (int i = 0; i < getNB_HOLES(); i++) {
			int x = graph.getRandomInt(getWIDTH());
			int y = graph.getRandomInt(getHEIGHT());
			Vertex v = graph.getVertex(x, y);
			int direction = graph.getRandomInt(4);
			switch (direction){
				case 0:
					if (graph.doesExist(v, Directions.NORTH)){
						//TODO
					}
			}
		}

	}
}
