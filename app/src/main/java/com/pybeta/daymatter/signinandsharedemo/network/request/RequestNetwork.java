package com.pybeta.daymatter.signinandsharedemo.network.request;

import android.content.Context;

import com.pybeta.daymatter.signinandsharedemo.bean.ResultDataBean;
import com.pybeta.daymatter.signinandsharedemo.interface_s.ResponseCallback;
import com.pybeta.daymatter.signinandsharedemo.interface_s.ResponseListener;
import com.pybeta.daymatter.signinandsharedemo.network.response.DefaultResponseListener;
import com.pybeta.daymatter.signinandsharedemo.network.response.ResponseResult;
import com.pybeta.daymatter.signinandsharedemo.utils.NetTypeUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created by luogj on 2018/2/8.
 */

public class RequestNetwork {

    /*********网络请求标识 start*************/
    public static String TEST = "net_test";
    /*********网络请求标识 end**************/

    private Context context;
    private String tag;
    private ResponseCallback callback;
    private Object object = new Object();
    private RequestQueue mQueue;

    public RequestNetwork(Context context, String tag, ResponseCallback callback){
        this.context = context;
        this.tag = tag;
        this.callback = callback;
        mQueue = NoHttp.newRequestQueue(3);
    }

    public void getCallbackData(EntityRequest entityRequest){
        if (!NetTypeUtils.isConnected(context)){
            callback.error(tag,"网络未连接！");
            stopRequest();
            return;
        }
        startRequest(0, entityRequest, new ResponseListener<ResultDataBean>() {
            @Override
            public void onSucceed(int what, ResponseResult<ResultDataBean> t) {
                if (t.isSucceed()){
                    callback.onSucceed(tag,t.getResult());
                }else {
                    callback.error(tag,t.getError());
                }
            }

            @Override
            public void onFailed(int what) {
                callback.error(tag,"网络请求失败！失败编号：" + what);
            }

            @Override
            public void onFinish(int what) {
                stopRequest();
            }

            @Override
            public void onStart(int what) {

            }
        });
    }


    private void startRequest(int what, AbstractRequest<ResultDataBean> request, ResponseListener<ResultDataBean> responseListener) {
        request.setCancelSign(object);
        mQueue.add( what, request, new DefaultResponseListener<>(responseListener, request));
    }

    private void stopRequest() {
        if (mQueue != null) {
            mQueue.cancelBySign(object);
            // 因为回调函数持有了activity，所以退出activity时请停止队列。
            mQueue.stop();
        }
    }
}
