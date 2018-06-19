import java.util.ArrayList;

import javafx.util.Pair;

public class GameObjects {


	
	int x;
	int y;
	
	GameObjects(){
		this(0,0);
	}
	
	GameObjects(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
}
