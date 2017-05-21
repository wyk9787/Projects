
public class Key extends Item{

	public Key(String name, int value) {
		super(name, value);
	}

	//If the key is used in the correct room, the door opens, if not, it does not work.
	//If already opened, it says the door is already open.
	@Override
	public void use(Room room) {
		if(room.getRoomNum() == 1) {
			System.out.println("It doesn't seem to work here");
		} else if (room.getRoomNum() == 2) {
			boolean[] doorLock = room.getDoorLock();
			if(doorLock[1]) {
				System.out.println("The door is already open.");
			} else {
				System.out.println("It seems like some door opens");
				room.setDoorLock(1, true);
			}
		} else if (room.getRoomNum() == 3) {
			boolean[] doorLock = room.getDoorLock();
			if(doorLock[0]) {
				System.out.println("The door is already open.");
			} else {
				System.out.println("It seems like some door opens");
				room.setDoorLock(0, true);
			}
		}

	}

}
