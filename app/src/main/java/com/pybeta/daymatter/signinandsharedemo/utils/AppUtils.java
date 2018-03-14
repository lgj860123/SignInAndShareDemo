package com.pybeta.daymatter.signinandsharedemo.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * App的工具类
 * Created by luogj on 2018/2/5.
 */

public class AppUtils {

    /**
     *  dp 的单位 转成为 px(像素)
     */
    public static int dpToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     *  px(像素) 的单位 转成为 dp
     */
    public static int pxToDp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 判断字符串是为空
     * @param str
     * @return  true:为空, false:不为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "null".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 手机设备标识码
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager telManager = null;
        WifiManager wifi = null;
        String deviceId = null;
        try {
            telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telManager.getDeviceId();
            if (TextUtils.isEmpty(deviceId)) {
                wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                deviceId = wifi.getConnectionInfo().getMacAddress();
            }
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            telManager = null;
            wifi = null;
        }
        return deviceId;
    }

    /**
     * 获取应用版本号
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String packageName = null;
        String versionName = null;
        int versionCode = 0;
        try {
            packageName = context.getPackageName();

            versionName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
            versionCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取手机的IEMI号
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }
}
