
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;


public class FirstGUI extends Application{

	
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
		
		
		
		
		Button exit = new Button("Exit");
		exit.setLayoutX(10);
		exit.setLayoutY(10);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});
		

		Canvas canvas = new Canvas(500,500);
		StackPane holder = new StackPane();
		holder.getChildren().add(canvas);
		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: BLACK;");
		root.getChildren().add(exit);
		System.out.println(canvas.getHeight());
		
		
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
				if ((keysActive.contains("LEFT") || keysActive.contains("A"))&&x>0) {
					x--;
				}
				if ((keysActive.contains("RIGHT") || keysActive.contains("D"))&&x<canvas.getWidth()-20) {
					x++;
				}
				if ((keysActive.contains("UP") || keysActive.contains("W"))&&y>0) {
					y--;
				}
				if ((keysActive.contains("DOWN") || keysActive.contains("S"))&&y<canvas.getHeight()-20) {
					y++;
				}
				
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
				
				//an obstacle
				gc.fillRect(30, 50, 10, 100);
				
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
