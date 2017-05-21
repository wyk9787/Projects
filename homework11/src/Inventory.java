import java.util.HashMap;

public class Inventory {
	private HashMap<String, Item> itemList;
	
	public Inventory(HashMap<String, Item> itemList) {
		this.itemList = itemList;
	}
	
	public HashMap<String, Item> getItemList() {
		return itemList;
	}
	
	public void addItem(Item item) {
		itemList.put(item.getName(), item);
	}
	
	public void deleteItem(Item item) {
		itemList.remove(item.getName());
	}
	
	public boolean isEmpty() {
		return itemList.size() <= 0;
	}
	
	public void printList() {
		System.out.print("You have ");
		for(String s : itemList.keySet()) {
			System.out.print(s + " ");
		}
		System.out.println("in your backpack.");
	}
}
