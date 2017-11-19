package Controller;

/**
 * Created by lulu on 19/11/17.
 */
public class Controller {
    private static Controller controller = null;

    private Controller() {
        super();
    }

    public static Controller getInstance(){
        if (Controller.controller == null){
            controller = new Controller();
            return controller;
        }
        return null;
    }
}
