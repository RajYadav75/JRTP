import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "abc@123";

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(str.getBytes());
        byte[] encytpText = digest.digest();
        System.out.println(new String(encytpText));

        //�{3Z��\��8h/���,ŗ�9���-B�


    }
}
