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
	
	public int getR() {
		return r;
	}
	
	public void setR(int r) {
		this.r=r;
	}
	
	
}
