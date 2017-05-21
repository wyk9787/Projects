import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
    
    private Scanner text;
    private int countWords;
    private int countSentences;
    
    /**
     * Constructor of the class WordGenerator
     * @param filename the path of the file to read from
     * @throws IOException if the file cannot be found, it will throw out the IOException
     */
    public WordGenerator(String filename) throws IOException {
        text = new Scanner(new File(filename));
        countWords = 0;
        countSentences = 0;
    }
    
    /**
     * 
     * @return if there is word left in the file
     */
    public boolean hasNext() {
        return text.hasNext();
    }
    /**
     * 
     * @return the next word and also update each count for words and sentences
     */
    public String next() {
        if (hasNext()) {
            countWords++;
            String temp = text.next();
            String punctuation = temp.substring(temp.length()-1);
            if(punctuation.equals(".") || punctuation.equals("!") || punctuation.equals("?")) {
                countSentences++;
            }
            return temp;
        } else {
            return "";
        }
    }
    
    /**
     * 
     * @return the count for words
     */
    public int getWordCount() {
        return countWords;
    }
    
    /**
     * 
     * @return the count for sentences
     */
    public int getSentenceCount() {
        return countSentences;
    }
}
