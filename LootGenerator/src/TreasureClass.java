import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TreasureClass {
    private String tc;
    private String item1;
    private String item2;
    private String item3;
    
    public TreasureClass(String tc, String item1, String item2, String item3) {
        this.tc = tc;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
    
    public String getTc() {
        return tc;
    }
    
    public ArrayList<String> getItems() {
        ArrayList<String> ls = new ArrayList<>();
        ls.add(item1);
        ls.add(item2);
        ls.add(item3);
        return ls;
    }
    
    public static TreasureClass parseEntry(Scanner in) {
        String entry = in.nextLine();
        String[] entries = entry.split("\\t");
        return new TreasureClass(entries[0], entries[1], entries[2], entries[3]);
    }
    
    public static HashMap<String, ArrayList<String>> generateTCHashMap(File file) throws FileNotFoundException {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            TreasureClass tc = parseEntry(in);
            hm.put(tc.getTc(), tc.getItems());
        }
        return hm;
    }
}
