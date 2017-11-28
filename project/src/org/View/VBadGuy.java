package org.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VBadGuy extends VPersonnage {

    public VBadGuy(){
        Image image = new Image(getClass().getResource("../Ressources/bad.png").toExternalForm());
        this.imagePersonnage =  new ImageView((image));
    }

}
