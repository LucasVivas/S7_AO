package org.View;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.Controller.Controller;
import org.Model.*;

import java.util.Iterator;
import java.util.Set;

import static org.View.VConsts.*;

/**
 * Created by lulu on 19/11/17.
 */
public class View {
    private static View view = null;
    private static Controller controller = Controller.getInstance();

    public VBadGuy vbadguy;
    public VPlayer vplayer;

    private View() {
        super();
        vplayer = new VPlayer();
        vbadguy = new VBadGuy();
    }



    public void start(Stage primaryStage) {
        nbrX = Model.getWIDTH();
        nbrY = Model.getHEIGHT();
        Graph graph = controller.getModel().getGraph();
        System.out.println(graph.vertexSet().size());
        System.out.println(graph.edgeSet().size());
        primaryStage.setWidth(((WALL + CELL) * nbrX + WALL+ WALL-1) * SPAN);
        primaryStage.setHeight(((WALL + CELL) * nbrY + WALL+ CELL-1) * SPAN);
        primaryStage.setTitle("Labyrinthe");
        VGraph.drawMaze(primaryStage,graph.edgeSet());
        drawPlayer();
        drawBadGuy();
        primaryStage.show();
    }


    private void drawPlayer(){
        double x = controller.getModel().getPlayer().getX();
        double y = controller.getModel().getPlayer().getY();
        vplayer.setX(x);
        vplayer.setY(y);
        vplayer.getImagePlayer().setFocusTraversable(true);
        root.getChildren().add(vplayer.getImagePlayer());
    }
    
    private void drawBadGuy(){

        double x = controller.getModel().getVillain().getX();
        double y = controller.getModel().getVillain().getY();
        vbadguy.setX(x);
        vbadguy.setY(y);
        vbadguy.getImagePlayer().setFocusTraversable(true);
        root.getChildren().add(vbadguy.getImagePlayer());
    }

    public static View getInstance(){
        if (View.view == null){
            view = new View();
            return view;
        }
        return view;
    }
}
