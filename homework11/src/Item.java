
public abstract class Item {
	
	public int value;
	private String name;
	
	public Item(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public abstract void use(Room room);
	
	public String getName() {
		return name;
	}
	
}
