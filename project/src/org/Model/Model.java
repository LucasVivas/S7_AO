package org.Model;

/**
 * Created by lulu on 19/11/17.
 */
public class Model {
    private static Model model = null;
    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;

    private Graph graph = Graph.getInstance();

    private Model() {
        super();
        graph.buildRandomPath();
    }

    public static Model getInstance(){
        if (Model.model == null){
            model = new Model();
        }
        return model;
    }

    public Graph getGraph() {
        return graph;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }
}
