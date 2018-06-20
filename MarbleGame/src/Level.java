import java.util.ArrayList;

import javafx.util.Pair;

public class Level {

	
	ArrayList<GameObjects> go = new ArrayList<GameObjects>();
	Pair<Integer,Integer> spawn;
	
	Level(){
		this(null,0,0);
	}
	
	Level(ArrayList<GameObjects> list, int x, int y){
		spawn = new Pair<Integer, Integer>(x,y);
		go=list;
	}
	
	public void add(GameObjects a) {
		go.add(a);
	}
	
	public GameObjects remove(GameObjects a) {
		GameObjects tmp = go.get(go.indexOf(a));
		go.remove(a);
		return tmp;
	}
	
	public GameObjects getGameObject(int index) {
		return go.get(index);
	}
	
	public void setGameObjectsList(ArrayList<GameObjects> a) {
		this.go=a;
	}
	
	public ArrayList<GameObjects> getGameObjectsList(){
		return go;
	}
	
	public int getGameObjectsAmmount() {
		return go.size();
	}
	
	public void setSpawnPoints(int x,int y) {
		spawn = new Pair<Integer, Integer>(x,y);
	}
	
	public void setSpawnPoints(Pair<Integer,Integer> values) {
		spawn = values;
	}
	
	public Pair<Integer,Integer> getSpawnPointsAsPair() {
		return spawn;
	}
	
	public int getSpawnX() {
		return spawn.getKey();
	}
	
	public int getSpawnY() {
		return spawn.getValue();
	}
}
