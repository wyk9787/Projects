import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BlockChainDriver {

    public static void main(String[] args) throws NumberFormatException, NoSuchAlgorithmException {
        BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
        System.out.print(chain.toString());
        Scanner in = new Scanner(System.in);
        System.out.println("Command?");
        String response = in.nextLine();
        while(!response.equals("quit")) {
            switch (response) {
            case "mine": System.out.println("Amount transferred?");
                         String amount1 = in.nextLine();
                         long nonce = chain.mine(Integer.parseInt(amount1)).getNonce();
                         System.out.println("amount = " + Integer.parseInt(amount1) 
                         + ", nonce = " + nonce);
                         System.out.println();
                         break;
            case "append" : System.out.println("Amount transferred?");
                            String amount2 = in.nextLine();
                            System.out.println("Nonce?");
                            String nonce2 = in.nextLine();
                            chain.append(new Block(chain.getSize(), Integer.parseInt(amount2), chain.getHash(), 
                                    Long.parseLong(nonce2)));
                            System.out.println();
                            break;
            case "remove" : chain.removeLast();
                            if(chain.isValidBlockChain()) {
                                System.out.println("Remove succeeds!");
                            } else {
                                System.out.println("Remove fails!");
                            }
                            System.out.println();
                            break;

            case "check" : if (chain.isValidBlockChain()) {
                               System.out.println("Yes, Chain is valid");
                           } else {
                               System.out.println("No, Chain is not valid");
                           }
                           System.out.println();
                           break;

            case "report" : chain.printBalances();
                            System.out.println();
                            break;

            case "help" : System.out.println("Valid commands:");
                          System.out.println("mine: discovers the nonce for a given transaction");
                          System.out.println("append: appends a new block onto the end of the chain");
                          System.out.println("remove: removes the last block from the end of the chain");
                          System.out.println("check: checks that the block chain is valid");
                          System.out.println("report: reports the balances of Alice and Bob");
                          System.out.println("help: prints this list of commands");
                          System.out.println("quit: quits the program");
                          System.out.println();
                          break;
            default: System.out.println("Invalid input. Please try again.");
            }
            System.out.print(chain.toString());
            System.out.println("Command?");
            response = in.nextLine();

        }
        in.close();
    }

}
