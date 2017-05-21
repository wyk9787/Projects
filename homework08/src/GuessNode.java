import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode{

    private String value;

    public GuessNode(String value) {
        this.value = value;
    }

    @Override
    public int countObjects() {
        return 1;
    }

    @Override
    public DecisionNode guess(Scanner in) {
        System.out.println("Are you thinking of " + value + "?");
        String ans = in.nextLine();
        while(true) {
            if(ans.equalsIgnoreCase("yes")) {
                System.out.println("Excellent, thanks!");
                return this;
            } else if(ans.equalsIgnoreCase("no")){
                System.out.println("Oh no, I was wrong!");
                System.out.println("What animal are you thinking of?");
                String guess = in.nextLine();
                System.out.println("What is a yes/no question that distinguishes a " + value + " from a " + guess + "?");
                System.out.println("(Yes corresponds to " + value + "; No corresponds to " + guess + ")");
                String question = in.nextLine();
                while(question.startsWith("#")) {
                	System.out.println("Sorry. Your question cannot start with '#'. Please try again.");
                	question = in.nextLine();
                }
                System.out.println("Thanks! I'll learn from this experience!"); 
                return new QuestionNode(question, new GuessNode(value), new GuessNode(guess));
            } else {
                System.out.println("Please answer yes or no.");
                ans = in.nextLine();
            }
        }
    }

    @Override
    public void write(FileWriter out) throws IOException {
        out.write(value + "\n");
    }

}
