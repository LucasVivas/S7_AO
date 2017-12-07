package org.Model;

public interface Subject{
    public void register(BadGuy badGuy);
    public void unregister(BadGuy badGuy);
    public void notifyObserver() throws PlayerReachedException, FinishedLevelException;
}
