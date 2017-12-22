package org.Controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.Model.*;
import org.View.View;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created by lulu on 19/11/17.
 */
public class Controller {
	private static Controller controller = null;
	private static Model model = null;
	private static View view = null;
	private Stage stage;
	private Thread t;
	private Thread th;
	private boolean onPause = false;

	private Controller() {
		super();
		model = Model.getInstance();
		view = View.getInstance();
		t = new Thread();
		th = new Thread();
	}

	public static Controller getInstance(){
		if (Controller.controller == null){
			controller = new Controller();
		}
		return controller;
	}

	public Model getModel() {
		return model;
	}

	public View getView() {
		return view;
	}

	public void start(Stage primaryStage) throws InterruptedException {
        view.start(primaryStage);
        view.vPlayer.getImagePlayer().setOnKeyPressed(event ->  {
            if (event.getCode().equals(KeyCode.SPACE)) {
					view.pause.setVisible(false);
					t.start();
				}
		});
		t = new Thread() {

			public void run(){
                try{
                    controller.movement(primaryStage);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
				controller.BadGuysMove();
				th.start();
			}

		};
		primaryStage.setOnCloseRequest(e -> {
			Alert alert = new Alert(AlertType.NONE, "Are you done playing ?", ButtonType.NO, ButtonType.YES);
			alert.setTitle("Exit game");
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				if(t.isAlive()){
					if(th.isAlive())
						th.interrupt();
					t.interrupt();
				}
				primaryStage.close();
			}else{
				e.consume();
			}
		});
	}

	public void BadGuysMove() {
		th = new Thread() {

			@SuppressWarnings("static-access")
			public void run() {
				try{
					while(true){
						model.getPlayer().notifyObserver();
						for(int i=0 ; i<getModel().getBadGuys().size() ; i++)
							view.vBadGuys.get(i).move(i);
						Thread.currentThread().sleep(view.getTimeBetweenMoves()*100);
					}
				} catch(PlayerReachedException e) {
					System.out.println("catched!");

				} catch (FinishedLevelException e) {

				} catch (InterruptedException e) {
					System.out.println("catched!1");
					//service.shutdownNow();
				}
			}

		};

	}

	public synchronized boolean doPause(){
		if(!onPause) {
			try {
			//	t.wait();
				th.wait();
				onPause = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(onPause){
		//	t.notify();
			th.notify();
			onPause = false;
		}
		return onPause;
    }

	public int candyCollapse(){
	    int score = 0;
        ArrayList<Candy> candyList = model.getCandyList();
        for (int i = 0; i < candyList.size(); i++) {
            Candy candy = candyList.get(i);
            if (candy.compareTo(model.getPlayer()) == 0) {
                view.deleteCandy(i);
                model.setScore(model.getScore() + candy.getNbrPoints());
                score = model.getScore();
            }
        }
        return score;
	}

	public void movement(Stage primaryStage) throws InterruptedException {
		view.vPlayer.getImagePlayer().setOnKeyPressed(event ->  {
			try {
				if (event.getCode().equals(KeyCode.RIGHT)) {
					if (model.getPlayer().move(Directions.EAST)) {
						view.vPlayer.setX(model.getPlayer().getX());
					}
				}
				if (event.getCode().equals(KeyCode.LEFT)) {
					if (model.getPlayer().move(Directions.WEST)) {
						view.vPlayer.setX(model.getPlayer().getX());
					}
				}
				if (event.getCode().equals(KeyCode.UP)) {
					if (model.getPlayer().move(Directions.NORTH)) {
						view.vPlayer.setY(model.getPlayer().getY());
					}
				}
				if (event.getCode().equals(KeyCode.DOWN)) {
					if (model.getPlayer().move(Directions.SOUTH)) {
						view.vPlayer.setY(model.getPlayer().getY());
					}
				}
				candyCollapse();
				view.updateScore();
				if (event.getCode().equals(KeyCode.SPACE) && !onPause) {
					doPause();
				}

                if (event.getCode().equals(KeyCode.SPACE) && onPause){
					doPause();
                }
			}catch (PlayerReachedException e){
				//service.shutdownNow();
				Alert alert = new Alert(AlertType.NONE, "Replay ?", ButtonType.YES, ButtonType.NO);
				alert.setTitle("You lose !");
				alert.showAndWait();

				if (alert.getResult() == ButtonType.NO) {
                    System.exit(0);
				}else{
				}
			}catch (FinishedLevelException e){
				//service.shutdownNow();
				Alert alert = new Alert(AlertType.NONE, "Replay ?", ButtonType.YES, ButtonType.NO);
				alert.setTitle("You win !");
				alert.showAndWait();

				if (alert.getResult() == ButtonType.NO){
                    System.exit(0);
				}else{
					view.showGame(primaryStage);
				}
			}
		});
	}

}