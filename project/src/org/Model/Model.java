package org.Model;

/**
 * Created by lulu on 19/11/17.
 */
public class Model {
    private static Model model = null;


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
}
