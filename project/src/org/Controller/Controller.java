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
	ExecutorService service;
	private int level;
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private Controller() {
		super();
		model = Model.getInstance();
		view = View.getInstance();
		service = Executors.newCachedThreadPool();
	}

	public void start(Stage primaryStage) {
		level = view.chooseLevel();
		view.start(primaryStage);
		controller.movement();
		controller.BadGuysMove();

	}

	public void BadGuysMove() {

		service.submit(new Runnable() {	
			@Override
			public void run() {


				try{
					while(true){
						model.getPlayer().notifyObserver();
						for(int i=0 ; i<getModel().getBadGuys().size() ; i++)
							view.vBadGuys.get(i).move(i);
						Thread.sleep(level*100);
					}
				} catch(PlayerReachedException e) {
					System.out.println("catched!");

				} catch (FinishedLevelException e) {

				} catch (InterruptedException e) {
					System.out.println("catched!1");
					//service.shutdownNow();
				}

			}

		});
	}

	public void movement() {

		view.vPlayer.getImagePlayer().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				try {
					if (ke.getCode().equals(KeyCode.RIGHT)) {
						if (model.getPlayer().move(Directions.EAST)) {
							view.vPlayer.setX(model.getPlayer().getX());
							//                             model.getPlayer().notifyObserver();
							//                             for(int i=0 ; i<getModel().getBadGuys().size() ; i++)
							//                                view.vBadGuys.get(i).move(i);
						}
					}
					if (ke.getCode().equals(KeyCode.LEFT)) {
						if (model.getPlayer().move(Directions.WEST)) {
							view.vPlayer.setX(model.getPlayer().getX());
							//                             model.getPlayer().notifyObserver();
							//                             for(int i=0 ; i<getModel().getBadGuys().size() ; i++)
							//                                 view.vBadGuys.get(i).move(i);
						}
					}
					if (ke.getCode().equals(KeyCode.UP)) {
						if (model.getPlayer().move(Directions.NORTH)) {
							view.vPlayer.setY(model.getPlayer().getY());
							//                             model.getPlayer().notifyObserver();
							//                             for(int i=0 ; i<getModel().getBadGuys().size() ; i++)
							//                                 view.vBadGuys.get(i).move(i);
						}
					}
					if (ke.getCode().equals(KeyCode.DOWN)) {
						if (model.getPlayer().move(Directions.SOUTH)) {
							view.vPlayer.setY(model.getPlayer().getY());
							//                             model.getPlayer().notifyObserver();
							//                             for(int i=0 ; i<getModel().getBadGuys().size() ; i++)
							//                                 view.vBadGuys.get(i).move(i);
						}
					}
				}catch (PlayerReachedException e){
					System.out.println("You lose !");
					//service.shutdownNow();
					Alert alert = new Alert(AlertType.INFORMATION, "Replay ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
					alert.showAndWait();

					if (alert.getResult() == ButtonType.YES) {
					}else{
					}
				}catch (FinishedLevelException e){
					System.out.println("You win !");
					service.shutdownNow();
					Alert alert = new Alert(AlertType.INFORMATION, "Replay ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
					alert.showAndWait();

					if (alert.getResult() == ButtonType.YES) {
					}else{
					}
				}
			}
		});
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
}