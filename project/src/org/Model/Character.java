package org.Model;

import java.util.Iterator;

/**
 * Class Character is used to create a Character which is a player or a bad guy.
 * @author Lucas Vivas, Gauthier Lamarque {@literal &} Co.
 * @version 1.0.0
 */
public class Character extends Vertex{
    /**
     * build a character with a random position.
     * @see Vertex
     */
    public Character(){
        super();
    }

    /**
     * build a character with the position (x,y).
     * @param x new x value
     * @param y new y value
     * @see Vertex
     */
    public Character(int x, int y) {
        super(x, y);
    }

    /**
     * @return the graph's vertex where the character is.
     */
    public Vertex getVertex() {
    	return Graph.getInstance().getVertex(this.getX(), this.getY());
    }

    /**
     * Move the player to a direction if it's possible.
     * @param direction the direction where the <b>Character</b> will move
     * @return boolean which is true if the character could move the character moved.
     * @throws PlayerReachedException //TODO: casa
     * @throws FinishedLevelException //TODO: casa
     */
    public boolean move(Directions direction) throws PlayerReachedException, FinishedLevelException {
        Iterator<Edge> graphEdgesIterator = Graph.getInstance().edgeSet().iterator();
        Edge E;
        int x_source = getX();
        int y_source = getY();
        int x_tmp = 0;
        int y_tmp = 0;

        switch (direction) {
            case NORTH:
                y_tmp = -1;
                break;
            case SOUTH:
                y_tmp = 1;
                break;
            case EAST:
                x_tmp = 1;
                break;
            case WEST:
                x_tmp = -1;
                break;
        }

        while (graphEdgesIterator.hasNext()) {
            E = graphEdgesIterator.next();
            if (((E.getSource().getX() == x_source && E.getSource().getY() == y_source)
                    && (E.getTarget().getX() == x_source + x_tmp && E.getTarget().getY() == y_source + y_tmp))
                    || ((E.getSource().getX() == x_source + x_tmp && E.getSource().getY() == y_source + y_tmp)
                    && (E.getTarget().getX() == x_source && E.getTarget().getY() == y_source))) {

                if(getX()+x_tmp == Player.getInstance().getX() && getY()+y_tmp == Player.getInstance().getY())
                    throw new PlayerReachedException();

                if(getX()+x_tmp == Door.getInstance().getX() && getY()+y_tmp == Door.getInstance().getY())
                    throw new FinishedLevelException();

                setX(getX() + x_tmp);
                setY(getY() + y_tmp);
                return true;
            }
        }
        return false;
    }
}

