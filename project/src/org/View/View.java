package org.View;

import javafx.stage.Stage;
import org.Controller.Controller;
import org.Model.*;

import java.util.ArrayList;

import static org.View.VConsts.*;

/**
 * Created by lulu on 19/11/17.
 */
public class View {
    private static View view = null;
    private static Controller controller = Controller.getInstance();

    public VPlayer vPlayer;
    public VDoor vDoor;
    public ArrayList<VBadGuy> vBadGuys;
    public ArrayList<VCandy> vCandies;

    private View() {
        super();
        vPlayer = new VPlayer();
        vDoor = new VDoor();
        vBadGuys = InitializeBadGuys();
        vCandies = InitializeCandies();
    }

    public ArrayList<VBadGuy> InitializeBadGuys(){
        vBadGuys = new ArrayList<>(Model.getNB_BADGUYS());;
        for(int i = 0; i < Model.getNB_BADGUYS(); i++)
            vBadGuys.add(new VBadGuy());
        return vBadGuys;
    }

    public ArrayList<VCandy> InitializeCandies(){
        vCandies = new ArrayList<>();
        ArrayList<Candy> candyList = Model.getInstance().getCandyList();
        for(int i = 0; i < candyList.size(); i++){
            if(candyList.get(i) instanceof CherryCandy)
               vCandies.add(new VCherryCandy());
            if(candyList.get(i) instanceof NormalCandy)
                vCandies.add(new VNormalCandy());
            if(candyList.get(i) instanceof SuperCandy)
                vCandies.add(new VSuperCandy());
            if(candyList.get(i) instanceof TastyCandy)
                vCandies.add(new VTastyCandy());
        }
        return vCandies;
    }

    public static View getInstance(){
        if (View.view == null){
            view = new View();
            return view;
        }
        return view;
    }

    public void start(Stage primaryStage) {
        nbrX = Model.getWIDTH();
        nbrY = Model.getHEIGHT();
        Graph graph = getController().getModel().getGraph();
        System.out.println(graph.vertexSet().size());
        System.out.println(graph.edgeSet().size());
        primaryStage.setWidth(((WALL + CELL) * nbrX + WALL) * SPAN);
        primaryStage.setHeight(((WALL + CELL) * nbrY + WALL) * SPAN);
        primaryStage.setTitle("Labyrinthe");
        VGraph.drawMaze(primaryStage,graph.edgeSet());
        drawDoor();
        drawCandies();
        drawBadGuys();
        drawPlayer();
        primaryStage.show();
    }

    private void drawDoor(){
        int x = getController().getModel().getDoor().getX();
        int y = getController().getModel().getDoor().getY();
        vDoor.setX(x);
        vDoor.setY(y);
        //vdoor.getImagePlayer().setFocusTraversable(true); //Lucas ne sais pas ce que ca fait
        root.getChildren().add(vDoor.getImagePlayer());
    }
    private void drawBadGuys(){
    	for(int i = 0; i < vBadGuys.size(); i++) {
	    	int x = getController().getModel().getBadGuy(i).getX();
	        int y = getController().getModel().getBadGuy(i).getY();
	        vBadGuys.get(i).setX(x);
            vBadGuys.get(i).setY(y);
	        //vplayer.getImagePlayer().setFocusTraversable(true);
	        root.getChildren().add(vBadGuys.get(i).getImagePlayer());
    	}
    }

    private void drawPlayer(){
        int x = getController().getModel().getPlayer().getX();
        int y = getController().getModel().getPlayer().getY();
        vPlayer.setX(x);
        vPlayer.setY(y);
        vPlayer.getImagePlayer().setFocusTraversable(true);
        root.getChildren().add(vPlayer.getImagePlayer());
    }

    private void drawCandies(){
        for(int i = 0; i < vCandies.size(); i++) {
            int x = getController().getModel().getCandy(i).getX();
            int y = getController().getModel().getCandy(i).getY();
            vCandies.get(i).setX(x);
            vCandies.get(i).setY(y);
            //vplayer.getImagePlayer().setFocusTraversable(true);
            root.getChildren().add(vCandies.get(i).getImagePlayer());
        }
    }

	public static Controller getController() {
		return controller;
	}

}
