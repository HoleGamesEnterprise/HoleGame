import java.util.ArrayList;

public class Level {

	
	ArrayList<GameObjects> go = new ArrayList<GameObjects>();
	
	Level(){
		this(null);
	}
	
	Level(ArrayList<GameObjects> list){
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
	
	public GameObjects getObject(int index) {
		return go.get(index);
	}
	
	public void setGameObjects(ArrayList<GameObjects> a) {
		this.go=a;
	}
	
	public ArrayList<GameObjects> getGameObjectsList(){
		return go;
	}
}
