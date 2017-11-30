package org.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class VSprite {
    protected ImageView imagePersonnage;

    protected VSprite(String pathToImage){
        Image image = new Image(getClass().getResource(pathToImage).toExternalForm());
        this.imagePersonnage =  new ImageView((image));
    }

    public ImageView getImagePlayer() {
        return imagePersonnage;
    }
}
