package enh.userutil;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Decrypter {
    private static final String ALGORITHM = "DES";
    private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";
     private  static final String keyStringForPass="10101010";
         private  static final String keyStringForAadhar="01010101";

    public static String decryptPass(String encryptedText) {
        try {
            byte[] keyBytes = keyStringForPass.getBytes(StandardCharsets.UTF_8);
            DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
            return decryptedString;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }
     public static String decryptAadhar(String encryptedText) {
        try {
            byte[] keyBytes = keyStringForAadhar.getBytes(StandardCharsets.UTF_8);
            DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
            return decryptedString;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }

   
}
