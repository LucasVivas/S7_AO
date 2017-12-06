package org.Model;

public interface Observer{

    void update(int newX, int newY) throws PlayerReachedException, FinishedLevelException;

}
