package server.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static SecretKeySpec secretKey;

    private static void setKey(String myKey) {

        MessageDigest sha = null;
        try {

            byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) {
        try {

            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        final String secretKey = "5*59-2";

        String originalString = "some text[lcvsxdl k980534^$%#%$^*/*-+-7846958";
        String encryptedString = AES.encrypt(originalString, secretKey);
        String decryptedString = AES.decrypt(encryptedString, secretKey);

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);

        System.out.println();
        System.out.println(getSecretKeyByToken("111111111111"));
        System.out.println((int) "1".charAt(0));
    }

    public static String getSecretKeyByToken(String token) {
        String secretKeyString;
        secretKeyString = "sg";
        secretKeyString += "" + (char) (token.charAt(3) + token.charAt(5));
        secretKeyString += (char) (token.charAt(0) * 2);
        secretKeyString += (char) (token.charAt(1) + token.charAt(4) - token.charAt(6));
        secretKeyString += "?!";
        secretKeyString += token.substring(7, 9);
        secretKeyString += "w./*-+er";
        secretKeyString += (char) (token.charAt(9));
        secretKeyString += "df";
        return secretKeyString;
    }
}
