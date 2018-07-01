import java.util.ArrayList;

import javafx.scene.paint.Color;

import javafx.animation.AnimationTimer;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;

public class FirstGUI extends Application {
	
	//objekte, variablen sofern moeglich ausserhalb von start
	
	//liste fuer gedrueckte tasten
	ArrayList<String> keysActive = new ArrayList<String>();
	//liste fuer hindernisse
	ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();
	//liste fuer bounds(hindernisse)
	ArrayList<BoundingBox> bounds = new ArrayList<BoundingBox>();
	Level[] levels = new Level[2];
	//Level[] levels = new Level[5];
	
	//speichert gewaehltes level
	int levelAct = 0;

	//hindernisse (aktuell nur 2 in benutzung)
	Rectangle obst1 = new Rectangle(220.1, 220.1, 10, 90);
	Rectangle obst2 = new Rectangle(300.1, 220.1, 10, 90);
	/*
 	Rectangle obst3 = new Rectangle(160.1, 150.1, 10, 90);
	Rectangle obst4 = new Rectangle(360.1, 150.1, 10, 90);
	Rectangle obst5 = new Rectangle(200.1, 350.1, 130, 10);
	*/
	
	//bounds der hindernisse (ebenfalls nur 2 in benutzung)
	BoundingBox bb1 = new BoundingBox(220.1, 220.1, 10, 90);
	BoundingBox bb2 = new BoundingBox(300.1, 220.1, 10, 90);
	/*
	BoundingBox bb3 = new BoundingBox(160.1, 150.1, 10, 90);
	BoundingBox bb4 = new BoundingBox(360.1, 150.1, 10, 90);
	BoundingBox bb5 = new BoundingBox(200.1, 350.1, 130, 10);
	*/
	
	//menu
	MenuBar menuBar = new MenuBar();
	
	//levelauswahl und verlassen des spiels
	Menu menuLevel = new Menu("Level");
	Menu menuExit = new Menu("Help");
	
	MenuItem exit = new MenuItem("Exit");
	
	RadioMenuItem l1 = new RadioMenuItem("Level 1");
	RadioMenuItem l2 = new RadioMenuItem("Level 2");
	/*
	RadioMenuItem l3 = new RadioMenuItem("Level 3");
	RadioMenuItem l4 = new RadioMenuItem("Level 4");
	RadioMenuItem l5 = new RadioMenuItem("Level 5");
	*/
	
	//diverser kram, der benoetigt wird
	ToggleGroup tg = new ToggleGroup();
	Group root = new Group();
	Scene newScene = new Scene(root);	
	Canvas canvas = new Canvas(500, 500);
	StackPane holder = new StackPane();
	
