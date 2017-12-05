package org.View;

import static org.View.VConsts.PATHBADGUY;

public class VBadGuy extends VPersonnage {

    public VBadGuy(){
        super(PATHBADGUY);
    }
    
    public void move() {
    	this.setX(View.getController().getModel().getPlayer().getX());
    	this.setY(View.getController().getModel().getPlayer().getY());
    }

}
