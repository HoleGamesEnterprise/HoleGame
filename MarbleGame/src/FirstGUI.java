import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.util.Pair;


public class FirstGUI extends Application{

	//did not figure out how to bounce away from obstacle
	
	public void start(Stage prime) throws Exception {

		//vielleicht das ganze auf ne textdatei auslagern weil diese iwie miskenhaft ist
		
		Level[] levels = new Level[10];
		for (int i = 0; i<10; i++) {
			levels[i]= new Level();
			levels[i].setGameObjects(new ArrayList<GameObjects>());
			for (int j = 0; j<10; j++) {
				levels[i].add(new GameObjects());
			}
		}
		
//		
		
		MenuBar menuBar = new MenuBar();
		Menu menuLevel = new Menu("Level");
		MenuItem l1 = new MenuItem("Level 1");
		MenuItem l2 = new MenuItem("Level 2");
		MenuItem l3 = new MenuItem("Level 3");
		MenuItem l4 = new MenuItem("Level 4");
		menuLevel.getItems().addAll(l1, l2, l3, l4);
		Menu menuExit = new Menu("Help");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				Platform.exit();
			}
		});
		menuExit.getItems().add(exit);
		menuBar.getMenus().addAll(menuLevel, menuExit);
		
		Random a = new Random();
		
		for (int k = 0; k<10; k++) {
			for (int j = 0; j<10; j++) {
				ArrayList<Pair<Integer,Integer>> coordsLevelOne = new ArrayList<Pair<Integer,Integer>>();
				for (int i = 0; i<10; i++) {
					coordsLevelOne.add(new Pair<Integer,Integer>(a.nextInt(500),a.nextInt(500)));
				}
				levels[k].getGameObjectsList().get(j).setCoords(coordsLevelOne);
			}

		}


		prime.setTitle("Game Test WIP");
		Group root = new Group();
		Scene newScene = new Scene(root);
		prime.setScene(newScene);
		
		
		/*Button exit = new Button("End\nGame");
		exit.setLayoutX(445);
		exit.setLayoutY(0);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});*/
		

		Canvas canvas = new Canvas(500,500);
		StackPane holder = new StackPane();
		holder.getChildren().add(canvas);
		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: BLACK;");
		//root.getChildren().add(exit);
		root.getChildren().add(menuBar);
		//System.out.println(canvas.getHeight());
		
		//obstacles
		Rectangle rl = new Rectangle(220, 220, 10, 90);
		rl.setFill(Color.WHITE);
		
		Rectangle rr = new Rectangle(300, 220, 10, 90);
		rr.setFill(Color.WHITE);
		
		root.getChildren().addAll(rl, rr);
		
		ArrayList<String> keysActive = new ArrayList<String>();
		
		newScene.setOnKeyPressed(
				//add pressed keys to list
				new EventHandler<KeyEvent>() {
					public void handle(KeyEvent e) {
						String code = e.getCode().toString();
						
						if (!keysActive.contains(code)) {
							keysActive.add(code);
						}
					}
				}
				
		);
		
		
		newScene.setOnKeyReleased(
				//remove released key from list
				new EventHandler<KeyEvent>() {
					public void handle(KeyEvent e) {
						String code = e.getCode().toString();
						
						if (keysActive.contains(code)) {
							keysActive.remove(code);
						}
					}
				}
				
				
		);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		new AnimationTimer() {
			//flexible spawnposition of ball
			Random r = new Random();
			int x = r.nextInt((int)((canvas.getWidth()-21)+1));			//300;
			int y = r.nextInt((int)((canvas.getWidth()-21)+1));			//300;
			
			public void handle(long currentNanoTime) {
				gc.clearRect(0, 0, 500, 500);
				
				//make sure the ball moves the right way and does not leave the window
				if(keysActive.contains("LEFT") &&x>0) {
					x--;
				}
				if(keysActive.contains("RIGHT") &&x<canvas.getWidth()-20) {
					x++;
				}
				if(keysActive.contains("UP") &&y>0) {
					y--;
				}
				if(keysActive.contains("DOWN") &&y<canvas.getHeight()-20) {
					y++;
				}
				//if(x )
				
				
				//if the ball is in the target
				if((x > 250 && x < 260) && (y > 250 && y < 260)) {
					System.out.println("WON");
					Platform.exit();
				}	
				
				//moving ball
				gc.setFill(Color.WHITE);;
				gc.fillOval(x, y, 20, 20);
				
				//information about position of ball 
				gc.fillText("x: " +x, 400, 10);
				gc.fillText("y: " +y, 400, 25);

				//the target
				gc.setStroke(Color.RED);
				gc.strokeOval(250, 250, 30, 30);
				
				
			}
			
			public void drawObjects() {
				//react to the levels array somehow
			}
		}.start();
		prime.setResizable(false);
		//to be changed accordingly to the display on the raspberry pi
		prime.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	

}
