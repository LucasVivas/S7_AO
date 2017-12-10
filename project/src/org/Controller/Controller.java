package org.Controller;

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
	private ExecutorService service;
	private ExecutorService gameThread;
	private int level;
	private Stage stage;
	Thread t;
	Thread th;

	public int getLevel() {
		return level = 5;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private Controller() {
		super();
		model = Model.getInstance();
		view = View.getInstance();
		service = Executors.newCachedThreadPool();
		gameThread = Executors.newCachedThreadPool();

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

	public void start(Stage primaryStage) {
		//level = view.chooseLevel();
		view.startButton.setOnAction(e ->{
			view.showGame(primaryStage);
			view.vPlayer.getImagePlayer().setOnKeyPressed(event ->  {
				if (event.getCode().equals(KeyCode.SPACE)) {
					view.pause.setVisible(false);
					t.start();
				}
			});
		});
		view.start(primaryStage);
		t = new Thread() {

			public void run() {
				controller.movement();
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
						System.out.println("hellooo");
						model.getPlayer().notifyObserver();
						for(int i=0 ; i<getModel().getBadGuys().size() ; i++)
							view.vBadGuys.get(i).move(i);
						Thread.currentThread().sleep(1000);;
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

	public void movement() {
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
			}catch (PlayerReachedException e){
				//service.shutdownNow();
				Alert alert = new Alert(AlertType.NONE, "Replay ?", ButtonType.YES, ButtonType.NO);
				alert.setTitle("You lose !");
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
				}else{
				}
			}catch (FinishedLevelException e){

				//service.shutdownNow();
				Alert alert = new Alert(AlertType.NONE, "Replay ?", ButtonType.YES, ButtonType.NO);
				alert.setTitle("You win !");
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
				}else{
				}
			}
		});
	}

}