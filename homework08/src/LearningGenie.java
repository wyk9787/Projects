import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LearningGenie {
    
	public static void main(String[] args) throws IOException {
	    File file = new File("data.txt");
	    DecisionTree tree = new DecisionTree(file);
		
		boolean flag = true;
		System.out.println("I am the learning genie!");
		System.out.println("I can figure out whatever you are thinking of by asking questions.");
		System.out.println("I know " + tree.countObjects() + " things.");
		while(flag) {
			Scanner in = new Scanner(System.in);
			tree.guess(in);
			System.out.println("Do you want to continue?");
			String ans = in.next();
			boolean innerFlag = true;
			while(innerFlag){
				if(ans.equalsIgnoreCase("yes")) {
					flag = true;
					innerFlag = false;
				} else if (ans.equalsIgnoreCase("no")) {
					flag = false;
					innerFlag = false;
				} else {
					System.out.println("Please answer yes or no.");
					ans = in.next();
				}
			}
		}
		FileWriter out = new FileWriter(file);
		tree.write(out);
		out.close();
		
	}
}
