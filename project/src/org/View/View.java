package org.View;

import javafx.scene.image.ImageView;
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

    public VPlayer vplayer;
    public VDoor vdoor;
    public ArrayList<VBadGuy> vbadguy;
    protected ImageView imagePersonnage;

    private View() {
        super();
        vplayer = new VPlayer();
        vdoor = new VDoor();
    }

    public ArrayList<VBadGuy> InitializeList(){
        vbadguy = new ArrayList<>(Model.getNB_BADGUYS());;
        for(int i = 0; i < Model.getNB_BADGUYS(); i++)
            vbadguy.add(new VBadGuy());
        return vbadguy;
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
        drawBadGuys();
        drawPlayer();
        primaryStage.show();
    }

    private void drawDoor(){
        int x = getController().getModel().getDoor().getX();
        int y = getController().getModel().getDoor().getY();
        vdoor.setX(x);
        vdoor.setY(y);
        //vdoor.getImagePlayer().setFocusTraversable(true); //Lucas ne sais pas ce que ca fait
        root.getChildren().add(vdoor.getImagePlayer());
    }
    private void drawBadGuys(){
        vbadguy = InitializeList();
    	for(int i = 0; i < vbadguy.size(); i++) {
	    	int x = getController().getModel().getBadGuy(i).getX();
	        int y = getController().getModel().getBadGuy(i).getY();
	        vbadguy.get(i).setX(x);
            vbadguy.get(i).setY(y);
	        //vplayer.getImagePlayer().setFocusTraversable(true);
	        root.getChildren().add(vbadguy.get(i).getImagePlayer());
    	}
    }

    private void drawPlayer(){
        int x = getController().getModel().getPlayer().getX();
        int y = getController().getModel().getPlayer().getY();
        vplayer.setX(x);
        vplayer.setY(y);
        vplayer.getImagePlayer().setFocusTraversable(true);
        root.getChildren().add(vplayer.getImagePlayer());
    }

	public static Controller getController() {
		return controller;
	}

}
