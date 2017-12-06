package org.Model;

import java.util.ArrayList;
import java.util.Iterator;

import static org.Model.MConsts.*;

/**
 * Created by lulu on 19/11/17.
 */
public class Model implements Observer {
    private static Model model = null;

    private Graph graph;
    private Player player;
    private Door door;
    private ArrayList<BadGuy> badGuys;

    public ArrayList<BadGuy> InitializeList(){
        badGuys = new ArrayList<>(4);;
        for(int i = 0; i < 4; i++)
            badGuys.add(new BadGuy());
        return badGuys;
    }
	

    private Model() {
		super();
		graph = Graph.getInstance();
		graph.buildRandomPath();
		player = Player.getInstance();
		door = Door.getInstance();
        //System.out.println("door : "+door.getX()+",,,"+door.getY());
        badGuys = new ArrayList<>(NB_BADGUYS);
        InitializeList();
        for(int i = 0; i < NB_BADGUYS; i++)
            player.register(new BadGuy());
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
	
	public BadGuy getBadGuy(int index) {
		return badGuys.get(index);
	}

    public ArrayList<BadGuy> getBadGuys() {
        return badGuys;
    }

    public void notifyObservers() throws PlayerReachedException, FinishedLevelException {
        for(BadGuy badGuy : badGuys){
            badGuy.update(player.getX(), player.getY());
        }
    }

    public Door getDoor(){return door;}

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

    @Override
    public void update(int newX, int newY) {

    }
}
