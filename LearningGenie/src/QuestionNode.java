import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionNode implements DecisionNode{

    private String value;
    public DecisionNode left;
    public DecisionNode right;

    public QuestionNode(String value, DecisionNode left, DecisionNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public int countObjects() {
        return left.countObjects() + right.countObjects();
    }

    @Override
    public DecisionNode guess(Scanner in) {
        System.out.println(value);
        String ans = in.next();
        while(true) {
            if(ans.equalsIgnoreCase("yes")) {
                left = left.guess(in);
            	return this;
            } else if (ans.equalsIgnoreCase("no")){
                right = right.guess(in);
            	return this;
            } else {
                System.out.println("Please answer yes or no.");
                ans = in.next();
            }
        }
    }

    @Override
    public void write(FileWriter out) throws IOException {
        out.write("#" + value + "\n");
    }

}
