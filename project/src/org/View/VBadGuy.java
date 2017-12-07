package org.View;

import org.Model.FinishedLevelException;
import org.Model.PlayerReachedException;

import static org.View.VConsts.PATHBADGUY;

public class VBadGuy extends VPersonnage {

    public VBadGuy() {
        super(PATHBADGUY);
    }

    public void move(int index) throws PlayerReachedException, FinishedLevelException {
        int x = View.getController().getModel().getBadGuy(index).getX();
        int y = View.getController().getModel().getBadGuy(index).getY();
        setX(x);
        setY(y);
        System.out.println("N°" + index + " : X=" + x + ", Y=" + y);
    }
}
