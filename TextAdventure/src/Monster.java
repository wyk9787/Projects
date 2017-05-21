import java.util.ArrayList;

public class Monster extends Creature{
	
	public Monster(String name, int value, int health) {
		super(name, value, health);
		super.dialogueDataBase = new ArrayList<>();
		dialogueDataBase.add("PM Monster: 'Hahahahah I am the god of computer science.'");
		dialogueDataBase.add("PM Monster: 'You will never be able to beat me!!'");
		dialogueDataBase.add("PM Monster: 'You will never get your homework back unless you beat me!!'");
		dialogueDataBase.add("PM Monster: 'You will at highest get a 55 on my exam!'");
	}
	
	@Override
	public void talk() {
		super.talk();
		int increment = (int) (Math.random() * 15) + 1;
		health += increment;
		attack += increment;
		System.out.println("> Because you talk to the lonely PM, his health and attack both increase by " + increment + ".");
	}
}
