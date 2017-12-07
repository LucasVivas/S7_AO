package org.View;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

final class VConsts {
    static final int SPAN = 4;//Pixels for a unit
    static final int WALL = 2;//thickness of the walls (in units)
    static final int CELL = 9;//size of the cells(in units)

    static final Paint WALLCOLOR = Color.BURLYWOOD;
    static final Paint SCENECOLOR = Color.ALICEBLUE;
    static final Group root = new Group();

    static int nbrX;
    static int nbrY;

    static final String PATHPLAYER = "../Ressources/player.png";
    static final String PATHBADGUY = "../Ressources/bad.png";
    static final String PATHDOOR = "../Ressources/door_open.png";
    static final String PATHCHERRYCANDY = "../Ressources/candy-3.png";
    static final String PATHNORMALCANDY = "../Ressources/candy-4.png";
    static final String PATHSUPERCANDY = "../Ressources/candy-1.png";
    static final String PATHTASTYCANDY = "../Ressources/candy-2.png";
}
