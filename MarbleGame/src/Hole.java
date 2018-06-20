import javafx.util.Pair;

public class Hole extends GameObjects{

	int r;
	
	/**
	 * Why the fuck would you create an empty hole lol. Pls set the coords later on as it will be on 0 0 0
	 */
	Hole(){
		this(0,0,0);
	}
	
	/**
	 * 
	 * @param x x coords
	 * @param y y coords
	 * @param r radius size in pixels
	 */
	Hole(int x, int y, int r){
		coords.add(new Pair<Integer,Integer>(x,y));
		this.r=r;
		
	}
	
	/**
	 * huh
	 * @return
	 */
	public int getRadius() {
		return r;
	}
	
	/** 
	 * really tough shit mate
	 * @param r
	 */
	public void setRadius(int r) {
		this.r=r;
	}
	
	
}
