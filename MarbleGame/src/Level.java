import java.util.ArrayList;

import javafx.util.Pair;

public class Level {

	
	ArrayList<GameObjects> go = new ArrayList<GameObjects>();
	Pair<Integer,Integer> spawn;
	
	/**
	 * Use this to create an empty level, an arraylist has to initilized tho
	 */
	Level(){
		this(null,0,0);
	}
	
	/**
	 * Hand over an arraylist with gameobjects (its not important if the gameobjects have coords or not and a spawn point
	 * @param list
	 * @param x
	 * @param y
	 */
	Level(ArrayList<GameObjects> list, int x, int y){
		spawn = new Pair<Integer, Integer>(x,y);
		go=list;
	}
	
	/**
	 * adds a single gameobject at the end of the array
	 * @param a
	 */
	public void add(GameObjects a) {
		go.add(a);
	}
	
	/**
	 * removes an gameobject and also returns it if the user wants the return value
	 * @param a
	 * @return
	 */
	public GameObjects remove(GameObjects a) {
		GameObjects tmp = go.get(go.indexOf(a));
		go.remove(a);
		return tmp;
	}
	
	/**
	 * returns an gameobject without removing it
	 * @param index
	 * @return
	 */
	public GameObjects getGameObject(int index) {
		return go.get(index);
	}
	
	/**
	 * use this if you have an complete arraylist of gameobjects and replace it with the current one.
	 * Again, its not neccesary that the gameobjects are completed
	 * @param a
	 */
	public void setGameObjectsList(ArrayList<GameObjects> a) {
		this.go=a;
	}
	
	/**
	 * returns the gameobjectsArray
	 * @return
	 */
	public ArrayList<GameObjects> getGameObjectsList(){
		return go;
	}
	
	/**
	 * pretty self explaining if you ask me.
	 * @return
	 */
	public int getGameObjectsAmmount() {
		return go.size();
	}
	
	/**
	 * accepts two ints as input
	 * @param x
	 * @param y
	 */
	public void setSpawnPoints(int x,int y) {
		spawn = new Pair<Integer, Integer>(x,y);
	}
	
	/**
	 * accepts a pair with two ints to set the spawnpoints
	 * @param values
	 */
	public void setSpawnPoints(Pair<Integer,Integer> values) {
		spawn = values;
	}
	
	/**
	 * returns the spawnpoints as a pair<Integer, Integer>
	 * @return
	 */
	public Pair<Integer,Integer> getSpawnPointsAsPair() {
		return spawn;
	}
	
	/**
	 * if you for some reason need the spawn x
	 * @return
	 */
	public int getSpawnX() {
		return spawn.getKey();
	}
	
	/**
	 * if you need spawn y
	 * @return
	 */
	public int getSpawnY() {
		return spawn.getValue();
	}
}
