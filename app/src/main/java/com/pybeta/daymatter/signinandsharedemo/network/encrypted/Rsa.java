package com.pybeta.daymatter.signinandsharedemo.network.encrypted;

import android.util.Log;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 数据解密
 * Created by luogj on 2016/4/28.
 */
public class Rsa {

    private static final String ALGORITHM = "RSA";
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    /**
     * 用于与sdk server间数据通信的rsa key
     */
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2u28fXJL/NCJLY7OI1oRTvY26nOOpUUDG2l+FRF+Y/YcvaqYiZphMKsoUDZZGUvIc5V6X7H8IVLD1psGSmbl2bNIoca40/vS8nZMFkxQ60kpDYtqzwdPMj1JkbYoc1Ido7ZetfgKelvShNNLssMKSw2VRnzrh2Zif2QEoKoap/QIDAQAB";

    /**
     * 获取加，解密的 publicKey
     * @param algorithm
     * @param bysKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    private static PublicKey getPublicKeyFromX509(String algorithm, String bysKey) throws NoSuchAlgorithmException,Exception {

        byte[] decodedKey = Base64.decode(bysKey);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }

    /**
     *对内容加密的方法
     * @param content
     * @return
     */
    public static String encrypt(String content){

        try {
            PublicKey pubKey = getPublicKeyFromX509(ALGORITHM, publicKey);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            byte plaintext[] = content.getBytes("UTF-8");
            byte[] output = cipher.doFinal(plaintext);
            String s = Base64.encode(output);
            return s;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对内容解密的方法
     * @param content
     * @return
     */
    public static String decrypt(String content){

        try {
            PublicKey priKey = getPublicKeyFromX509(ALGORITHM, publicKey);

            byte plaintext[] = Base64.decode(content);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] output = cipher.doFinal(plaintext);
            return new String(output, ("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *是否要核对
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    public static boolean doCheck(String content, String sign, String publicKey) {

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes("utf-8"));
            Log.i("Result", "content :   "+content);
            Log.i("Result", "sign:   " + sign);
            boolean bVerify = signature.verify(Base64.decode(sign));
            Log.i("Result", "bverify = " + bVerify);
            return bVerify;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
