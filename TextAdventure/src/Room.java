import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Room {
	private int roomNumber;
	private HashMap<String, Creature> creatureList;
	private HashMap<String, Integer> position; 
	private boolean[] doorLock; 
	private Person person;
	private Inventory inventory;
	private int nextRoom;
	private RoomMap roomList;

	//setting up the room
	public Room(HashMap<String, Creature> creatureList, HashMap<String, Integer> position, int roomNumber, Inventory inventory) {
		this.creatureList = creatureList;
		this.position = position;
		this.roomNumber = roomNumber;
		this.inventory = inventory;
		doorLock = new boolean[4];
		for(int i = 0; i < 4; i ++) {
			doorLock[i] = false;
		}
		nextRoom = 0;
	}

	public void enterRoom(Person player) {
		person = player;
	}

	public void createRoomMap(ArrayList<Room> roomList) {
		this.roomList = new RoomMap(roomList);
	}

	public int getNextRoom() {
		return nextRoom;
	}

	public void resetNextRoom() {
		nextRoom = 0;
	}

	public void hold() throws InterruptedException {
		int chance = (int) (Math.random() * 100);
		System.out.println("Wait...one hour passed");
		Thread.sleep(1000);
		System.out.println("Wait...two hours passed");
		Thread.sleep(1000);
		System.out.println("Wait...three hours passed");
		Thread.sleep(1000);
		if(chance < 15) {
			System.out.println("You starved to death...");
		}
	}

	//the function returns boolean. If true, you are still alive, if false, you are dead and now the game is over.
	@SuppressWarnings("resource")
	public boolean go(String direction) {
		Scanner in = new Scanner(System.in);
		if(direction.equalsIgnoreCase("northeast")) {
			System.out.println("=====================================================================");
			System.out.println("= Congratulations!!! You successfully escaped from Noyce 3rd!!      =");
			System.out.println("= But now you don't have CS in your life so you are bored to death. =");
			System.out.println("=====================================================================");
			return false; 
		}
		int temp = position.get(direction);
		if(temp == -1) {
			System.out.println("You cannot go this way.");
		} else {
			if(roomNumber == 1) {
				if(temp == 2) {
					if(doorLock[0]) {
						nextRoom = temp;
					} else {
						System.out.println("Sorry this door requires password. What's the password?");
						String ans = in.nextLine();
						if(ans.equals("608")) {
							System.out.println("That's correct!");
							doorLock[0] = true;
							nextRoom = temp;
							roomList.getRoom(1).setDoorLock(2, true);
						} else {
							System.out.println("Sorry you are wrong. You can ask around and come back.");
						}
					}
				} else if (temp == 3) {
					if(doorLock[1]) {
						nextRoom = temp;
					} else {
						System.out.println("Sorry this door requires password. What's the password?");
						String ans = in.nextLine();
						if(ans.equals("653")) {
							System.out.println("That's correct!");
							doorLock[1] = true;
							nextRoom = temp;
							roomList.getRoom(2).setDoorLock(3, true);
						} else {
							System.out.println("Sorry you are wrong. You can ask around and come back.");
						}
					}
				} 
			} else if(roomNumber == 2) {
				if(temp == 1) {
					if(doorLock[2]) {
						nextRoom = temp;
					} else {
						System.out.println("Sorry the door is locked. You need to find a way to open the door.");
					}
				} else if (temp == 4) {
					if(doorLock[1]) {
						nextRoom = temp;
						TextAdventure.updateDoorLock(this, roomList.getRoom(3));
					} else {
						System.out.println("Sorry the door is locked. You need to find a way to open the door.");
					}
				} 
			} else if (roomNumber == 3) {
				if(temp == 4) {
					if(doorLock[0]) {
						nextRoom = temp;
						TextAdventure.updateDoorLock(this, roomList.getRoom(3));
					} else {
						System.out.println("Sorry the door is locked. You need to find a way to open the door.");
					}
				} else if (temp == 1) {
					if(doorLock[3]) {
						nextRoom = temp;
					} else {
						System.out.println("Sorry the door is locked. You need to find a way to open the door.");
					}
				} 
			} else if (roomNumber == 4) {
				if(temp == 2) {
					if(doorLock[3]) {
						nextRoom = temp;
						TextAdventure.updateDoorLock(this, roomList.getRoom(1));
					} else {
						System.out.println("Sorry the door is locked. You need to find a way to open the door.");
					}
				} else if (temp == 3) {
					if(doorLock[2]) {
						nextRoom = temp;
						TextAdventure.updateDoorLock(this, roomList.getRoom(2));
					} else {
						System.out.println("Sorry the door is locked. You need to find a way to open the door.");
					}
				} else if (temp == 5) {
					if(doorLock[1]) {
						nextRoom = temp;
					} else {
						System.out.println("Seems like there is a monster called PM standing in front of the door.");
						System.out.println("You have to kill him to enter the next room");
					}
				}
			}
		}
		return true;
	}

	public void talkTo (String name) {
		if(creatureList == null) {
			System.out.println("There is no one you can talk to in this room.");
		} else {
			if(name.equalsIgnoreCase("pm")) {
				Monster monster = (Monster) creatureList.get(name); 
				if(monster == null) {
					System.out.println(name + " is not in this room.");
				} else {
					monster.talk();
				}
			} else { 
				NPC npc = (NPC) creatureList.get(name);
				if(npc == null) {
					System.out.println(name + " is not in this room.");
				} else {
					npc.talk();
				}
			}
			
		}
	}

	public void pickUp(String name, Inventory backpack) {
		HashMap<String, Item> itemList = inventory.getItemList();
		Item item = itemList.get(name);
		if(item == null) {
			System.out.println(name + " is not in this room");
		} else {
			backpack.addItem(item);
			System.out.println(item.getName() + " is added to your backpack.");
		}
	}

	public void use(Room this, String name, Inventory backpack) {
		HashMap<String, Item> itemList = backpack.getItemList();
		Item item = itemList.get(name);
		if(item == null) {
			System.out.println(name + " is not in your backpack");
		} else {
			backpack.deleteItem(item);
			System.out.println(item.getName() + " is used");
			item.use(this);
		}
	}

	public boolean attack(String name) throws InterruptedException {
		boolean win = false;
		Creature creature = creatureList.get(name);
		if(creature == null) {
			System.out.println("This room doesn't have this creature");
			win = true;
		} else {
			win = person.attack(creature);
		}
		if(win) {
			System.out.println("Now you can go east and enter the next room");
			TextAdventure.updateDoorLock(this, roomList.getRoom(4));
			setDoorLock(1, true);
		} 
		return win;
	}

	public void lookAt(Inventory inventory) {
		if(inventory.isEmpty()) {
			System.out.println("Sorry you don't have anything in your backpack.");
		} else {
			inventory.printList();
		}
	}

	public int getRoomNum() {
		return roomNumber;
	}

	public void setDoorLock(int direction, boolean validity) {
		doorLock[direction] = validity;
	}

	public boolean[] getDoorLock() {
		return doorLock;
	}

}
