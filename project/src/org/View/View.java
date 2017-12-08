package org.View;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.Model.*;
import org.Controller.Controller;
import static org.View.VConsts.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



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


    public static View getInstance(){
        if (View.view == null){
            view = new View();
            return view;
        }
        return view;
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

    public int chooseLevel(){
        List<String> choices = new ArrayList<>();
        choices.add("EASY");
        choices.add("NORMAL");
        choices.add("HARD");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("EASY", choices);
        dialog.setTitle("Level difficulty");
        dialog.setContentText("Choose the difficulty:");
        int timeBetweenMovements = 10;

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
            switch (result.get()){
                case "EASY":
                    timeBetweenMovements = 10;
                    break;
                case "NORMAL":
                    timeBetweenMovements = 6;
                    break;
                case "HARD":
                    timeBetweenMovements = 3;
                    break;
            }
        }
        return timeBetweenMovements;
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
        drawScores();
        drawDoor();
        drawCandies();
        drawBadGuys();
        drawPlayer();
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Alert alert = new Alert(AlertType.NONE, "Are you done playing ?", ButtonType.NO, ButtonType.YES);
            alert.setTitle("Exit game");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES){
                primaryStage.close();
                System.exit(0);
            }
            e.consume();
        });
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

    private void drawScores(){
        Label label1 = new Label("Your score :" + Model.getInstance().getScore());
        Label label2 = new Label("Best score :" + Model.getInstance().getScore());
        HBox scores = new HBox(500);
        scores.getChildren().addAll(label1, label2);
        //scores.setFocusTraversable(true);
        root.getChildren().add(scores);
    }

	public static Controller getController() {
		return controller;
	}

}
