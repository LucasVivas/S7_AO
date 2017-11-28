package org.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class VPersonnage{

    ImageView imagePersonnage;

    public VPersonnage(Image image) {
        this.imagePersonnage =  new ImageView((image));
    }

    public ImageView getImagePlayer() {
        return imagePersonnage;
    }

    public void setX(double x) {
        imagePersonnage.setX((View.WALL + x * (View.WALL + View.CELL)) * View.SPAN);
    }
    public void setY(double y) {
        imagePersonnage.setY((View.WALL + y * (View.WALL + View.CELL)) * View.SPAN);
    }

}
