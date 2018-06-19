import java.util.ArrayList;

import javafx.util.Pair;

public class GameObjects {


	
	ArrayList<Pair<Integer, Integer>> coords = new ArrayList<Pair<Integer,Integer>>();
	
	GameObjects(){
		this(null);
	}
	
	GameObjects(ArrayList<Pair<Integer,Integer>> insert){
		coords = insert;
	}
	
	public ArrayList<Pair<Integer, Integer>> getCoordsArray(){
		return coords;
	}
	
	public void setCoords(ArrayList<Pair<Integer, Integer>> insert) {
		coords = insert;
	}
	
	public void addCoord(Pair<Integer,Integer> a) {
		coords.add(a);
	}
}
