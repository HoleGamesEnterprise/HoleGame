import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Level {

	public ArrayList<Rectangle> obstacles;

	Level() {
		obstacles = new ArrayList<Rectangle>();
	}
	
	Level(Rectangle... r) {
		for(Rectangle rect: r)
			obstacles.add(rect);
	}

	public void add(Rectangle r) {
		obstacles.add(r);
	}
	
	public Rectangle getObstacles(int i) {
		return obstacles.get(i);
	}

}
