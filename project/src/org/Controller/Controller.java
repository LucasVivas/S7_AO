package org.Controller;

import org.Model.Model;
import org.View.View;

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
