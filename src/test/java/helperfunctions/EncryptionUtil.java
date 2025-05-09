package helperfunctions;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
    private static final String KEY = System.getenv("SECRET_KEY");

    public static String encrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decoded = Base64.getDecoder().decode(encrypted);
        return new String(cipher.doFinal(decoded));
    }
}
