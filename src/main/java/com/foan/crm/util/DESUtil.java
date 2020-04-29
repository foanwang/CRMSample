package com.foan.crm.util;

import org.apache.shiro.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

public class DESUtil {
    private final static String DES = "DES";
    private final static String ENCODE = "UTF-8";
    private final static String defaultKey = "test1234";//at least 8 byte or multiple 8 bytes


    public static String encrypt(String express) throws Exception {
        if (express == null) return null;
        byte[] bCiphertext = encrypt(express.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
        String ciphertext = Base64.encodeToString(bCiphertext);
        return ciphertext;
    }

    public static String decrypt(String ciphertext) throws IOException, Exception {
        if (ciphertext == null) return null;
        byte[] buf =  Base64.decode(ciphertext);
        byte[] bExpress = decrypt(buf, defaultKey.getBytes(ENCODE));
        return new String(bExpress, ENCODE);
    }

    /**
     * Description
     * @param express
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String express, String key) throws Exception {
        if (express == null) return null;
        byte[] bCiphertext = encrypt(express.getBytes(ENCODE), key.getBytes(ENCODE));
        return new String(bCiphertext, ENCODE);
    }

    /**
     * Description
     * @param ciphertext
     * @param key
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String ciphertext, String key) throws IOException, Exception {
        if (ciphertext == null) return null;
        byte[] bExpress = decrypt(ciphertext.getBytes(ENCODE), key.getBytes(ENCODE));
        return new String(bExpress, ENCODE);
    }

    /**
     * Description encrypt
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }

    /**
     * Description decrypt
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }
}
