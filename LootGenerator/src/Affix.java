import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Affix {
    public String name;
    public String mod1code;
    public int mod1min;
    public int mod1max;
    
    public Affix(String name, String mod1code, int mod1min, int mod1max) {
        this.name = name;
        this.mod1code = mod1code;
        this.mod1min = mod1min;
        this.mod1max = mod1max;
    }
    
    public static Affix parseEntry(Scanner in) {
        String entry = in.nextLine();
        String[] entries = entry.split("\\t");
        return new Affix(entries[0], entries[1], Integer.parseInt(entries[2]), Integer.parseInt(entries[3]));
    }
    
    public static ArrayList<Affix> generateAffixList(File file) throws FileNotFoundException {
        ArrayList<Affix> ls = new ArrayList<Affix>();
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            Affix affix = parseEntry(in);
            ls.add(affix);
        }
        return ls;
    }
}
