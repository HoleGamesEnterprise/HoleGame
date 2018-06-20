
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.sun.prism.paint.Color;
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
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

public class FirstGUI extends Application {

	public void start(Stage prime) throws Exception {

		ArrayList<String> keysActive = new ArrayList<String>();
		Random a = new Random();
		Level[] levels = new Level[10];
		// vielleicht das ganze auf ne textdatei auslagern weil diese iwie miskenhaft
		// ist

		
		for (int i = 0; i < 10; i++) {
			levels[i] = new Level();
			levels[i].setGameObjectsList(new ArrayList<GameObjects>());
			for (int j = 0; j < 10; j++) {
				levels[i].add(new GameObjects());
			}
		}
		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				ArrayList<Pair<Integer, Integer>> coordsLevelOne = new ArrayList<Pair<Integer, Integer>>();
				for (int i = 0; i < 10; i++) {
					coordsLevelOne.add(new Pair<Integer, Integer>(a.nextInt(500), a.nextInt(500)));
				}
				levels[k].getGameObjectsList().get(j).setCoordsList(coordsLevelOne);
			}

		}

		prime.setTitle("Game Test WIP");
		Group root = new Group();
		Scene newScene = new Scene(root);
		prime.setScene(newScene);
		Button exit = new Button("Click to exit");
		exit.setLayoutX(10);
		exit.setLayoutY(10);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});
		Canvas canvas = new Canvas(500, 500);
		canvas.setStyle("-fx-background-color: black;"); // warum auch immer das nicht geht
		root.getChildren().add(canvas);
		root.getChildren().add(exit);
		System.out.println(canvas.getHeight());
		
		
		
		
		
		for (int i = 0; i<levels[1].getGameObjectsList().size(); i++) {
			int oldValX = levels[1].getGameObjectsList().get(i).getCoordsList().get(levels[1].getGameObjectsList().get(i).getCoordsLength()-1).getKey();
			int oldValY = levels[1].getGameObjectsList().get(i).getCoordsList().get(levels[1].getGameObjectsList().get(i).getCoordsLength()-1).getValue();
			for (int j = 0; j<levels[1].getGameObjectsList().get(i).getCoordsLength(); j++) {
				
				Pair<Integer,Integer> values = levels[1].getGameObjectsList().get(i).getCoordsList().get(j);
				Line line = new Line(oldValX, oldValY, values.getKey(), values.getValue());
				oldValX = values.getKey();
				oldValY = values.getValue();
				root.getChildren().add(line);
			}
		}
		
		
		
		
		
		
		
		
		
		newScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				if (!keysActive.contains(code)) {
					keysActive.add(code);
				}
			}
		}

		);

		newScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
			int x = 300;
			int y = 300;

			public void handle(long currentNanoTime) {
				gc.clearRect(0, 0, 500, 500);
				if (keysActive.contains("LEFT") && x > 0) {
					x--;
				}
				if (keysActive.contains("RIGHT") && x < canvas.getWidth() - 20) {
					x++;
				}
				if (keysActive.contains("UP") && y > 0) {
					y--;
				}
				if (keysActive.contains("DOWN") && y < canvas.getHeight() - 20) {
					y++;
				}

				// gc.fillText("test", x, y);
				// Image marble = new Image("marble.png");
				// gc.drawImage(marble, x, y,20,20);
				gc.fillOval(x, y, 20, 20);
				gc.fillText("x:" + x, 400, 10);
				gc.fillText("y:" + y, 400, 25);
				

			}

		}.start();
		
		
		
		
		
		
		
		
		
		
		
		prime.setResizable(false);
		// to be changed accordingly to the display on the raspberry pi
		prime.show();
	}

	
	public void drawLevel(int Level, Level[] levels) {
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
