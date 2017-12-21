package org.View;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.Controller.Controller;
import org.Model.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
    static Scene scene1,scene2, scene3;
    public Label pause;
    public Button startButton;
    public Button easy;
    public Button medium;
    public Button hard;
    private int timeBetweenMoves;

    private View() {
        super();
        vPlayer = new VPlayer();
        vDoor = new VDoor();
        vBadGuys = InitializeBadGuys();
        vCandies = InitializeCandies();
        pause = new Label("PAUSE\nPress SPACE to resume");
        pause.setFont(new Font("Cambria", 18));
        startButton = new Button("Start");
        easy = new Button("EASY");
        medium = new Button("MEDIUM");
        hard = new Button("HARD");
        timeBetweenMoves = 10;
    }

    public void setButtonsOnAction(Stage primaryStage){
        startButton.setOnAction(e -> {
            DrawLevelSelectScene();
            primaryStage.setTitle("Select difficulty");
            primaryStage.setScene(scene3);
            primaryStage.show();
        });
        easy.setOnAction(e -> {
            setTimeNetweenMoves(10);
            showGame(primaryStage);
        });
        medium.setOnAction(e -> {
            setTimeNetweenMoves(5);
            showGame(primaryStage);
        });
        hard.setOnAction(e -> {
            setTimeNetweenMoves(3);
            showGame(primaryStage);
        });
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

    public int getTimeBetweenMoves(){
        return timeBetweenMoves;
    }

    public void setTimeNetweenMoves(int timeBetweenMoves){
        this.timeBetweenMoves = timeBetweenMoves;
    }

    public void DrawStartScene(){
        Label label= new Label("Welcome !\nPress Start to play :)");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setTextFill(Color.web("#0076a3"));
        FontWeight fontWeight = FontWeight.NORMAL;
        FontPosture fontPosture = FontPosture.REGULAR;
        Font font = Font.font("Verdana", fontWeight,  fontPosture,20);
        label.setFont(font);
        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, startButton);
        scene2 = new Scene(layout, 300,150);
    }

    public void DrawLevelSelectScene(){
        Label label= new Label("Select your difficulty");
        label.setTextFill(Color.web("#0076a3"));
        FontWeight fontWeight = FontWeight.NORMAL;
        FontPosture fontPosture = FontPosture.REGULAR;
        Font font = Font.font("Verdana", fontWeight,  fontPosture,20);
        label.setFont(font);
        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(easy, medium, hard);
        layout.getChildren().addAll(label, buttons);
        scene3 = new Scene(layout, 300,150);
    }

    public void start(Stage primaryStage){
        DrawStartScene();
        primaryStage.setTitle("Welcome to the game");
        primaryStage.setScene(scene2);
        primaryStage.show();
        setButtonsOnAction(primaryStage);
    }
    
    public void showGame(Stage primaryStage){
        nbrX = Model.getWIDTH();
        nbrY = Model.getHEIGHT();
        Graph graph = getController().getModel().getGraph();
        System.out.println(graph.vertexSet().size());
        System.out.println(graph.edgeSet().size());
        VGraph.drawMaze(primaryStage,graph.edgeSet());
        Label label1 = new Label("Your score :" + Model.getInstance().getScore());
        Label label2 = new Label("Best score :" + Model.getInstance().getScore());
        HBox scores = new HBox(500);
        scores.getChildren().addAll(label1, label2);
        root.getChildren().add(scores);
        root.getChildren().add(pause);
        pause.setLayoutX(primaryStage.getWidth());
        pause.setLayoutY(primaryStage.getHeight());
        drawDoor();
        drawCandies();
        drawBadGuys();
        drawPlayer();
        primaryStage.hide();
        primaryStage.setWidth(((WALL + CELL) * nbrX + WALL) * SPAN);
        primaryStage.setHeight(((WALL + CELL) * nbrY + WALL) * SPAN);
        primaryStage.setTitle("Labyrinthe");
        primaryStage.setScene(scene1);
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

    public void deleteCandy(int indexCandy){
        VCandy vCandy = vCandies.get(indexCandy);
        root.getChildren().remove(vCandy.getImagePlayer());
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
