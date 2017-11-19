/**
 * Created by lulu on 19/11/17.
 */
import Controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main {
    private static Controller controller;

    public static void main(String[] args) {
        controller = Controller.getInstance();
    }

    public void start(Stage primaryStage) throws Exception {

    }
}
