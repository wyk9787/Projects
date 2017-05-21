import java.util.ArrayList;

public class Creature {
	
	public String name;
	public int health;
	public int attack;
	public ArrayList<String> dialogueDataBase;
	
	public Creature(String name, int health, int attack) {
		this.name = name;
		this.health = health;
		this.attack = attack;
	}
	
	public void talk() {
		int rand = (int)(Math.random() * dialogueDataBase.size());
		System.out.println(dialogueDataBase.get(rand));
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
}
