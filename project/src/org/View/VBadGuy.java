package org.View;

import org.Model.FinishedLevelException;
import org.Model.PlayerReachedException;

import java.util.ArrayList;

import static org.View.VConsts.PATHBADGUY;

public class VBadGuy extends VSprite {

    public VBadGuy() {
        super(PATHBADGUY);
    }

    public void move(int index) throws PlayerReachedException, FinishedLevelException {
        View.getController().getModel().getBadGuy(index).move(View.getController().getModel().getGraph());
        setX(View.getController().getModel().getBadGuy(index).getX());
        setY(View.getController().getModel().getBadGuy(index).getY());
    }
}
