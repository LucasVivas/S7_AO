package org.View;

import org.Model.FinishedLevelException;
import org.Model.PlayerReachedException;

import static org.View.VConsts.PATHBADGUY;

public class VBadGuy extends VPersonnage {

    public VBadGuy(){
        super(PATHBADGUY);
    }
    
    public void move() throws PlayerReachedException, FinishedLevelException {
    	View.getController().getModel().getBadGuy(1).move(View.getController().getModel().getGraph());
    	this.setX(View.getController().getModel().getBadGuy(1).getX());
    	this.setY(View.getController().getModel().getBadGuy(1).getY());
    }

}
