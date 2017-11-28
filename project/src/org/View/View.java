package org.View;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.Controller.Controller;
import org.Model.Directions;
import org.Model.Edge;
import org.Model.Graph;
import org.Model.Model;

import com.sun.corba.se.impl.orbutil.graph.Node;

import java.util.Iterator;
import java.util.Set;

import static org.View.VConsts.*;

/**
 * Created by lulu on 19/11/17.
 */
public class View {
    private static View view = null;
    private static Controller controller = Controller.getInstance();

    private VPlayer vplayer;

    private View() {
        super();
        vplayer = new VPlayer();
    }



    public void start(Stage primaryStage) {
        int nbrX = Model.getWIDTH();
        int nbrY = Model.getHEIGHT();
        Graph graph = controller.getModel().getGraph();
        primaryStage.setWidth(((WALL + CELL) * nbrX + WALL) * SPAN);
        primaryStage.setHeight(((WALL + CELL) * nbrY + WALL) * SPAN);
        primaryStage.setTitle("Labyrinthe");
        VGraph.drawMaze(primaryStage, nbrX, nbrY,graph.getAllEdges(),graph.getGraphEdges());
        drawPlayer();
        primaryStage.show();
    }


    private void drawPlayer(){

        double x = controller.getModel().getPlayer().getX();
        double y = controller.getModel().getPlayer().getY();
        vplayer.setX(x);
        vplayer.setY(y);
        vplayer.getImagePlayer().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.RIGHT)) {
                	if (controller.getModel().checkMove(Directions.EAST))
                		vplayer.setX(controller.getModel().getPlayer().getX());
                }
                if (ke.getCode().equals(KeyCode.LEFT)) {
                	if (controller.getModel().checkMove(Directions.WEST))
                		vplayer.setX(controller.getModel().getPlayer().getX());
                }
                if (ke.getCode().equals(KeyCode.UP)) {
                	if (controller.getModel().checkMove(Directions.NORTH))
                		vplayer.setY(controller.getModel().getPlayer().getY());
                }
                if (ke.getCode().equals(KeyCode.DOWN)) {
                	if (controller.getModel().checkMove(Directions.SOUTH))
                		vplayer.setY(controller.getModel().getPlayer().getY());
                }
            }
        });
        vplayer.getImagePlayer().setFocusTraversable(true);
        root.getChildren().add(vplayer.getImagePlayer());
    }

    public static View getInstance(){
        if (View.view == null){
            view = new View();
            return view;
        }
        return view;
    }
}
