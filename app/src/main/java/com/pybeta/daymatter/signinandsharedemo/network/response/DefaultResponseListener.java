package com.pybeta.daymatter.signinandsharedemo.network.response;

import com.pybeta.daymatter.signinandsharedemo.interface_s.ResponseListener;
import com.pybeta.daymatter.signinandsharedemo.network.request.AbstractRequest;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by luogj on 2018/2/8.
 */

public class DefaultResponseListener<T> implements OnResponseListener<ResponseResult<T>> {
    private ResponseListener<T> responseListener;
    private AbstractRequest<T> abstractRequest;

    public DefaultResponseListener(ResponseListener<T> responseListener, AbstractRequest<T> abstractRequest){
        this.responseListener = responseListener;
        this.abstractRequest = abstractRequest;

    }
    @Override
    public void onStart(int var1) {
        // TODO 显示dialog。
        if (responseListener != null && !abstractRequest.isCanceled())
            responseListener.onStart(var1);
    }

    @Override
    public void onSucceed(int var1, Response<ResponseResult<T>> response) {
        // http层的请求成功，响应码可能是200、400、500。
        if (responseListener != null && !abstractRequest.isCanceled())
            responseListener.onSucceed(var1, response.get());
    }

    @Override
    public void onFailed(int var1, Response<ResponseResult<T>> response) {
        Exception exception = response.getException();
        if (exception instanceof TimeoutError) { // 超时。
            // Toast
        }
        if (responseListener != null && !abstractRequest.isCanceled()){
            responseListener.onFailed(var1);
        }
    }

    @Override
    public void onFinish(int var1) {
        // TODO 关闭dialog。
        if (responseListener != null){
            responseListener.onFinish(var1);
        }
    }
}
