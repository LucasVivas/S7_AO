package org.View;

import javafx.scene.image.ImageView;

public abstract class VPersonnage extends VSprite{ //j'ai laisser cette classe mais il y a moyen qu'on en ai plus besoin

    protected VPersonnage(String pathToImage) {
        super(pathToImage);
    }

    public void setX(double x) {
        imagePersonnage.setX((VConsts.WALL + x * (VConsts.WALL + VConsts.CELL)) * VConsts.SPAN);
    }

    public void setY(double y) {
        imagePersonnage.setY((VConsts.WALL + y * (VConsts.WALL + VConsts.CELL)) * VConsts.SPAN);
    }

}
