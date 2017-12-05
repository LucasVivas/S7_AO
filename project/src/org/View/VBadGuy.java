package org.View;

import static org.View.VConsts.PATHBADGUY;

public class VBadGuy extends VPersonnage {

    public VBadGuy(){
        super(PATHBADGUY);
    }
    
    public void move() {
    	View.getController().getModel().getBadGuy(1).move(View.getController().getModel().getGraph());
    	this.setX(View.getController().getModel().getBadGuy(1).getX());
    	this.setY(View.getController().getModel().getBadGuy(1).getY());
    }

}
