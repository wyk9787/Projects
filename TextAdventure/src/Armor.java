
public class Armor extends Item{

	private Person person;
	
	public Armor(String name, int value, Person person) {
		super(name, value);
		this.person = person;
	}

	@Override
	public void use(Room room) {
		System.out.println("You put " + getName() + " on.");
		person.health += value;
		System.out.println("Now your health is increased by " + value + " and your health now is " + person.health + ".");
	}

	
}
