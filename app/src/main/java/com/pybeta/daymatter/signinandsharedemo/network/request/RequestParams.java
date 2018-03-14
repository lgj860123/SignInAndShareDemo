package com.pybeta.daymatter.signinandsharedemo.network.request;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.pybeta.daymatter.signinandsharedemo.bean.ResultDataBean;
import com.pybeta.daymatter.signinandsharedemo.utils.AppUtils;
import com.yanzhenjie.nohttp.RequestMethod;

/**
 * 设置网络请求参数
 * Created by luogj on 2018/2/8.
 */

public class RequestParams {
    private static final String TAG = "RequestParams";
    //整个网络请求通用的json
    private JSONObject currencyJson;
    //设置不同请求参数使用的json
    private JSONObject paramsJson;

    public RequestParams(Context context){
        currencyJson = new JSONObject();
        paramsJson = new JSONObject();
        currencyJson.put("platform", 1);
        currencyJson.put("version", "2.0");
        currencyJson.put("deviceId", AppUtils.getDeviceId(context));
        currencyJson.put("imei", AppUtils.getIMEI(context));
        Log.i(TAG, "##### RequestParams: "+AppUtils.getIMEI(context));
    }

    public void addCurrencyParams(String key, Object value) {
        if (AppUtils.isEmpty(key) || value == null)
            return;
        currencyJson.put(key, value);
    }

    public void addParams(String key, Object value) {
        if (value == null)
            return;
        paramsJson.put(key, value);
    }

    public EntityRequest addCommit() {
        JSONObject json = new JSONObject();
        json.put("RequestParams", currencyJson);
        json.put("Event", paramsJson);
        String key = RequestConfig.createKey( 8 );
        String rData = RequestConfig.encryptData( key, json.toJSONString() );
        String rKey = RequestConfig.encryptKey( key );
        EntityRequest<ResultDataBean> entityRequest = new EntityRequest<>(RequestConfig.URL, RequestMethod.POST, ResultDataBean.class);
        entityRequest.add("rKey", rKey);
        entityRequest.add("rData", rData);
        return  entityRequest;

    }
}
