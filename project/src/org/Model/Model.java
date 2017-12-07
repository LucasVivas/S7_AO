package org.Model;

import java.util.ArrayList;

import static org.Model.MConsts.*;

/**
 * Created by lulu on 19/11/17.
 */
public class Model {
    private static Model model = null;

    private Graph graph;
    private Player player;
    private Door door;
    private ArrayList<BadGuy> badGuys;



    private Model() {
		super();
		graph = Graph.getInstance();
		graph.buildRandomPath();
		createHoles();
		player = Player.getInstance();
		door = Door.getInstance();
        badGuys = new ArrayList<>(NB_BADGUYS);
        for(int i = 0; i < NB_BADGUYS; i++)
        	badGuys.add(new BadGuy());
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

	public void notifyObservers() throws PlayerReachedException, FinishedLevelException{
        for(BadGuy badGuy : badGuys) {
            badGuy.update(player.getX(), player.getY());
        }
    }

    public ArrayList<BadGuy> getBadGuys() {
        return badGuys;
    }

	public Door getDoor(){return door;}

	public static int getNB_HOLES() {
		return NB_HOLES;
	}

	public static int getNB_BADGUYS(){
	    return NB_BADGUYS;
    }

    public void createHoles(){
        for (int i = 0; i < MConsts.NB_HOLES; i++) {
            int x = graph.getRandomInt(getWIDTH());
            int y = graph.getRandomInt(getHEIGHT());
            Vertex source = graph.getVertex(x, y);
            Vertex target;
            int direction = graph.getRandomInt(4);
            switch (direction){
                case 0:
                    if (graph.doesExist(source, Directions.NORTH)){
                        target = graph.vertexByDir(source, Directions.NORTH);
                        if (!graph.containsEdge(source, target)){
                            Edge e = new Edge(source, target, Edge.Type.CORRIDOR);
                            graph.addEdge(source, target, e);
                        } else {
                            i++;
                        }
                    } else {
                        i++;
                    }
                    break;
                case 1:
                    if (graph.doesExist(source, Directions.SOUTH)){
                        target = graph.vertexByDir(source, Directions.SOUTH);
                        if (!graph.containsEdge(source, target)){
                            Edge e = new Edge(source, target, Edge.Type.CORRIDOR);
                            graph.addEdge(source, target, e);
                        } else {
                            i++;
                        }
                    } else {
                        i++;
                    }
                    break;
                case 2:
                    if (graph.doesExist(source, Directions.EAST)){
                        target = graph.vertexByDir(source, Directions.EAST);
                        if (!graph.containsEdge(source, target)){
                            Edge e = new Edge(source, target, Edge.Type.CORRIDOR);
                            graph.addEdge(source, target, e);
                        } else {
                            i++;
                        }
                    } else {
                        i++;
                    }
                    break;
                case 3:
                    if (graph.doesExist(source, Directions.WEST)){
                        target = graph.vertexByDir(source, Directions.WEST);
                        if (!graph.containsEdge(source, target)){
                            Edge e = new Edge(source, target, Edge.Type.CORRIDOR);
                            graph.addEdge(source, target, e);
                        } else {
                            i++;
                        }
                    } else {
                        i++;
                    }
                    break;
            }
        }

    }

}
