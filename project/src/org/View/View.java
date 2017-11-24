package org.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.Model.Vertex;

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

    public void start(Stage primaryStage){
        int nbrX = Vertex.RIGHT_BORDER;
        int nbrY = Vertex.BOTTOM_BORDER;
        primaryStage.setWidth(((WALL + CELL) * nbrX + WALL) * SPAN);
        primaryStage.setHeight(((WALL + CELL) * nbrY + WALL) * SPAN);
        primaryStage.setTitle("Labyrinthe");
        View.drawFrame(primaryStage,nbrX,nbrY);
        View.drawWall(0,0,0,1,WALLCOLOR);
/*        for(int i = 0; i<Vertex.BOTTOM_BORDER ; i++){
            for(int j = 0 ; j<Vertex.RIGHT_BORDER ; j++){
                VerticiesMatrix[i][j];
            } */
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
