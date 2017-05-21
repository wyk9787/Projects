import java.util.ArrayList;

public class NPC extends Creature{

	public NPC(String name, int value, int health) {
		super(name, value, health);
		dialogueDataBase = new ArrayList<>();
		if(name.equalsIgnoreCase("charlie")) {
			dialogueDataBase.add("Charlie: 'Seems like there is a door nearby.'");
			dialogueDataBase.add("Charlie: 'Password? What password?'");
			dialogueDataBase.add("Charlie: 'I actually have some clue about the password if you think I am telling the truth...'");
			dialogueDataBase.add("Charlie: 'Password is the sum of the course number of the courses I am teaching this semester.'");	
			dialogueDataBase.add("Charlie: 'Password is the sum of the course number of the courses I am teaching this semester.'");
			dialogueDataBase.add("Charlie: 'Password is the sum of the course number of the courses I am teaching this semester.'");
			dialogueDataBase.add("Charlie: 'Password is the sum of the course number of the courses I am teaching this semester.'");
			dialogueDataBase.add("Charlie: 'I am a music professor at Grin College and am teaching MUS-001 and MUS-999 this semester.'");
			dialogueDataBase.add("Charlie: 'There are golden and silver objects you can pick up in some rooms.'");
			dialogueDataBase.add("Charlie: 'There are golden and silver objects you can pick up in some rooms.'");
			dialogueDataBase.add("Charlie: 'There are golden and silver objects you can pick up in some rooms.'");
			dialogueDataBase.add("Charlie: 'There are golden and silver objects you can pick up in some rooms.'");
			dialogueDataBase.add("Charlie: 'Do you think you might need weapons and armors to beat a monster ahead?'");
			dialogueDataBase.add("Charlie: 'Do you think you might need weapons and armors to beat a monster ahead?'");
			dialogueDataBase.add("Charlie: 'Do you think you might need weapons and armors to beat a monster ahead?'");
		} else if (name.equalsIgnoreCase("titus")) {
			dialogueDataBase.add("Titus: 'Seems like there is a door nearby.");
			dialogueDataBase.add("Titus: 'Password? What password?");
			dialogueDataBase.add("Titus: 'I actually have some clue about the password if you think I am telling the truth...'");
			dialogueDataBase.add("Titus: 'Password is the sum of the course number of the courses I am teaching this semester.'");	
			dialogueDataBase.add("Titus: 'Password is the sum of the course number of the courses I am teaching this semester.'");
			dialogueDataBase.add("Titus: 'Password is the sum of the course number of the courses I am teaching this semester.'");
			dialogueDataBase.add("Titus: 'Password is the sum of the course number of the courses I am teaching this semester.'");
			dialogueDataBase.add("Titus: 'I am a art professor at Nell College and am teaching ART-001 and ART-999 this semester.'");
			dialogueDataBase.add("Titus: 'I bet you need keys to open some doors.'");
			dialogueDataBase.add("Titus: 'I bet you need keys to open some doors.'");
			dialogueDataBase.add("Titus: 'I bet you need keys to open some doors.'");
			dialogueDataBase.add("Titus: 'I bet you need keys to open some doors.'");			
			dialogueDataBase.add("Titus: 'Do you think you might need weapons and armors to beat a monster ahead?'");
			dialogueDataBase.add("Titus: 'Do you think you might need weapons and armors to beat a monster ahead?'");
			dialogueDataBase.add("Titus: 'Do you think you might need weapons and armors to beat a monster ahead?'");			
		} else if (name.equalsIgnoreCase("sam")) {
			dialogueDataBase.add("Sam: 'Seems like there is a door nearby.'");
			dialogueDataBase.add("Sam: 'Password? What password?'");
			dialogueDataBase.add("Sam: 'I actually have some clue about the password if you think I am telling the truth...'");
			dialogueDataBase.add("Sam: 'This door doesn't really have a key but it's hard to find.'");
			dialogueDataBase.add("Sam: 'This door doesn't really have a key but it's hard to find.'");
			dialogueDataBase.add("Sam: 'This door doesn't really have a key but it's hard to find.'");
			dialogueDataBase.add("Sam: 'Sometimes if you rotate 45 degress and see, the world is much better.'");
			dialogueDataBase.add("Sam: 'Sometimes if you rotate 45 degress and see, the world is much better.'");
		}
	}

	public void talk() {
		super.talk();
	}

}
