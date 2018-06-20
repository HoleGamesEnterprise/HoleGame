import javafx.util.Pair;

public class Hole extends GameObjects{

	int r;
	
	Hole(){
		this(0,0,0);
	}
	
	Hole(int x, int y, int r){
		coords.add(new Pair<Integer,Integer>(x,y));
		this.r=r;
		
	}
	
	public int getRadius() {
		return r;
	}
	
	public void setRadius(int r) {
		this.r=r;
	}
	
	
}
