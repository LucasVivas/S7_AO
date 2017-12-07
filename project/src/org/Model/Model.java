package org.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


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
    private ArrayList<Candy> candyList;

    private Model() {
		super();
		graph = Graph.getInstance();
		graph.buildRandomPath();
		createHoles();
		player = Player.getInstance();
		door = Door.getInstance();
		badGuys = createBadGuysList();
        candyList = randomCandyList();
	}

    public static Model getInstance(){
        if (Model.model == null){
            model = new Model();
        }
        return model;
    }

    public ArrayList<BadGuy> createBadGuysList(){
        badGuys = new ArrayList<>(NB_BADGUYS);
        for(int i = 0; i < NB_BADGUYS; i++)
            badGuys.add(new BadGuy());
        return badGuys;
    }

    public ArrayList<Candy> randomCandyList(){
        int nbCandies = 5;
        ArrayList<Candy> candyList = new ArrayList<>(nbCandies);
        CandyFactory candyFactory = new CandyFactory();
        for(int i=0 ; i<nbCandies ; i++){
            int typeOfCandy = new Random().nextInt(4);
            Candy candy = candyFactory.makeCandy(typeOfCandy);
            candyList.add(candy);
        }
        return candyList;
    }

    public ArrayList<Candy> getCandyList() {
        return candyList;
    }

    public Candy getCandy(int index){
        return candyList.get(index);
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

	public void notifyObservers() throws PlayerReachedException{
        for(BadGuy badGuy : badGuys) {
            badGuy.update();
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
            Directions dir = getRandomDirection();

            if (graph.doesExist(source, dir)){
                target = graph.vertexByDir(source, dir);
                if (!graph.containsEdge(source, target)){
                    Edge e = new Edge(source, target, Edge.Type.CORRIDOR);
                    graph.addEdge(source, target, e);
                } else {
                    i--;
                }
            } else {
                i--;
            }
        }

    }

    public Directions getRandomDirection(){
        int dir = graph.getRandomInt(4);
        switch(dir){
            case 0:
                return Directions.NORTH;
            case 1:
                return Directions.SOUTH;
            case 2:
                return Directions.WEST;
            case 3:
                return Directions.EAST;
        }
        return null;
    }

}