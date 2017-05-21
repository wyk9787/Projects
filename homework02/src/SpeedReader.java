import java.io.IOException;
import java.awt.*;
import java.text.AttributedString;

public class SpeedReader {
    
    /**
     * Set up the Graphics
     * @param width width of the speedReader
     * @param height height of the speedReader
     * @param fontSize font size
     * @param wpm words per minute the speedReader suppose to display
     * @return Graphics
     */
    public static Graphics displayPanel(int width, int height, int fontSize) {
        DrawingPanel panel = new DrawingPanel(width, height);
        Graphics g = panel.getGraphics();
        Font f = new Font("Courier", Font.BOLD, fontSize);
        g.setFont(f);
        return g;
    }
    /**
     * Show each word on the speedReader
     * @param width width of the speedReader
     * @param height height of the speedReader
     * @param fontSize font size
     * @param interval interval between the display of each word
     * @throws IOException if the file cannot be found, it will throw out the IOException
     * @throws InterruptedException if thread.sleep goes run, it will throw out hte InterruptedException
     */
    public static void speedReading(int width, int height, int fontSize, int interval, WordGenerator text) 
            throws IOException, InterruptedException {
        Graphics g = displayPanel(width, height, fontSize);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        
        while (text.hasNext()) {
            String nextText = text.next();
            String firstPart = "";
            String middleWord = "";
            String secondPart = "";
            int len = nextText.length();
            int x, y;
            y = (height - metrics.getHeight()) / 2 + metrics.getAscent();
            if (len <= 1) {
                x = width / 2 - 1 * metrics.charWidth(nextText.charAt(0));
                firstPart = "";
                middleWord = nextText;
                secondPart = "";
            } else if (len >= 2 && len <= 5) {
                x = width / 2 - 2 * metrics.charWidth(nextText.charAt(0));
                firstPart = nextText.substring(0, 1);
                middleWord = nextText.substring(1, 2);
                secondPart = nextText.substring(2);
            } else if (len >= 6 && len <= 9) {
                x = width / 2 - 3 * metrics.charWidth(nextText.charAt(0));
                firstPart = nextText.substring(0, 2);
                middleWord = nextText.substring(2, 3);
                secondPart = nextText.substring(3);
            } else if (len >= 10 && len <= 13) {
                x = width / 2 - 4 * metrics.charWidth(nextText.charAt(0));
                firstPart = nextText.substring(0, 3);
                middleWord = nextText.substring(3, 4);
                secondPart = nextText.substring(4);
            } else {
                x = width / 2 - 5 * metrics.charWidth(nextText.charAt(0));
                firstPart = nextText.substring(0, 4);
                middleWord = nextText.substring(4, 5);
                secondPart = nextText.substring(5);
            }
            int firstIntervalWidth = metrics.stringWidth(firstPart);
            int secondIntervalWidth = metrics.stringWidth(firstPart + middleWord);
            g.clearRect(0, 0, width, height);
            g.setColor(Color.WHITE);
            g.drawString(firstPart, x, y);
            g.setColor(Color.RED);
            g.drawString(middleWord, x+firstIntervalWidth, y);
            g.setColor(Color.WHITE);
            g.drawString(secondPart, x+secondIntervalWidth, y);
            Thread.sleep(interval);
        }
    }
    /**
     * 
     * @param args
     * @throws IOException if the file cannot be found, it will throw out the IOException
     * @throws InterruptedException if thread.sleep goes run, it will throw out hte InterruptedException
     */
    public static void main (String[] args) throws IOException, InterruptedException {
        String filePath = args[0];
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);
        int fontSize = Integer.parseInt(args[3]);
        int wpm = Integer.parseInt(args[4]);
        int interval = (int) (1.0 / wpm * 60 * 1000);
        WordGenerator text = new WordGenerator(filePath);
        speedReading(width, height, fontSize, interval, text);
        System.out.println(text.getWordCount());
        System.out.println(text.getSentenceCount());
        
    }
    

}
