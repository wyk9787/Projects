import java.util.Scanner;

public class VigenereCipher {

    public static String prompt(){
        System.out.println("This program encrypts and decrypts messages using the Vigenere Cipher.");
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

    public static String inputString(String s){
        if(s == "E"){
            System.out.print("Enter the string to encode: ");
        }else if (s == "D"){
            System.out.print("Enter the string to decode: ");
        }
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        return str;
    }

    public static String inputKey(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the key: ");
        String key = in.nextLine();
        
        return key;
    }

    public static String encode(String str, String key){
        char[] arr = str.toCharArray();
        int j = 0;
        int len = key.length();
        for(int i = 0; i < str.length(); i++){
            int n = (int)key.charAt(j) - (int)'a';
            arr[i] = (char) ((((int)arr[i] + n - (int)'a') % 26)+(int)'a');
            j = (j + 1) % len;
        }
        String result = new String(arr);

        return result;
    }

    public static String decode(String str, String key){
        char[] arr = str.toCharArray();
        int j = 0;
        int len = key.length();
        for(int i = 0; i < str.length(); i++){
            int n = (int)key.charAt(j) - (int)'a';
            int temp = (int)arr[i] - n - (int)'a';
            if(temp >= 0){
                arr[i] = (char)(temp + (int)'a');
            }else{
                temp += 26 + (int)'a';
                arr[i] = (char)temp;
            }
            j = (j + 1) % len;
        }
        String result = new String(arr);

        return result;
    }

    public static void output(String str, String key, String flag){
        if(flag.equals("E")){
            System.out.println(encode(str,key));
        }else if (flag.equals("D")){
            System.out.println(decode(str,key));
        }
    }


    public static void main(String[] args){
        String response = prompt();
        String str = " ";
        String key = " ";
        if(response == "F"){
            System.exit(0);
        }else{
            str = inputString(response);
            key = inputKey();
        }
        output(str, key, response);

        return;
    }
}
