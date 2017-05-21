
public class Weapon extends Item{

	private Person person;
	
	public Weapon(String name, int value, Person person) {
		super(name, value);
		this.person = person;
	}
	
	@Override
	public void use(Room room) {
		System.out.println("You put " + getName() + " on.");
		person.attack += value;
		System.out.println("Now your attack is increased by " + value + " and your attack now is " + person.attack + ".");
	}

}
