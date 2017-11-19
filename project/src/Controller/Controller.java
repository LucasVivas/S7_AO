package Controller;

import Model.Model;
import View.View;
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



    public static Controller getInstance(){
        if (Controller.controller == null){
            controller = new Controller();
            return controller;
        }
        return null;
    }
}
