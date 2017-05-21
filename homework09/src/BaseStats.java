import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BaseStats {
    private String name;
    private int minac;
    private int maxac;
    
    public BaseStats(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Integer> getItems() {
        ArrayList<Integer> ls = new ArrayList<>();
        ls.add(minac);
        ls.add(maxac);
        return ls;
    }
    
    public static BaseStats parseEntry(Scanner in) {
        String entry = in.nextLine();
        String[] entries = entry.split("\\t");
        return new BaseStats(entries[0], Integer.parseInt(entries[1]), Integer.parseInt(entries[2]));
    }
    
    public static HashMap<String, ArrayList<Integer>> generateBSHashMap(File file) throws FileNotFoundException {
        HashMap<String, ArrayList<Integer>> hm = new HashMap<>();
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            BaseStats bs = parseEntry(in);
            hm.put(bs.getName(), bs.getItems());
        }
        return hm;
    }
}
