import java.util.ArrayList;

import javafx.util.Pair;

public class GameObjects {


	
	ArrayList<Pair<Integer, Integer>> coords;/* = new ArrayList<Pair<Integer,Integer>>();*/
	
	/**
	 * creates an gameobject, it already has an arraylist initliazed like dis= ArrayList<Pair<Integer,Integer>>()
	 */
	GameObjects(){
	}
	
	/**
	 * hand over an arraylist of coords if you want to lawl
	 * @param insert
	 */
	GameObjects(ArrayList<Pair<Integer,Integer>> insert){
		coords = insert;
	}
	
	/**
	 * returns the Coordslist to the caller. usefull if you dont wanna use my methods lul
	 * @return
	 */
	public ArrayList<Pair<Integer, Integer>> getCoordsList(){
		if (coords!=null) {
			return coords;
		}
		return null;
	}
	
	/**
	 * if you for some reason need to set the coords at a later date. Use this to replace the full coordslist
	 * @param insert
	 */
	public void setCoordsList(ArrayList<Pair<Integer, Integer>> insert) {
		coords = insert;
	}
	
	/** add a single coordinate to the object
	 * 
	 * @param a
	 */
	public void addCoord(Pair<Integer,Integer> a) {
		coords.add(a);
	}
	
	/**
	 * removes a single coordinate from the array
	 * @param a
	 * @return returns false if the coordinate doesnt exist in the array lmao
	 */
	public boolean removeCoord(Pair<Integer,Integer> a) {
		if (coords.contains(a)) {
			coords.remove(a);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * simple as a cake. 
	 * @return
	 */
	public int getCoordsLength() {
		return coords.size();
	}
}
