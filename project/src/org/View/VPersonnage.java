package org.View;

import javafx.scene.image.ImageView;

public abstract class VPersonnage extends VSprite{ //j'ai laisser cette classe mais il y a moyen qu'on en ai plus besoin

    protected VPersonnage(String pathToImage) {
        super(pathToImage);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
    }


}
