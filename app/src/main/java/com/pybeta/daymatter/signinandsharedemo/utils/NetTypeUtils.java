package com.pybeta.daymatter.signinandsharedemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络连接的状态以及网络连接的种类
 * Created by luogj on 2018/2/8.
 */

public class NetTypeUtils {

    private static final int WIFI_NET = 1;
    private static final int MOBILE_NET = 2;

    /**
     * 网络是否连接
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();
        return netInfo == null || !netInfo.isConnected() ? false : true;
    }

    /**
     * 获取网络类型
     * @param context
     * @return -1:网络, 0:未知, 1:wifi, 2:手机
     */
    public static int getConnectedType(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();
        if (netInfo == null)
            return -1;
        String type = netInfo.getTypeName();
        if (type == null) {
            return -1;
        } else if ("WIFI".equals(type)) {
            return WIFI_NET;
        } else if ("MOBILE".equals(type)) {
            return MOBILE_NET;
        }
        return 0;
    }
}
