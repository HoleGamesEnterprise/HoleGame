
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
		/*
		 * start ist bei jeder javafx dabei, also nichts besonderes. Noetig waere aber noch ne gescheite exception handling sache
		 */

		ArrayList<String> keysActive = new ArrayList<String>();
		/*
		 * Zwischenspeicher welche tasten grade aktiv gedrueckt werden. Wird von den Eventhandlern gehandhabt. Bsp: Eintrag "LEFT" und "DOWN" 
		 */
		Random a = new Random();
		
		/*
		 * 10 level für den anfang, nach der praesi koennen wir ja noch mehr einfuegen
		 */
		Level[] levels = new Level[10];
		

		
		
		
		/*
		 * gibt jeden der zehn level dir wir haben eine GameObject Arraylist.
		 */
		for (int i = 0; i < levels.length; i++) {
			levels[i] = new Level();
			levels[i].setGameObjectsList(new ArrayList<GameObjects>());
			for (int j = 0; j < 2; j++) {
				levels[i].add(new GameObjects());
				/*
				 * BUG: Wir sind nach dem jetzigen design gezwungen nicht mehr oder weniger GameObjects hinzuzufügen als wir dann auch initalisieren
				 * pls fix rito
				 */
			}
		}

		
		
		levels[0].getGameObjectsList().get(0).setCoordsList(new ArrayList<Pair<Integer,Integer>>());
		levels[0].getGameObjectsList().get(0).addCoord(new Pair<Integer,Integer>(100,100));
		levels[0].getGameObjectsList().get(0).addCoord(new Pair<Integer,Integer>(400,100));
		levels[0].getGameObjectsList().get(0).addCoord(new Pair<Integer,Integer>(400,400));
		levels[0].getGameObjectsList().get(0).addCoord(new Pair<Integer,Integer>(100,400));
		levels[0].getGameObjectsList().get(1).addCoord(new Pair<Integer,Integer>(250,100));
		levels[0].getGameObjectsList().get(1).addCoord(new Pair<Integer,Integer>(100,400));
		levels[0].getGameObjectsList().get(1).addCoord(new Pair<Integer,Integer>(400,400));
		
		/*
		 * test objekte. einmal ein viereck und einmal ein dreieck. Versucht mal mehr hinzuzufügen
		 * !!!!!!!Vergisst nicht, wenn ihr mehr haben wollt, oben die schleife auf eins zu erweitern!!!!!!!
		 */
		
		
		
		
		

		prime.setTitle("Game Test WIP"); //titel
		Group root = new Group();	//gruppe der einfachheit wegen
		Scene newScene = new Scene(root);	//Die szene gehört jetzt root an
		prime.setScene(newScene);	//newscene soll angezeigt werden
		Button exit = new Button("Click to exit");	//exit button ungso ez pz
		exit.setLayoutX(10);	//position unso
		exit.setLayoutY(10);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.exit();	//beendet das ganze sobald der button gedrückt wird
			}
		});
		Canvas canvas = new Canvas(500, 500);	//canvas notwendig für interaktive und sich bewegende figuren etc
		canvas.setStyle("-fx-background-color: black;"); // warum auch immer das nicht geht
		root.getChildren().add(canvas);	//der ganze mist hinzugefuegt
		root.getChildren().add(exit);
		System.out.println(canvas.getHeight());	//debug
		
		
		
		/*
		 * Die hier unten stehende verschachtelte schleife braucht nur noch dass sie erkennt wenn ein level nicht fertig ist usw. Sie ist fürs zeichnen zustädnig
		 */
		
		for (int i = 0; i<levels[0].getGameObjectsList().size(); i++) {
			/*
			 * Fuer die anzahl der gameobjects in einen level machst du
			 */
			int oldValX = levels[0].getGameObjectsList().get(i).getCoordsList().get(levels[0].getGameObjectsList().get(i).getCoordsLength()-1).getKey();
			int oldValY = levels[0].getGameObjectsList().get(i).getCoordsList().get(levels[0].getGameObjectsList().get(i).getCoordsLength()-1).getValue();
			for (int j = 0; j<levels[0].getGameObjectsList().get(i).getCoordsLength(); j++) {
				/*
				 * male eine linie von den alten koords aus zu den neuen. 
				 * ganz praktisch dass am anfang der schleife die letze koordinate das letzte element der liste ist
				 */
				
				Pair<Integer,Integer> values = levels[0].getGameObjectsList().get(i).getCoordsList().get(j);
				Line line = new Line(oldValX, oldValY, values.getKey(), values.getValue());
				oldValX = values.getKey();
				oldValY = values.getValue();
				root.getChildren().add(line);
			}
		}
		
		
		
		
		
		
		
		/*
		 * eventhandler unso, dazu gibts nicht all zu viel zu sagen
		 */
		
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
				/*
				 * vier mal ifs, damit die kugel auch schräg fliegen kann
				 */
				// gc.fillText("test", x, y);
				// Image marble = new Image("marble.png");
				// gc.drawImage(marble, x, y,20,20);
				gc.fillOval(x, y, 20, 20);
				gc.fillText("x:" + x, 400, 10);	//debug
				gc.fillText("y:" + y, 400, 25);
				

			}

		}.start();
		
		
		
		
		
		
		
		
		
		
		
		prime.setResizable(false);	//kein bock lol
		// to be changed accordingly to the display on the raspberry pi
		prime.show();
	}

	
	public void drawLevel(int Level, Level[] levels) {
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
