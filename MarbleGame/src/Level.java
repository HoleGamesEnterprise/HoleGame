import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Level {

	//arraylist mit hindernissen
	public ArrayList<Rectangle> obstacles;

	//default constructor
	Level() {
		obstacles = new ArrayList<Rectangle>();
	}
	
	//nimmt beliebig viele rectangle objekte an und fuegt sie der liste hinzu
	Level(Rectangle... r) {
		obstacles = new ArrayList<Rectangle>();
		for(Rectangle rect: r)
			obstacles.add(rect);
	}
	
	//rectangle objekt zur liste hinzufuegen
	public void add(Rectangle r) {
		obstacles.add(r);
	}
	
	//rectangle objekt an bestimmter stelle der liste zurueckgeben
	public Rectangle getObstacles(int i) {
		return obstacles.get(i);
	}

}
