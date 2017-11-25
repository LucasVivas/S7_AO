package org.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.Model.Edge;
import org.Model.Graph;
import org.Model.Model;
import org.Model.Vertex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lulu on 19/11/17.
 */
public class View {
    private static View view = null;

    static final int SPAN = 4;//Pixels for a unit
    static final int WALL = 2;//thickness of the walls (in units)
    static final int CELL = 9;//size of the cells(in units)
    public static final Paint WALLCOLOR = Color.BURLYWOOD;
    public static final Paint SCENECOLOR = Color.ALICEBLUE;
    //public static final Pane pane = new Pane(); //pas sur
    public static final Group root = new Group();

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

    private View() {
        super();
    }

    public void start(Stage primaryStage) {
        int nbrX = Model.getWIDTH();
        int nbrY = Model.getHEIGHT();
        primaryStage.setWidth(((WALL + CELL) * nbrX + WALL) * SPAN);
        primaryStage.setHeight(((WALL + CELL) * nbrY + WALL) * SPAN);
        primaryStage.setTitle("Labyrinthe");
        View.drawFrame(primaryStage, nbrX, nbrY);
        Graph graph = Graph.getInstance();
        Vertex v = new Vertex(0,0,0);
        graph.addVertex(v);
        graph.setVerticesMatrix(v);
        graph.buildRandomPath(v);
        Set<Edge> graphEdgesSet = graph.getAllEdges();
        Set<Edge> pathSet = graph.getGraphEdges();
        Iterator<Edge> pathSetIterator = pathSet.iterator();
        Iterator<Edge> graphEdgesIterator = graphEdgesSet.iterator();
        while (graphEdgesIterator.hasNext()) {
            Edge E = graphEdgesIterator.next();
            int xs = (E.getSource().getX());
            int ys = (E.getSource().getY());
            int xt = (E.getTarget().getX());
            int yt = (E.getTarget().getY());
            View.drawWall(xs, ys, xt, yt, WALLCOLOR);
        }
        while (pathSetIterator.hasNext()) {
            Edge E = pathSetIterator.next();
            int xs = (E.getSource().getX());
            int ys = (E.getSource().getY());
            int xt = (E.getTarget().getX());
            int yt = (E.getTarget().getY());
            View.drawWall(xs, ys, xt, yt, SCENECOLOR);
        }
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
