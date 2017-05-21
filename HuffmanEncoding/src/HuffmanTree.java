import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

	//A single node that can implement both node and a leaf
	private class Node implements Comparable<Node>{
		public short data;
		public int frequency;
		public Node left;
		public Node right;

		public Node(short data, int frequency, Node left, Node right) {
			this.data = data;
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}

		//Override method compareTo to be used in a priority queue
		@Override
		public int compareTo(Node o) {
			if (o.frequency < this.frequency) {
				return 1;
			} else if (o.frequency == this.frequency) {
				return 0;
			} else {
				return -1;
			}
		}
	}
	//Declaration of a Node root
	private Node root;

	//construct a HuffmanTree from the given frequency map of 9-bit values.
	public HuffmanTree(Map<Short, Integer> m) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		//For every Short s of the m.keyset, add our elements to the Priority queue q
		for(short s : m.keySet()) {
			q.add(new Node(s, m.get(s), null, null));
		}
		q.add(new Node((short) 256, 1, null, null));
		//Keep adding the nodes to the priority queue
		while(q.size() >= 2) {
			Node first = q.poll();
			Node second = q.poll();
			q.add(new Node((short)-1, first.frequency+second.frequency, first, second));
		}
		root = q.poll();
	}
	
	//Helper method for HuffmanTree(BitInputStream in). 
	//Constructs a HuffmanTree from the given file encoded in a serialized format.
	private Node add(BitInputStream in, Node cur) {
		int temp = in.readBit();
		//Return a leaf of short bit 9 upon reading 0, else return a node.
		if(temp == 0) {
			short tempData = (short)in.readBits(9);
			return new Node(tempData, 0, null, null);
		} else {
			return new Node((short)-1, 0, add(in, cur), add(in, cur));
		}
	}

	public HuffmanTree(BitInputStream in) {
		root = add(in, root);
	}

	//Helper method for void serialize(BitOutputStream out). 
	private void write(BitOutputStream out, Node cur) {
		if(cur.data == (short)-1) {
			out.writeBit(1);
			write(out, cur.left);
			write(out, cur.right);
		} else {
			out.writeBit(0);
			out.writeBits((int)cur.data, 9);
		}
	}

	public void serialize(BitOutputStream out) {
		write(out,root);
	}

	//Decodes a stream of huffman codes from a file given as a stream of bits into their uncompressed form,
	//saving the results to the given output stream.
	public void decode(BitInputStream in, BitOutputStream out) {
		Node cur = root;
		while(cur.data != (short)256) {
			while(cur.data == (short)-1) {
				int tempBit = in.readBit();
				if(tempBit == 1) {
					cur = cur.right;
				} else if (tempBit == 0) {
					cur = cur.left;
				}
			}
			if(cur.data != (short)256) {
				out.writeBits((int)cur.data, 8);
				cur = root;
			}
		}
	}

	//Helper for CreateHuffmanCodeMap. The first parameter String str will be used later to create Huffman Code.
	private void createHuffmanCodeMap(String str, Node cur, HashMap<Short, String> hm) {
		if(cur.data != (short)-1) {
			hm.put(cur.data, str);
		} else {
			createHuffmanCodeMap(str+"0", cur.left, hm);
			createHuffmanCodeMap(str+"1", cur.right, hm);
		}
	}

	//HuffmanCodeMap that will start with "", root, and hm.
	private HashMap<Short, String> createHuffmanCodeMap() {
		HashMap<Short, String> hm = new HashMap<>();
		createHuffmanCodeMap("", root, hm);
		return hm;
	}

	//Encodes the file given as a stream of bits into a compressed format using this Huffman tree.
	//The encoded values are written, bit-by-bit to the given BitOuputStream.
	public void encode(BitInputStream in, BitOutputStream out) {
		HashMap<Short, String> hm = createHuffmanCodeMap();
		while(in.hasBits()) {
			Short temp = (short)in.readBits(8);
			String code = hm.get(temp);
			for(int i = 0; i < code.length(); i++) {
				int tempInt = Integer.parseInt(code.substring(i, i+1));
				out.writeBit(tempInt);
			}
		}
		String eof = hm.get((short)256);
		for(int i = 0; i < eof.length(); i++) {
			int tempInt = Integer.parseInt(eof.substring(i, i+1));
			out.writeBit(tempInt);
		}
	}

}
