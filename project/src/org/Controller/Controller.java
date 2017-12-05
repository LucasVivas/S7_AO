package org.Controller;

import org.Model.Directions;
import org.Model.Model;
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
                 if (ke.getCode().equals(KeyCode.RIGHT)) {
                 	if (controller.getModel().getPlayer().move(Directions.EAST)) {
                 		view.vplayer.setX(controller.getModel().getPlayer().getX());
                 		model.getBadGuy(1).moove();
                 		view.vbadguy[1].move();
                 	}
                 }
                 if (ke.getCode().equals(KeyCode.LEFT)) {
                 	if (controller.getModel().getPlayer().move(Directions.WEST)) {
                 		view.vplayer.setX(controller.getModel().getPlayer().getX());
                 		model.getBadGuy(1).moove();
                 		view.vbadguy[1].move();
                 		}
                 }
                 if (ke.getCode().equals(KeyCode.UP)) {
                 	if (controller.getModel().getPlayer().move(Directions.NORTH)) {
                 		view.vplayer.setY(controller.getModel().getPlayer().getY());
                 		model.getBadGuy(1).moove();
                 		view.vbadguy[1].move();
                 		}
                 }
                 if (ke.getCode().equals(KeyCode.DOWN)) {
                 	if (controller.getModel().getPlayer().move(Directions.SOUTH)) {
                 		view.vplayer.setY(controller.getModel().getPlayer().getY());
                 		model.getBadGuy(1).moove();
                 		view.vbadguy[1].move();
                 		}
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
