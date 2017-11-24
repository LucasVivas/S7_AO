package org.Model;

/**
 * Created by lulu on 19/11/17.
 */
public class Model {
    private static Model model = null;
    private static final int HEIGHT = 3;
    private static final int WIDTH = 3;

    private Model() {
        super();
    }

    public static Model getInstance(){
        if (Model.model == null){
            model = new Model();
            return model;
        }
        return null;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }
}
