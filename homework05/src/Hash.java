import java.util.Arrays;

public class Hash {
		
	private byte[] data;
	
	public Hash(byte[] data) {
		this.data = data;
	}
	
	public byte[] getData() {
		return this.data;
	}
	
	public boolean isValid() {
		for(int i = 0; i < 3; i++) {
			if(data[i] != 0x00) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String result = "";
		for(int i = 0; i < data.length; i++) {
			result += String.format("%02X", Byte.toUnsignedInt(data[i]));
		}
		return result;
	}
	
	public boolean equals(Object other) {
		if(other instanceof Hash) {
			Hash o = (Hash) other;
			return Arrays.equals(this.data, o.data);
		} else {
			return false;
		}
	}
}
