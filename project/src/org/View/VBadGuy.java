package org.View;

import org.Model.FinishedLevelException;
import org.Model.PlayerReachedException;

import static org.View.VConsts.PATHBADGUY;

public class VBadGuy extends VPersonnage {

    public VBadGuy() {
        super(PATHBADGUY);
    }

    public void move(int index) throws PlayerReachedException, FinishedLevelException {
        setX(View.getController().getModel().getBadGuy(index).getX());
        setY(View.getController().getModel().getBadGuy(index).getY());
    }
}
