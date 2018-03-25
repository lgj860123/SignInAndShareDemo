package com.pybeta.daymatter.signinandsharedemo.network.encrypted;



import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 数据加密
 * Created by luogj on 2016/4/27.
 */
public class Des {
    static final String iv = "12345678";

    /**
     *加密
     * @param key
     * @param encryptString
     * @return
     */
    public static String encrypt(String key, String encryptString) throws Exception {

        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec sKey = new SecretKeySpec(key.getBytes(),"DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, sKey, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return Base64.encode(encryptedData);
    }

    /**
     * 解密
     * @param key
     * @param decryptString
     * @return
     */
    public static String decrypt(String key, String decryptString) throws Exception {

        byte[] byteMi = Base64.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec sKey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, sKey, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData);
    }
}
