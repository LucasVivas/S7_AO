package org.View;

import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.Model.Edge;

import java.util.Iterator;
import java.util.Set;

import static org.View.VConsts.*;

class VGraph {

    static void drawMaze(Stage stage, Set<Edge> graphEdges){
        drawFrame(stage, nbrX, nbrY);
        drawAllWalls(WALLCOLOR,nbrX,nbrY);
        drawWalls(graphEdges,SCENECOLOR);
    }


    private static void drawAllWalls(Paint color,int nbrX,int nbrY){
        for (int i = 0; i < nbrX; i++) {
            for (int j = 0; j < nbrY; j++) {
                drawWall(i, j, i+1, j, color);
                drawWall(i, j, i, j+1, color);
            }
        }
    }



    private static void drawFrame(Stage stage, int nbrX, int nbrY) {
        View.scene1 = new Scene(root,
                ((WALL + CELL) * nbrX + WALL) * SPAN,
                ((WALL + CELL) * nbrY + WALL) * SPAN);
        View.scene1.setFill(SCENECOLOR);

        Rectangle square;
       // stage.setScene(scene);

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



    private static void drawWalls(Set<Edge> set, Paint color){
        Iterator<Edge> graphEdgesIterator = set.iterator();
        Edge E;
        int xs, ys, xt, yt;
        while (graphEdgesIterator.hasNext()) {
            E = graphEdgesIterator.next();
            xs = (E.getSource().getX());
            ys = (E.getSource().getY());
            xt = (E.getTarget().getX());
            yt = (E.getTarget().getY());
            VGraph.drawWall(xs, ys, xt, yt, color);
        }
    }

    private static void drawWall(int xs, int ys, int xt, int yt, Paint color){
        int x, y, xspan, yspan;
        if(ys==yt){
            x = ((WALL+CELL) + (WALL+CELL) * ((xs+xt)/2)) * SPAN;
            y = (WALL + ys * (WALL+CELL)) * SPAN;
            xspan = WALL * SPAN;
            yspan = CELL * SPAN;
            Rectangle square = new Rectangle(x,y,xspan,yspan);
            square.setFill(color);
            root.getChildren().add(square);
        }
        else if(xs==xt){
            x = (WALL + xs * (WALL+CELL)) * SPAN;
            y = ((WALL+CELL) + (WALL+CELL) * ((ys+yt)/2)) * SPAN;
            xspan = CELL * SPAN;
            yspan = WALL * SPAN;
            Rectangle square = new Rectangle(x,y,xspan,yspan);
            square.setFill(color);
            root.getChildren().add(square) ;
        }
    }
}
