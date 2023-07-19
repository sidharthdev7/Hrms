package enh.userutil;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Encrypter{
  //  private static final Logger LOGGER = LogManager.getLogger(Des.class);
   
 private static final String ALGORITHM = "DES";
          private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";
      
        String plainText;
         private  static final String keyStringForPass="10101010";
         private  static final String keyStringForAadhar="01010101";
      
        public static String encrypt(String plainText)
        {
            
        try{
        byte[] keyBytes = keyStringForAadhar.getBytes(StandardCharsets.UTF_8);
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        Key key = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedString;
        }

        
        catch(Exception e)
        {
            System.out.print(e);
            return null;

        }
        }
         public static String encryptPass(String plainText)
        {
            
        try{
        byte[] keyBytes = keyStringForPass.getBytes(StandardCharsets.UTF_8);
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        Key key = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
       // byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
       // Convert the decrypted bytes to string
        //String decryptedData = new String(decryptedBytes, StandardCharsets.UTF_8);
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedString;
        }

        
        catch(Exception e)
        {
            System.out.print(e);
            return null;

        }
        }

       }