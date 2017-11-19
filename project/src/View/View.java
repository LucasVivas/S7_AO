package View;

import javafx.stage.Stage;

/**
 * Created by lulu on 19/11/17.
 */
public class View {
    private static View view = null;


    private View() {
        super();
    }

    public void start(Stage primaryStage){
        primaryStage.setWidth(1024);
        primaryStage.setHeight(968);
        primaryStage.setTitle("Labyrinthe");
        primaryStage.show();
    }

    public static View getInstance(){
        if (View.view == null){
            view = new View();
            return view;
        }
        return null;
    }
}
