import java.util.ArrayList;

public class RoomMap {
	ArrayList<Room> roomList;
	
	public RoomMap(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}
	
	public Room getRoom(int roomNum) {
		return roomList.get(roomNum);
	}
}
