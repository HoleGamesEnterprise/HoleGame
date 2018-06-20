import java.util.ArrayList;

import javafx.util.Pair;

public class GameObjects {


	
	ArrayList<Pair<Integer, Integer>> coords = new ArrayList<Pair<Integer,Integer>>();
	
	GameObjects(){
	}
	
	GameObjects(ArrayList<Pair<Integer,Integer>> insert){
		coords = insert;
	}
	
	public ArrayList<Pair<Integer, Integer>> getCoordsList(){
		return coords;
	}
	
	public void setCoordsList(ArrayList<Pair<Integer, Integer>> insert) {
		coords = insert;
	}
	
	public void addCoord(Pair<Integer,Integer> a) {
		coords.add(a);
	}
	
	public boolean removeCoord(Pair<Integer,Integer> a) {
		if (coords.contains(a)) {
			coords.remove(a);
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getCoordsLength() {
		return coords.size();
	}
}
