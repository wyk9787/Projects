import java.util.Scanner;

public class CaeserCipher {

    public static String prompt(){
        System.out.println("This program encrypts and decrypts messages using the Caeser Cipher.");
        System.out.print("Would you like to encode or decode a message? ");
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        if(response.equals("encode")){
            return "E";
        }else if (response.equals("decode")){
            return "D";
        }else{
            System.out.println("Valid options are \"encode\" or \"decode\"");
            return "F";
        }
    }

    public static String input(String s){
        if(s == "E"){
            System.out.print("Enter the string to encode: ");
        }else if (s == "D"){
            System.out.print("Enter the string to decode: ");
        }
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        
        return str;
    }

    public static String encode(String str, int n){
        char[] arr = str.toCharArray();
        for(int i = 0; i < str.length(); i++){
            arr[i] = (char) ((((int)arr[i] + n - (int)'a') % 26)+(int)'a');
        }
        String result = new String(arr);

        return result;
    }

    public static String decode(String str, int n){
        char[] arr = str.toCharArray();
        for(int i = 0; i < str.length(); i++){
            int temp = (int)arr[i] - n - (int)'a';
            if(temp >= 0){
                arr[i] = (char)(temp + (int)'a');
            }else{
                temp += 26 + (int)'a';
                arr[i] = (char)temp;
            }	
        }
        String result = new String(arr);

        return result;
    }

    public static void output(String str, String flag){
        if(flag.equals("E")){
            for(int i = 0; i < 26; i++){
                System.out.printf("n = %d: %s\n", i, encode(str, i));
            }
        }else if (flag.equals("D")){
            for(int i = 0; i < 26; i++){
                System.out.printf("n = %d: %s\n", i, decode(str, i));
            }
        }
    }


    public static void main(String[] args){
        String response = prompt();
        String str = " ";
        if(response == "F"){
            System.exit(0);
        }else{
            str = input(response);
        }
        output(str, response);
        
        return;
    }
}
