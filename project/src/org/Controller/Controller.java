package org.Controller;

import javafx.application.Application;
import org.Model.*;
import org.View.View;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created by lulu on 19/11/17.
 */
public class Controller {
    private static Controller controller = null;
    private static Model model = null;
    private static View view = null;


    private Controller() {
        super();
        model = Model.getInstance();
        view = View.getInstance();
    }

    public void start(Stage primaryStage){
        view.start(primaryStage);
        controller.movement();
    }
    
    public void movement() {
    	
    	 view.vplayer.getImagePlayer().setOnKeyPressed(new EventHandler<KeyEvent>() {
             @Override
             public void handle(KeyEvent ke) {
                 try {
                     if (ke.getCode().equals(KeyCode.RIGHT)) {
                         if (model.getPlayer().move(Directions.EAST)) {
                             view.vplayer.setX(model.getPlayer().getX());
                             model.getGraph().launchManhattan(model.getBadGuy(1), Player.getInstance());
                             model.getBadGuy(1).move(model.getGraph());
                             view.vbadguy[1].move();
                         }
                     }
                     if (ke.getCode().equals(KeyCode.LEFT)) {
                         if (model.getPlayer().move(Directions.WEST)) {
                             view.vplayer.setX(model.getPlayer().getX());
                             model.getGraph().launchManhattan(model.getBadGuy(1), Player.getInstance());
                             model.getBadGuy(1).move(model.getGraph());
                             view.vbadguy[1].move();
                         }
                     }
                     if (ke.getCode().equals(KeyCode.UP)) {
                         if (model.getPlayer().move(Directions.NORTH)) {
                             view.vplayer.setY(model.getPlayer().getY());
                             model.getGraph().launchManhattan(model.getBadGuy(1), Player.getInstance());
                             model.getBadGuy(1).move(model.getGraph());
                             view.vbadguy[1].move();
                         }
                     }
                     if (ke.getCode().equals(KeyCode.DOWN)) {
                         if (model.getPlayer().move(Directions.SOUTH)) {
                             view.vplayer.setY(model.getPlayer().getY());
                             model.getGraph().launchManhattan(model.getBadGuy(1), Player.getInstance());
                             model.getBadGuy(1).move(model.getGraph());
                             view.vbadguy[1].move();
                         }
                     }
                 }catch (PlayerReachedException e){
                     System.out.println("You lose !");

                 }catch (FinishedLevelException e){
                     System.out.println("You win !");
                 }
             }
         });
    }

    public static Controller getInstance(){
        if (Controller.controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}