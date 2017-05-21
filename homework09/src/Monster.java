import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Monster {
    public String mosnterClass;
    public String type;
    public String level;
    public String tc;
    
    public Monster(String monsterClass, String type, String level, String tc) {
        this.mosnterClass = monsterClass;
        this.type = type;
        this.level = level;
        this.tc = tc;
    }
    
    public String getTc() {
        return this.tc;
    }
    
    public static Monster parseEntry(Scanner in) {
        String entry = in.nextLine();
        String[] entries = entry.split("\\t");
        return new Monster(entries[0], entries[1], entries[2], entries[3]);
    }
    
    public static ArrayList<Monster> generateMonsterList(File file) throws FileNotFoundException {
        ArrayList<Monster> ls = new ArrayList<Monster>();
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            ls.add(parseEntry(in));
        }
        return ls;
    }
}
