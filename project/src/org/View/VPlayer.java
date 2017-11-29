package org.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VPlayer {
	ImageView imagePlayer;
	private static VPlayer vplayer = null;
	private VPlayer() {
		Image image = new Image(getClass().getResource("../Ressources/player.png").toExternalForm());
        this.imagePlayer =  new ImageView((image));
	}
	
	 public static VPlayer getInstance(){
	        if (VPlayer.vplayer == null){
	            vplayer = new VPlayer();
	            return vplayer;
	        }
	        return vplayer;
	    }

	public ImageView getImagePlayer() {
		return imagePlayer;
	}
	
	public void setX(double x) {
		imagePlayer.setX((View.WALL + x * (View.WALL + View.CELL)) * View.SPAN);
	}
	public void setY(double y) {
		imagePlayer.setY((View.WALL + y * (View.WALL + View.CELL)) * View.SPAN);
	}
}
