import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Garrett
 *
 * @param <K>
 * @param <V>
 */
public class SkipListNode<K, V> {
		private K key;
		private V value; 
		private List<SkipListNode<K, V>> forward;
		
		/**
		 * Constructor
		 * Create a new SkipListNode with key, value and level
		 * @param key K
		 * @param value V
		 * @param level int
		 */
		public SkipListNode(K key, V value, int level) {
			this.key = key;
			this.value = value;
			forward = new ArrayList<SkipListNode<K, V>>(level);
		}
		
		/**
		 * Return the key of this node 
		 * @return K
		 */
		public K getKey() {
			return key;
		}
		
		/**
		 * Return the value of this node
		 * @return V
		 */
		public V getValue() {
			return value;
		}
		
		/**
		 * Set the value of this node as value
		 * @param value V
		 */
		public void setValue(V value) {
			this.value = value;
		}
		
		/**
		 * Return the forwar pointers of this node
		 * @return ArrayList<SkipListNode<K, V>>
		 */
		public ArrayList<SkipListNode<K, V>> getForward() {
			return (ArrayList<SkipListNode<K, V>>) forward;
		}
		
		/**
		 * Return the level of this node
		 * @return int
		 */
		public int level(){
			return forward.size();
		}
	}