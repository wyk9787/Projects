import java.security.NoSuchAlgorithmException;

public class BlockChain {
	public static class Node {
		public Block value;
		public Node next;
		
		public Node(Block value, Node next){
			this.next = next;
			this.value = value;
		}
	}
	
	private Node first;
	private Node last;
	
	public BlockChain(int initial) throws NoSuchAlgorithmException {
		if(initial < 0) {
			throw new IllegalArgumentException();
		}
		this.first = new Node(new Block(0, initial, null), null);
		this.last = first;
	}
	
	public Block mine(int amount) throws NoSuchAlgorithmException {
		return new Block(this.getSize(), amount, last.value.getHash());
	}
	
	public int getSize() {
		return this.last.value.getNum() + 1;
	}
	
	public void append(Block blk) {
		Node temp = new Node(blk, null);
		this.last.next = temp;
		this.last = this.last.next;
		if(!this.isValidBlockChain()) {
		    this.removeLast();
			throw new IllegalArgumentException();
		}
	}
	
	public boolean removeLast() {
		if(this.getSize() == 1) {
			return false;
		} else {
			Node temp = first;
			while(temp.next != last) {
				temp = temp.next;
			} 
			temp.next = null;
			last = temp;
			return true;
		}
	}
	
	public Hash getHash() {
		return last.value.getHash();
	}
		
	public boolean isValidBlockChain() {
		int aliceAmount = first.value.getAmount();
		int bobAmount = 0;
		Node temp = first.next;
		Node temp2 = first;
		while(temp != null) {
			if(temp2.value.getHash() != temp.value.getPrevHash()) { return false; }
			if(!temp.value.getHash().isValid()) { return false; }
			if(aliceAmount - temp.value.getAmount() < 0 ) { return false;}
			if(bobAmount + temp.value.getAmount() < 0 ) { return false;}
			if(temp2.value.getNum() + 1 != temp.value.getNum()) { return false; }
			aliceAmount -= temp.value.getAmount();
			bobAmount += temp.value.getAmount();
			temp = temp.next;
			temp2 = temp2.next;
		}
		return true;
	}
	
	public void printBalances() {
		int aliceAmount = first.value.getAmount(), bobAmount = 0;
		Node temp = first.next;
		while(temp != null) {
			aliceAmount -= temp.value.getAmount();
			bobAmount += temp.value.getAmount();
			temp = temp.next;
		}
		System.out.println("Alice: " + aliceAmount + ", Bob: " + bobAmount); 
	}
	
	public String toString() {
		String result = "";
		Node temp = first;
		for(int i = 0; i < this.getSize(); i++) {
			result += temp.value.toString() + "\n";
			temp = temp.next;
		}
		return result;
	}
	
}
