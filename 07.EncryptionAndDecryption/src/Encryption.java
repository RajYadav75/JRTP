import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "abc@123";

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(str.getBytes());
        byte[] encytpText = digest.digest();
        System.out.println(new String(encytpText));

        //�{3Z��\��8h/���,ŗ�9���-B�

        String encoded = Base64.getEncoder().encodeToString(encytpText);
        System.out.println(encoded);
        //5YV7M1r981yoGhELyB84aC+KiYksxZf1OY3++C1CtRM=


    }
}
