import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LootGenerator {

    public static Monster pickMonster(ArrayList<Monster> ls) {
        int index = (int)(Math.random() * ls.size());
        return ls.get(index);
    }

    public static String fetchTreasureClass(Monster m) {
        return m.getTc();
    }

    public static String generateBaseItem(HashMap<String, ArrayList<String>> hm, String key) {
        if(hm.containsKey(key)) {
            int index = (int)(Math.random() * 3);
            String newKey = hm.get(key).get(index);
            return generateBaseItem(hm, newKey);
        } else {
            return key;
        }
    }

    public static int generateBaseStats(HashMap<String, ArrayList<Integer>> hm, String name) {
        ArrayList<Integer> ls = hm.get(name);
        int bs = (int)(Math.random() * (ls.get(1) - ls.get(0)) + ls.get(0));
        return bs;
    }

    public static Affix generateAffix(ArrayList<Affix> ls) {
        int index = (int)(Math.random() * ls.size());
        return ls.get(index);
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws FileNotFoundException {
        //Make files
        File monsFile = new File("data/large/monstats.txt");
        File tcFile = new File("data/large/TreasureClassEx.txt");
        File bsFile = new File("data/large/armor.txt");
        File prefixFile = new File("data/large/MagicPrefix.txt");
        File suffixFile = new File("data/large/MagicSuffix.txt");

        //parse files into necessary data structures
        ArrayList<Monster> monsterList = Monster.generateMonsterList(monsFile);
        HashMap<String, ArrayList<String>> tcHashMap = TreasureClass.generateTCHashMap(tcFile);
        HashMap<String, ArrayList<Integer>> bsHashMap = BaseStats.generateBSHashMap(bsFile);
        ArrayList<Affix> prefixes = Affix.generateAffixList(prefixFile);
        ArrayList<Affix> suffixes = Affix.generateAffixList(suffixFile);


        //start fighting!
        Scanner in = new Scanner(System.in);
        do{
            //generate monster, base item, base stats
            Monster m = pickMonster(monsterList);
            String baseItem = generateBaseItem(tcHashMap, fetchTreasureClass(m));
            int bs = generateBaseStats(bsHashMap, baseItem);
            
            //50% chance for prefix and suffix
            double prefixChance = Math.random();
            double suffixChance = Math.random();
            
            //start building strings
            String finalName = baseItem;
            String finalAdditionalStat1 = "";
            String finalAdditionalStat2 = "";
            
            //add stuff to base strings for prefix and suffix
            if(prefixChance > 0.5) {
                Affix prefix = generateAffix(prefixes);
                String prefixName = prefix.name;
                String additionalPrefixStat = prefix.mod1code;
                int prefixStatValue = (int) (Math.random() * (prefix.mod1max - prefix.mod1min) + prefix.mod1min);
                finalName = prefixName + " " + finalName;
                finalAdditionalStat1 = prefixStatValue + " " + additionalPrefixStat;
            }
            if(suffixChance > 0.5) {
                Affix suffix = generateAffix(suffixes);
                String suffixName = suffix.name;
                String additionalsuffixStat = suffix.mod1code;
                int suffixStatValue = (int) (Math.random() * (suffix.mod1max - suffix.mod1min) + suffix.mod1min);
                finalName += " " + suffixName;
                finalAdditionalStat2 = suffixStatValue + " " + additionalsuffixStat;
            }

            //print results
            System.out.println("Fighting " + m.mosnterClass);
            System.out.println("You have slain " + m.mosnterClass);
            System.out.println(m.mosnterClass + " dropped:\n");
            System.out.println(finalName);
            System.out.println("Defense: " + bs);
            if(!finalAdditionalStat1.equals("")){
                System.out.println(finalAdditionalStat1);
            }
            if(!finalAdditionalStat2.equals("")){
                System.out.println(finalAdditionalStat2);
            }
            System.out.println("Fight again [y/n]?");

        } while(in.nextLine().equalsIgnoreCase("y"));     
    }

}
