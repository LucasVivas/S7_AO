package org.Model;

import java.util.Iterator;

import static org.Model.MConsts.*;

/**
 * Created by lulu on 19/11/17.
 */
public class Model {
    private static Model model = null;

    private Graph graph;
    private Character player;
    private Character badGuys[];
		
	

    private Model() {
		super();
		graph = Graph.getInstance();
		graph.buildRandomPath();

		player = new Player(0, 0);
		badGuys = new BadGuy[NB_BADGUYS];
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
		int xTmp = 0;
		int yTmp = 0;

		if (!new Vertex(x_source,y_source).inBorders(direction))
		    return false;

		switch (direction) {
		case NORTH :
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
            if(((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                    && (E.getTarget().getX() == x_source + xTmp && E.getTarget().getY() == y_source + yTmp))
                    || ((E.getSource().getX() == x_source + xTmp && E.getSource().getY() == y_source + yTmp)
                    && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {
                player.setX(player.getX() + xTmp);
                player.setY(player.getY() + yTmp);
                return true;
            }
        }
        return false;
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
					break;
                case 1:
                    if (graph.doesExist(v, Directions.SOUTH)){
                        //TODO
                    }
                    break;
                case 2:
                    if (graph.doesExist(v, Directions.EAST)){
                        //TODO
                    }
                    break;
                case 3:
                    if (graph.doesExist(v, Directions.WEST)){
                        //TODO
                    }
                    break;
			}
		}

	}
}