	public void start(Stage prime) throws Exception {
		
		//level zur toggle group hinzufuegen (so kann nur 1 gewaehlt werden)
		l1.setToggleGroup(tg);
		l2.setToggleGroup(tg);
		/*
		l3.setToggleGroup(tg);
		l4.setToggleGroup(tg);
		l5.setToggleGroup(tg);
		*/

		//event handling
		l1.setOnAction(e -> { levelAct = 1; root.getChildren().clear(); root.getChildren().addAll(holder, menuBar, obst1); });
		l2.setOnAction(e -> { levelAct = 2; root.getChildren().clear(); root.getChildren().addAll(holder, menuBar, obst1, obst2); });
		/*
		l3.setOnAction(e -> { levelAct = 3; root.getChildren().clear(); root.getChildren().addAll(holder, menuBar, obst1, obst2, obst3); });
		l4.setOnAction(e -> { levelAct = 4; root.getChildren().clear(); root.getChildren().addAll(holder, menuBar, obst1, obst2, obst3, obst4); });
		l5.setOnAction(e -> { levelAct = 5; root.getChildren().clear(); root.getChildren().addAll(holder, menuBar, obst1, obst2, obst3, obst4, obst5); });	
		*/
		
		//farben der hindernisse
		obst1.setFill(Color.LIGHTYELLOW);
		obst2.setFill(Color.LIGHTYELLOW);
		/*
		obst3.setFill(Color.LIGHTYELLOW);
		obst4.setFill(Color.LIGHTYELLOW);
		obst5.setFill(Color.LIGHTYELLOW);
		*/
		
		//hindernisse zur liste hinzufuegen
		obstacles.add(obst1);
		obstacles.add(obst2);
		/*
		obstacles.add(obst3);
		obstacles.add(obst4);
		obstacles.add(obst5);
		*/
		
		//level erstellen
		for(int i = 0; i < levels.length; i++) {
			levels[i] = new Level();
		}
		
		//hindernisse der level speichern
		for(int i = 0; i < levels.length; i++) {
			for(int j = 0; j <= i; j++) {
				levels[i].add(obstacles.get(j));
			}
		}

		holder.getChildren().add(canvas);
		holder.setStyle("-fx-background-color: black;"); 
		root.getChildren().addAll(holder, menuBar);	
	
		/**
		 * Die hier unten stehende verschachtelte schleife muss noch erkennen,
		 * wenn ein Level nicht fertig ist usw. 
		 * ist fuers zeichnen zustaendig
		 */	
		
		exit.setOnAction(e -> { Platform.exit(); });
		
		//menu aufbauen
		menuLevel.getItems().addAll(l1, l2);
		//menuLevel.getItems().addAll(l1, l2, l3, l4, l5);
		menuExit.getItems().add(exit);
		menuBar.getMenus().addAll(menuLevel, menuExit);
		
		/*
		 * eventhandler zum hinzufuegen und loeschen von gedrueckten tasten
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

		
		prime.setTitle("Game Test WIP"); 
		prime.setScene(newScene);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		//bounds der hindernisse in liste speichern
		bounds.add(bb1);
		bounds.add(bb2);
		/*
		bounds.add(bb3);
		bounds.add(bb4);
		bounds.add(bb5);
		*/
		
		new AnimationTimer() {
			
			int x = 300;
			int y = 350;


			public void handle(long currentNanoTime) {
				gc.clearRect(0, 0, 500, 500);
				
				//ueberprueft, dass ball das fenster nicht verlaesst
				//vier ifs, damit ball sich auch schraeg bewegen kann
				if(keysActive.contains("LEFT") && x > 0) {
					for(int i = 0; i < levelAct; i++) {
						if(!bounds.get(i).intersects(x, y, 20, 20))
							x--;
						else
							x++;
					}
				}
				if(keysActive.contains("RIGHT") && x < canvas.getWidth()-20) {
					for(int i = 0; i < levelAct; i++) {
						if(!bounds.get(i).intersects(x, y, 20, 20))
							x++;
						else
							x--;
					}
				}
				if(keysActive.contains("UP") && y > 0) {
					for(int i = 0; i < levelAct; i++) {
						if(!bounds.get(i).intersects(x, y, 20, 20))
							y--;
						else
							y++;
					}
				}
				if(keysActive.contains("DOWN") && y < canvas.getHeight()-20) {
					for(int i = 0; i < levelAct; i++) {
						if(!bounds.get(i).intersects(x, y, 20, 20))
							y++;
						else
							y--;
					}
				}
			
				//wenn der ball im ziel ist, beendet sich das spiel
				if((x > 250 && x < 260) && (y > 250 && y < 260)) {
					System.out.println("WON");
					Platform.exit();
				}	
				
				//farbe des bewegenden balls
				gc.setFill(Color.WHITE);
				
				//bewegbarer ball
				gc.fillOval(x, y, 20, 20);
				
				String xR = Math.round(x) + "";
				String yR = Math.round(y) + "";
				
				//koordinaten des balls 
				gc.fillText("x: " + xR, 400, 10);
				gc.fillText("y: " + yR, 400, 25);

				//ziel, in das ball soll
				gc.setStroke(Color.RED);
				gc.strokeOval(250, 250, 30, 30);
				

			}

		}.start();
		
		//groesse des fensters voerst nicht veraenderbar
		prime.setResizable(false);
		prime.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
