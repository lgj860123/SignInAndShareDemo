package com.pybeta.daymatter.signinandsharedemo.network.request;

import android.content.Context;

import com.pybeta.daymatter.signinandsharedemo.interface_s.ResponseCallback;

/**
 * 设置网络请求参数
 * Created by luogj on 2018/2/8.
 */

public class SetRequestParams {

    public static void getTestData(Context context, String tag, ResponseCallback callback){
       RequestNetwork requestNetwork = new RequestNetwork(context,tag,callback);
        RequestParams requestParams = new RequestParams(context);
        requestParams.addCurrencyParams("interfaceName","getAD");
        requestParams.addCurrencyParams("positionId",1);
        EntityRequest entityRequest = requestParams.addCommit();
        requestNetwork.getCallbackData(entityRequest);
    }
}
