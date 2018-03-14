package com.pybeta.daymatter.signinandsharedemo.interface_s;

import com.pybeta.daymatter.signinandsharedemo.network.response.ResponseResult;

/**
 * 网络请求响应监听器
 * Created by luogj on 2018/2/8.
 */

public interface ResponseListener<T> {

    void onSucceed(int what, ResponseResult<T> t);

    void onFailed(int what);

    void onFinish(int what);

    void onStart(int what);
}
