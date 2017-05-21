import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TextAdventure {

	@SuppressWarnings("resource")
	public static Person introduction(Inventory inventory) {
		System.out.println("Welcome to the ultimate escape room!");
		System.out.println("Lists of commands are 'Command', 'Wait', 'Talk', 'Go', 'Pick up', 'Use', 'Attack', and 'Look'.");		
		System.out.println("What's your name?");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();

		return new Person(name, 100, 20, inventory);
	}

	public static HashMap<String, Creature> createCreatureList(int roomNum) {
		HashMap<String, Creature> hm = new HashMap<String, Creature>();
		Creature charlie = new NPC("Charlie", 500, 30);
		Creature titus = new NPC("Titus", 500, 30);
		Creature pm = new Monster("PM", 130, 30);
		Creature sam = new NPC("Sam", 5000, 10000);
		if(roomNum == 1) {
			hm.put("Charlie", charlie);
			hm.put("Titus", titus);
		} else if (roomNum == 4) {
			hm.put("PM", pm);
		} else if (roomNum == 5) {
			hm.put("Sam", sam);
		}

		return hm;
	}

	public static HashMap<String, Integer> createPosition(int north, int south, int east, int west) {
		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("north", north);
		hm.put("south", south);
		hm.put("east", east);
		hm.put("west", west);

		return hm;
	}

	public static HashMap<String, Item> createInventory(int roomNum, Person player) {
		HashMap<String, Item> hm = new HashMap<String, Item>();
		Item goldenKey = new Key("golden key", 200);
		Item silverKey = new Key("silver key", 100);
		Item goldenArmor = new Armor("golden armor", 30, player);
		Item silverArmor = new Armor("silver armor", 10, player);
		Item goldenWeapon = new Weapon("golden sword", 30, player);
		Item silverWeapon = new Weapon("silver sword", 10, player);

		if(roomNum == 1) {
			hm.put("silver sword", silverWeapon);
			hm.put("silver armor", silverArmor);
		} else if (roomNum == 2) {
			hm.put("silver key", silverKey);
			hm.put("golden sword", goldenWeapon);
		} else if (roomNum == 3) {
			hm.put("golden key", goldenKey);
			hm.put("golden armor", goldenArmor);
		} 

		return hm;
	}
	
	public static void updateDoorLock(Room fromRoom, Room toRoom) {
		int fromRoomNum = fromRoom.getRoomNum();
		int toRoomNum = toRoom.getRoomNum();
		if(fromRoomNum == 1 && toRoomNum == 2) {
			toRoom.setDoorLock(2, true);
		} else if (fromRoomNum == 2 && toRoomNum == 1) {
			toRoom.setDoorLock(0, true);
		} else if (fromRoomNum == 1 && toRoomNum == 3) {
			toRoom.setDoorLock(3, true);
		} else if (fromRoomNum == 3 && toRoomNum == 1) {
			toRoom.setDoorLock(1, true);
		} else if (fromRoomNum == 2 && toRoomNum == 4) {
			toRoom.setDoorLock(3,	true);
		} else if (fromRoomNum == 4 && toRoomNum == 2) {
			toRoom.setDoorLock(1, true);
		} else if (fromRoomNum == 3 && toRoomNum == 4) {
			toRoom.setDoorLock(2,	true);
		} else if (fromRoomNum == 4 && toRoomNum == 3) {
			toRoom.setDoorLock(0, true);
		} else if (fromRoomNum == 4 && toRoomNum == 5) {
			toRoom.setDoorLock(3,	true);
		} else if (fromRoomNum == 5 && toRoomNum == 4) {
			toRoom.setDoorLock(1, true);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		boolean gameStatus = true;
		while(gameStatus) { 
			//set up the environment
			Scanner in = new Scanner(System.in);
			Inventory backpack = new Inventory(new HashMap<String, Item>());
			Parser parser;
			Person player = introduction(backpack);

			Room curRoom;

			//Establish Rooms
			Room room1 = new Room(createCreatureList(1), createPosition(2, -1, 3, -1), 1, new Inventory(createInventory(1, player)));
			Room room2 = new Room(createCreatureList(2), createPosition(-1, 1, 4, -1), 2, new Inventory(createInventory(2, player)));
			Room room3 = new Room(createCreatureList(3), createPosition(4, -1, -1, 1), 3, new Inventory(createInventory(3, player)));
			Room room4 = new Room(createCreatureList(4), createPosition(-1, 3, 5, 2), 4, new Inventory(createInventory(4, player)));
			Room room5 = new Room(createCreatureList(5), createPosition(-1, -1, 3, 4), 5, new Inventory(createInventory(5, player)));
			ArrayList<Room> roomList = new ArrayList<>();
			roomList.add(room1);
			roomList.add(room2);
			roomList.add(room3);
			roomList.add(room4);
			roomList.add(room5);
			room1.createRoomMap(roomList);
			room2.createRoomMap(roomList);
			room3.createRoomMap(roomList);
			room4.createRoomMap(roomList);
			room5.createRoomMap(roomList);
			
			//Game starts
			curRoom = room1;
			System.out.println("Beep...");
			System.out.println("Beep...");
			System.out.println("Beep...");
			System.out.println("There are two fairly young Grinnell computer science professors in this room that you can talk to.");
			while(gameStatus) {
				curRoom.enterRoom(player);
				System.out.print("> ");
				String command = in.nextLine();
				parser = new Parser(command, curRoom, backpack);
				gameStatus = parser.parse();
				int nextRoomNum = curRoom.getNextRoom();
				if (nextRoomNum > 0) {
					curRoom.resetNextRoom();
					curRoom = roomList.get(nextRoomNum - 1);
					System.out.println("You just entered room " + nextRoomNum + ".");
					if ( nextRoomNum == 5) {
						System.out.println("There is a department chair in this room that you can talk to.");
					} else if (nextRoomNum == 1) {
						System.out.println("There are some Grinnell computer science professors in this room that you can talk to.");
					}
				}
			}
			
			System.out.println("Do you want to play again? (yes/no)");
			String ans = in.nextLine();
			if(ans.equalsIgnoreCase("yes")) {
				gameStatus = true;
			} else {
				gameStatus = false;
			}
		}
		System.out.println("Thanks for playing.");
	}
}
