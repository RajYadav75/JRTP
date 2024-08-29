import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

public class Encoding {
    public static void main(String[] args) {
        String str = "abc@123";

        Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(str.getBytes());

        System.out.println(encodedString);

        Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encodedString);
        System.out.println(new String(decode));
    }
}
