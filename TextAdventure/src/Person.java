import java.util.HashMap;

public class Person extends Creature{
	
	private Inventory inventory;

	public Person(String name, int health, int attack, Inventory inventory) {
		super(name, health, attack);
		inventory = new Inventory(new HashMap<String, Item>());
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public boolean attack(Creature creature) throws InterruptedException {
		System.out.println("The fight begins!");
		System.out.println("Your health is " + health + " and your attack is " + attack + ".");
		System.out.println(creature.name + "'s health is " + creature.health + " and his attack is " + creature.attack + ".");
		while(this.isAlive() && creature.isAlive()) {
			int attackFromPlayer = (int) (Math.random() * this.attack);
			int attackFromCreature = (int) (Math.random() * creature.attack);
			this.health -= attackFromCreature;
			creature.health -= attackFromPlayer;
			System.out.println(creature.getName() + " caused you " + attackFromCreature + " damage. Now your health is " + this.health);
			System.out.println("You caused " + creature.getName() + " " + attackFromPlayer + " damage. Now his health is " + creature.health);
			Thread.sleep(1500);
		}
		
		if(this.isAlive()) {
			System.out.println("Congratulations! You beat the " + creature.name + " !");
			return true;
		} else {
			System.out.println("Sorry you are dead.");
			return false;
		}
	}
}
