import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

	private ArrayList<String> command;
	private Room room;
	private Inventory inventory;
	private boolean status;
 
	public Parser(String command, Room room, Inventory inventory) {
		command = command.toLowerCase();
		this.room = room;
		this.status = true;
		this.inventory = inventory;
		this.command = new ArrayList<>(Arrays.asList(command.split(" ")));
	}

	//parsing various commands
	public boolean parse() throws InterruptedException {
		boolean status = true;

		if(command.contains("command")) {
			System.out.println("\nwait\n" + "talk\n" + "go\n" + "pick up\n" + "use\n" + "attack\n" + "look at\n");
		}
		else if(command.contains("go")) {
			String direction = null;
			if(command.contains("north")) {
				direction = "north";
			} else if (command.contains("south")) {
				direction = "south";
			} else if (command.contains("east")) {
				direction = "east";
			} else if (command.contains("west")) {
				direction = "west";
			} else {
				System.out.println("Please specify where you want to go.");
			}
			if (command.contains("north") && command.contains("east")) {
				direction = "northeast";
			}
			if(direction != null) {
				status = room.go(direction);
			}
		} else if (command.contains("wait")) {
			room.hold();
		} else if (command.contains("talk")) {
			if(command.contains("charlie")) {
				room.talkTo("Charlie");
			} else if (command.contains("pm")) {
				room.talkTo("PM");
			} else if (command.contains("sam")) {
				room.talkTo("Sam");
			} else if (command.contains("titus")) {
				room.talkTo("Titus");
			} else {
				System.out.println("Please specify who you want to talk to.");
			}
		} else if (command.contains("pick") || command.contains("pickup")) {
			if(command.contains("key")) {
				if(command.contains("silver")) {
					room.pickUp("silver key", inventory);
				} else if (command.contains("golden")) {
					room.pickUp("golden key", inventory);
				} 
			} else if(command.contains("armor")) {
				if(command.contains("silver")) {
					room.pickUp("silver armor", inventory);
				} else if (command.contains("golden")) {
					room.pickUp("golden armor", inventory);
				} else {
					System.out.println("please specify which armor you want to use.");
				}
			}
			else if(command.contains("sword")) {
				if(command.contains("silver")) {
					room.pickUp("silver sword", inventory);
				} else if (command.contains("golden")) {
					room.pickUp("golden sword", inventory);
				} else {
					System.out.println("please specify which sword you want to use.");
				}
			} else {
				System.out.println("Please specify what do you want to pick up.");
			}
		} else if (command.contains("use")) {
			if(command.contains("key")) {
				if(command.contains("silver")) {
					room.use("silver key", inventory);
				} else if (command.contains("golden")) {
					room.use("golden key", inventory);
				} else {
					System.out.println("please specify which key you want to use.");
				}
			} else if (command.contains("armor")) {
				if(command.contains("silver")) {
					room.use("silver armor", inventory);
				} else if (command.contains("golden")) {
					room.use("golden armor", inventory);
				} else {
					System.out.println("please specify which armor you want to use.");
				}
			} else if (command.contains("sword")) {
				if(command.contains("silver")) {
					room.use("silver sword", inventory);
				} else if (command.contains("golden")) {
					room.use("golden sword", inventory);
				} else {
					System.out.println("please specify which sword you want to use.");
				}
			} else {
				System.out.println("Please specify what do you want to use.");
			}
		} else if (command.contains("attack")) {
			if(command.contains("charlie")) {
				status = room.attack("Charlie");
			} else if (command.contains("pm")) {
				status = room.attack("PM");
			} else if (command.contains("sam")) {
				status = room.attack("Sam");
			} else if (command.contains("titus")) {
				status = room.attack("Titus");
			} else {
				System.out.println("Please specify who you want to attack.");
			}
		} else if (command.contains("check") || command.contains("look")) {
			room.lookAt(inventory);
		} else {
			System.out.println("Nothing happened");
		}
		return status;
	}




}
