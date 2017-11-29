package org.View;

import javafx.scene.image.ImageView;

public abstract class VPersonnage{

    protected ImageView imagePersonnage;

    public ImageView getImagePlayer() {
        return imagePersonnage;
    }

    public void setX(double x) {
        imagePersonnage.setX((VConsts.WALL + x * (VConsts.WALL + VConsts.CELL)) * VConsts.SPAN);
    }

    public void setY(double y) {
        imagePersonnage.setY((VConsts.WALL + y * (VConsts.WALL + VConsts.CELL)) * VConsts.SPAN);
    }

}
