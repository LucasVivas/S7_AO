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
                 	if (controller.getModel().checkMove(Directions.EAST))
                 		view.vplayer.setX(controller.getModel().getPlayer().getX());
                 }
                 if (ke.getCode().equals(KeyCode.LEFT)) {
                 	if (controller.getModel().checkMove(Directions.WEST))
                 		view.vplayer.setX(controller.getModel().getPlayer().getX());
                 }
                 if (ke.getCode().equals(KeyCode.UP)) {
                 	if (controller.getModel().checkMove(Directions.NORTH))
                 		view.vplayer.setY(controller.getModel().getPlayer().getY());
                 }
                 if (ke.getCode().equals(KeyCode.DOWN)) {
                 	if (controller.getModel().checkMove(Directions.SOUTH))
                 		view.vplayer.setY(controller.getModel().getPlayer().getY());
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
