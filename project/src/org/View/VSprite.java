package org.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class VSprite {
    protected ImageView imagePersonnage;

    protected VSprite(String pathToImage){
        Image image = new Image(getClass().getResource(pathToImage).toExternalForm());
        this.imagePersonnage =  new ImageView((image));
    }

    public void setX(double x) {
        imagePersonnage.setX((VConsts.WALL + x * (VConsts.WALL + VConsts.CELL)) * VConsts.SPAN);
    }

    public void setY(double y) {
        imagePersonnage.setY((VConsts.WALL + y * (VConsts.WALL + VConsts.CELL)) * VConsts.SPAN);
    }

    public ImageView getImagePlayer() {
        return imagePersonnage;
    }

}
