package org.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static org.View.VConsts.*;

public class VPlayer {
	private ImageView imagePlayer;

	VPlayer() {
		Image image = new Image(getClass().getResource("../Ressources/player.png").toExternalForm());
        this.imagePlayer =  new ImageView((image));
	}

	ImageView getImagePlayer() {
		return imagePlayer;
	}
	
	public void setX(double x) {
		imagePlayer.setX((WALL + x * (WALL + CELL)) * SPAN);
	}
	public void setY(double y) {
		imagePlayer.setY((WALL + y * (WALL + CELL)) * SPAN);
	}


}
