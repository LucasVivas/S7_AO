package View;

/**
 * Created by lulu on 19/11/17.
 */
public class View {
    private static View view = null;


    private View() {
        super();
    }

    public static View getInstance(){
        if (View.view == null){
            view = new View();
            return view;
        }
        return null;
    }
}
