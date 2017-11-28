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
import org.Model.*;

import com.sun.corba.se.impl.orbutil.graph.Node;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by lulu on 19/11/17.
 */
public class View {
    private static View view = null;
    private static Controller controller = Controller.getInstance();

    static final int SPAN = 4;//Pixels for a unit
    static final int WALL = 2;//thickness of the walls (in units)
    static final int CELL = 9;//size of the cells(in units)
    public static final Paint WALLCOLOR = Color.BURLYWOOD;
    public static final Paint SCENECOLOR = Color.ALICEBLUE;
    public static final Group root = new Group();
    private VPlayer vplayer;

    private View() {
        super();
        vplayer = new VPlayer() {
        };
    }

    public static void drawFrame(Stage stage, int nbrX, int nbrY) {
        Scene scene = new Scene(root,
                ((WALL + CELL) * nbrX + WALL) * SPAN,
                ((WALL + CELL) * nbrY + WALL) * SPAN);
        scene.setFill(SCENECOLOR);

        Rectangle square;
        stage.setScene(scene);

        square = new Rectangle(0, 0,
                SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
        square.setFill(WALLCOLOR);
        root.getChildren().add(square);

        square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)),
                SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
        square.setFill(WALLCOLOR);
        root.getChildren().add(square);

        square = new Rectangle(0, 0,
                WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALLCOLOR);
        root.getChildren().add(square);
        square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0,
                WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALLCOLOR);
        root.getChildren().add(square);
        for (int x = 0; x < nbrX - 1; ++x) {
            int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
            for (int y = 0; y < nbrY - 1; ++y) {
                int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
                square = new Rectangle(offsetX, offsetY,
                        WALL * SPAN, WALL * SPAN);
                square.setFill(WALLCOLOR);
                root.getChildren().add(square);
            }
        }
    }

    public static void drawWall(int xs, int ys, int xt, int yt, Paint color){
        int x = 0, y = 0, xspan = 0, yspan = 0;
        if(ys==yt){
            x = ((WALL+CELL) + (WALL+CELL) * ((int)(xs+xt)/2)) * SPAN;
            y = (WALL + ys * (WALL+CELL)) * SPAN;
            xspan = WALL * SPAN;
            yspan = CELL * SPAN;
            Rectangle square = new Rectangle(x,y,xspan,yspan);
            square.setFill(color);
            root.getChildren().add(square);
        }
        else if(xs==xt){
            x = (WALL + xs * (WALL+CELL)) * SPAN;
            y = ((WALL+CELL) + (WALL+CELL) * ((int)(ys+yt)/2)) * SPAN;
            xspan = CELL * SPAN;
            yspan = WALL * SPAN;
            Rectangle square = new Rectangle(x,y,xspan,yspan);
            square.setFill(color);
            root.getChildren().add(square) ;
        }
    }

    public void start(Stage primaryStage) {
        int nbrX = Model.getWIDTH();
        int nbrY = Model.getHEIGHT();
        Graph graph = controller.getModel().getGraph();
        primaryStage.setWidth(((WALL + CELL) * nbrX + WALL) * SPAN);
        primaryStage.setHeight(((WALL + CELL) * nbrY + WALL) * SPAN);
        primaryStage.setTitle("Labyrinthe");
        View.drawFrame(primaryStage, nbrX, nbrY);
        drawWalls(graph.getAllEdges(),WALLCOLOR);
        drawWalls(graph.getGraphEdges(),SCENECOLOR);
        drawPlayer();
        //drawBadGuys()
        primaryStage.show();
    }

    private void drawWalls(Set<Edge> set, Paint color){
        Iterator<Edge> graphEdgesIterator = set.iterator();
        Edge E;
        int xs, ys, xt, yt;
        while (graphEdgesIterator.hasNext()) {
            E = graphEdgesIterator.next();
            xs = (E.getSource().getX());
            ys = (E.getSource().getY());
            xt = (E.getTarget().getX());
            yt = (E.getTarget().getY());
            View.drawWall(xs, ys, xt, yt, color);
        }
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
                            //stockGrabber.hasMoved(x.y);
                    }
                    if (ke.getCode().equals(KeyCode.LEFT)) {
                        if (controller.getModel().checkMove(Directions.WEST))
                            vplayer.setX(controller.getModel().getPlayer().getX());
                            //stockGrabber.hasMoved(x.y);
                    }
                    if (ke.getCode().equals(KeyCode.UP)) {
                        if (controller.getModel().checkMove(Directions.NORTH))
                            vplayer.setY(controller.getModel().getPlayer().getY());
                            //stockGrabber.hasMoved(x.y);
                    }
                    if (ke.getCode().equals(KeyCode.DOWN)) {
                        if (controller.getModel().checkMove(Directions.SOUTH))
                            vplayer.setY(controller.getModel().getPlayer().getY());
                            //stockGrabber.hasMoved(x.y);
                    }
                }
            });
            vplayer.getImagePlayer().setFocusTraversable(true);
            root.getChildren().add(vplayer.getImagePlayer());
        }

/*    private void drawBadGuys(){

        StockObserver
        StockGrabber stockGrabber = new StockGrabber();
        StockObserver observer1 = new StockObserver(stockGrabber);
        stockGrabber.hasMoved(197.00);
        stockGrabber.setAAPLPrice(677.60);
        stockGrabber.setGOOGPrice(676.40);
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
    } */


    public static View getInstance(){
        if (View.view == null){
            view = new View();
            return view;
        }
        return view;
    }
}
