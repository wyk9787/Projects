import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Grin {

	//Docode function with file parameters and using the methods created in HuffmanTree.java.
	public static void decode(String infile, String outfile) throws IOException {
		BitInputStream in = new BitInputStream(infile);
		BitOutputStream out = new BitOutputStream(outfile);
		int magicNumber = in.readBits(32);
		if(magicNumber != 1846) {
			throw new IllegalArgumentException();
		}
		HuffmanTree huffTree = new HuffmanTree(in);
		huffTree.decode(in, out);
		in.close();
		out.close();
	}

	//Encode function with file parameters and using the methods created in HuffmanTree.java
	public static void encode(String infile, String outfile) throws IOException {
		BitInputStream in = new BitInputStream(infile);
		BitOutputStream out = new BitOutputStream(outfile);
		out.writeBits(1846, 32);
		HuffmanTree huffTree = new HuffmanTree(createFrequencyMap(infile));
		huffTree.serialize(out);
		huffTree.encode(in, out);
		in.close();
		out.close();
	}

	//Create frequency Map from the String file with output of Map<Short, Integer>
	public static Map<Short, Integer> createFrequencyMap(String file) throws IOException {
		BitInputStream in = new BitInputStream(file);
		Map<Short, Integer> m = new HashMap<>();
		while(in.hasBits()) {
			Short key = (short)in.readBits(8);
			if(m.get(key) == null) {
				m.put(key, 1);
			} else {
				m.put(key, m.get(key)+1);
			}
		}
		return m;
	}

	public static void main(String[] args) throws IOException {
		if(args[0].equals("encode")) {
			encode(args[1], args[2]);
		} else if (args[0].equals("decode")) {
			decode(args[1], args[2]);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
