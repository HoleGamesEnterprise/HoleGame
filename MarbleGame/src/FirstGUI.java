

import java.awt.event.MouseEvent;
import java.util.ArrayList;



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
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class FirstGUI extends Application{

	
	public void start(Stage prime) throws Exception {

		prime.setTitle("Game Test WIP");
		Group root = new Group();
		Scene newScene = new Scene(root);
		prime.setScene(newScene);
		Button exit = new Button("Click to exit");
		exit.setLayoutX(10);
		exit.setLayoutY(10);
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});
		

		
		Canvas canvas = new Canvas(500,500);
		root.getChildren().add(canvas);
		root.getChildren().add(exit);
		
		
		ArrayList<String> keysActive = new ArrayList<String>();
		
		newScene.setOnKeyPressed(
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
			int x = 300;
			int y = 300;
			public void handle(long currentNanoTime) {
				gc.clearRect(0, 0, 500, 500);
				if (keysActive.contains("LEFT")) {
					x--;
				}
				if (keysActive.contains("RIGHT")) {
					x++;
				}
				if (keysActive.contains("UP")) {
					y--;
				}
				if (keysActive.contains("DOWN")) {
					y++;
				}
				
				gc.fillText("test", x, y);
				
			}
		}.start();
		
		prime.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
