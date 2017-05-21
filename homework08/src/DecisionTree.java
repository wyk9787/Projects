import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecisionTree {

	private DecisionNode root;

	public DecisionTree() {
		root = new GuessNode("Dog");
	}

	public DecisionTree(File file) throws IOException {
		Scanner in = new Scanner(file);
		root = add(in, root);
	}

	private static DecisionNode add(Scanner in, DecisionNode cur) {
		if(in.hasNext()){
			String temp = in.nextLine();
			if(!temp.startsWith("#")) {
				return new GuessNode(temp);
			} else {
				return new QuestionNode(temp.substring(1), add(in, cur), add(in, cur));
			}
		} else {
			return cur;
		}
	}


	public int countObjects(){
		return root.countObjects();
	}

	public void guess(Scanner in){
		System.out.println("Think of an object!");
		root = root.guess(in);
	}

	public void write(FileWriter out)throws IOException {
		write(root, out);
	}

	private static void write(DecisionNode cur, FileWriter out) throws IOException {
		if(cur instanceof QuestionNode) {
			cur.write(out);
			write(((QuestionNode) cur).left, out);
			write(((QuestionNode) cur).right, out);
		} else {
			cur.write(out);
		}
	}
}
