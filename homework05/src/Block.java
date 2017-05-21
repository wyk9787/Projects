import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private int num;
    private int data;
    private Hash prevHash;
    private long nonce;
    private Hash hash;



    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
        this.num = num;
        this.data = amount;
        this.prevHash = prevHash;

        Hash h = null;

        long tempNonce = -1;
        MessageDigest md = MessageDigest.getInstance("sha-256");
        do{
            tempNonce ++;
            md.update(ByteBuffer.allocate(4).putInt(this.num).array());
            md.update(ByteBuffer.allocate(4).putInt(this.data).array());
            if(this.prevHash != null) {
                md.update(this.prevHash.getData());
            }
            md.update(ByteBuffer.allocate(8).putLong(tempNonce).array());
            h = new Hash(md.digest()); 
        }while (!h.isValid());
        this.nonce = tempNonce;
        this.hash = h;
    }

    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException{
        this.num = num;
        this.data = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(ByteBuffer.allocate(4).putInt(this.num).array());
        md.update(ByteBuffer.allocate(4).putInt(this.data).array());
        if(this.prevHash != null) {
            md.update(this.prevHash.getData());
        }
        md.update(ByteBuffer.allocate(8).putLong(this.nonce).array());
        this.hash= new Hash(md.digest());
    }

    public int getNum() {
        return this.num;
    }

    public int getAmount() {
        return this.data;
    }

    public long getNonce() {
        return this.nonce;
    }

    public Hash getPrevHash() {
        return this.prevHash;
    }

    public Hash getHash() {
        return this.hash;
    }

    public String toString() {
        String result = "Block ";
        result = result + num + " (Amount: " + data + ", Nonce: " + nonce + 
                ", prevHash: " + prevHash + ", hash: " +  hash + ")";
        return result;
    }
}
