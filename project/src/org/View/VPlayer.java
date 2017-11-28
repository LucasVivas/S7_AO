package org.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VPlayer extends VPersonnage{

	public VPlayer(){
        Image image = new Image(getClass().getResource("../Ressources/player.png").toExternalForm());
        this.imagePersonnage =  new ImageView((image));
	}

}
