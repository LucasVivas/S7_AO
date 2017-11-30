package org.Model;

import java.util.Iterator;

import static org.Model.MConsts.*;

/**
 * Created by lulu on 19/11/17.
 */
public class Model {
    private static Model model = null;

    private Graph graph;
    private Player player;
    private BadGuy badGuys[];
		
	

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
