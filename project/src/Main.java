/**
 * Created by lulu on 19/11/17.
 */
import org.Controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static Controller controller;

    public static void main(String[] args) {
        controller = Controller.getInstance();
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        controller.start(primaryStage);
    }
}