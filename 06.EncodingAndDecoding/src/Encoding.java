import java.util.Base64;
import java.util.Base64.Encoder;

public class Encoding {
    public static void main(String[] args) {
        String str = "abc@123";

        Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(str.getBytes());

        System.out.println(encodedString);
    }
}
