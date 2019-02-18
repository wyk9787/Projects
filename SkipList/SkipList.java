import java.util.ArrayList;

// William Pugh: Skip Lists: A Probabilistic Alternative to Balanced Trees

/**
 * 
 * @author Garrett
 *
 * @param <K>
 * @param <V>
 */
public class SkipList<K extends Comparable<K>, V> {
	private SkipListNode<K, V> header;
	private SkipListNode<K, V> tail;
	private int maxLevel;
	private int level;
	private final double probablity =  0.5;
	
	/**
	 * Default Constructor
	 * Initialize the level to be 1
	 * Initialize the maxLevel to be 20
	 * Initialize the header
	 * Initialize the tail 
	 */
	public SkipList() {
		level = 1;
		maxLevel = 20;
		header = new SkipListNode<>(null, null, level);
		tail = new SkipListNode<>(null, null, level);
		tail.getForward().add(null);
		header.getForward().add(tail);
	}
	
	/**
	 * Returns the header of the list
	 * @return SkipListNode<K, V> 
	 */
	public SkipListNode<K, V> getHeader() {
		return header;
	}
	
	/**
	 * Insert the {key, value} pair into the SkipList
	 * @param key K 
	 * @param value V
	 */
	public void insert(K key, V value) {
		ArrayList<SkipListNode<K, V>> update = new ArrayList<>();
		for(int i = 0; i < maxLevel; i++) {
			update.add(null);
		}
		SkipListNode<K, V> temp = header;
		for(int i = level-1; i>= 0; i--) {
			while(temp.getForward().get(i).getKey() != null && temp.getForward().get(i).getKey().compareTo(key) < 0) {
				temp = temp.getForward().get(i);
			}
			update.set(i, temp);
		}
		temp = temp.getForward().get(0);
		if(temp.getKey() != null && temp.getKey().compareTo(key) == 0) {
			temp.setValue(value);
		} else {
			int newLevel = randomLevel();
			if(newLevel > this.level) {
				for(int i = this.level; i < newLevel; i++) {
					this.header.getForward().add(i, tail);
					update.set(i, this.header);
				}
				this.level = newLevel;
			}
			temp = new SkipListNode<K, V>(key, value, newLevel);
			for(int i = 0; i < newLevel; i++) {
				temp.getForward().add(i, update.get(i).getForward().get(i));
				update.get(i).getForward().set(i, temp);
			}
		}
	}
	
	/**
	 * Delete the key and its corresponding value in the SkipList
	 * @param key K
	 * @throws IllegalArgumentException if the key is not found in the SkipList
	 */
	public void delete(K key) {
		ArrayList<SkipListNode<K, V>> update = new ArrayList<>();
		for(int i = 0; i < maxLevel; i++) {
			update.add(null);
		}
		SkipListNode<K, V> temp = header;
		for(int i = level-1; i>= 0; i--) {
			while(temp.getForward().get(i).getKey() != null && temp.getForward().get(i).getKey().compareTo(key) < 0) {
				temp = temp.getForward().get(i);
			}
			update.set(i, temp);
		}
		temp = temp.getForward().get(0);
		if(temp.getKey() != null && temp.getKey().compareTo(key) == 0) {
			for(int i = 0; i < this.level; i++) {
				SkipListNode<K, V> cur = update.get(i).getForward().get(i); 
				if(cur.getKey() != temp.getKey() || cur.getValue() != temp.getValue()) {
					break;
				} else {
					update.get(i).getForward().set(i, temp.getForward().get(i));
				}
			}
			while(this.level >= 1 && this.header.getForward().get(this.level-1) == null) {
				this.level--;
			}
		} else {
			throw new IllegalArgumentException("The key " + key + " doesn't exist in the skip list");
		}
	}
	
	/**
	 * Return the corresponding value of the key in the SkipList
	 * @param key
	 * @return V 
	 * @throws IllegalArgumentException if the key is not found in the SkipList
	 */
	public V search(K key) {
		SkipListNode<K, V>temp = this.header;
		for(int i = this.level - 1; i >= 0; i--) {
			while(temp.getForward().get(i).getKey() != null && temp.getForward().get(i).getKey().compareTo(key) < 0) {
				temp = temp.getForward().get(i);
			}
		}
		temp = temp.getForward().get(0);
		if(temp.getKey() != null && temp.getKey().compareTo(key) == 0) {
			return temp.getValue();  
		} else {
			throw new IllegalArgumentException("The key " + key + " doesn't exist in the skip list");
		}
	}
	
	/**
	 * Return a random level base on probability
	 * @return int
	 */
	private int randomLevel() {
		int newLevel = 1;
		while (Math.random() < this.probablity) {
			newLevel++;
		}
		return Math.min(newLevel, this.maxLevel);
	}
	
	/**
	 * Print the current state of the SkipList
	 */
	public void printList() {
		SkipListNode<K, V> cur = this.header.getForward().get(0);
		for(int i = 0; i < this.level; i++) {
			System.out.print(" X ");
		}
		System.out.println();
		for(int i = 0; i < this.level; i++) {
			System.out.print(" | ");
		}
		System.out.println();
		while(cur.getForward().get(0) != null) {
			for(int i = 0; i < cur.level(); i++) {
				System.out.print(" " + cur.getKey() + " ");
			}
			for(int i = 0; i < this.level - cur.level(); i++) {
				System.out.print(" | ");
			}
			System.out.println();
			for(int i = 0; i < this.level; i++) {
				System.out.print(" | ");
			}
			System.out.println();
			cur = cur.getForward().get(0);
		}
	}
	
}
