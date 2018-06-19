

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.sun.prism.paint.Color;

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


public class FirstGUI extends Application{

	
	public void start(Stage prime) throws Exception {

		prime.setTitle("Game Test WIP");
		Group root = new Group();
		Scene newScene = new Scene(root);
		prime.setScene(newScene);
		
		Button exit = new Button("Click to exit");
		exit.setLayoutX(10);
		exit.setLayoutY(10);
		
		Circle marble = new Circle(30);
		
		exit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});
		

		
		Canvas canvas = new Canvas(500,500);
		canvas.setStyle("-fx-background-color: black;");
		root.getChildren().add(canvas);
		root.getChildren().add(exit);
		System.out.println(canvas.getHeight());
		
		
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
				if (keysActive.contains("LEFT")&&x>0) {
					x--;
				}
				if (keysActive.contains("RIGHT")&&x<canvas.getWidth()-20) {
					x++;
				}
				if (keysActive.contains("UP")&&y>0) {
					y--;
				}
				if (keysActive.contains("DOWN")&&y<canvas.getHeight()-20) {
					y++;
				}
				
//				gc.fillText("test", x, y);
//				Image marble = new Image("marble.png");
//				gc.drawImage(marble, x, y,20,20);
				gc.fillOval(x, y, 20, 20);
				gc.fillText("x:" +x, 400, 10);
				gc.fillText("y:" +y, 400, 25);
				
				
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
