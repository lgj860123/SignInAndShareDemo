package com.pybeta.daymatter.signinandsharedemo.network.request;

import com.pybeta.daymatter.signinandsharedemo.network.encrypted.Des;
import com.pybeta.daymatter.signinandsharedemo.network.encrypted.Rsa;
import com.pybeta.daymatter.signinandsharedemo.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by luogj on 2018/2/8.
 */

public class RequestConfig {
    //网络请求测试地址
    public static final String URL =  "http://ceshidaoshurinew.icodestar.com/index.php";
    /**
     * 解密接口返回的数据
     * @param jsonStr 接口加密Json数据
     * @return 解密后数据
     */
    public static String decryptData(String jsonStr) {
        String data = null;
        JSONObject json ;
        String rData ;
        String rKey ;
        try {
            json = new JSONObject(jsonStr);
            rData = json.optString("rData");
            rKey = json.optString("rKey");

            if(!AppUtils.isEmpty(rData) && !AppUtils.isEmpty(rKey)) {
                String key = Rsa.decrypt(rKey);

                if(!AppUtils.isEmpty(key))
                    data = Des.decrypt(key, rData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            json = null;
        }
        return data;
    }

    /**
     * 加密请求参数
     * @param key
     * @param jsonStr
     * @return
     */
    public static String encryptData(String key, String jsonStr) {
        String rData = null;
        try {
            rData = Des.encrypt(key, jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rData;
    }

    /**
     * 加密Key
     * @param key
     * @return
     */
    public static String encryptKey(String key) {
        return Rsa.encrypt(key);
    }

    /**
     * 生成Key
     * @param length 生成Key的长度
     * @return
     */
    public static String createKey(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
