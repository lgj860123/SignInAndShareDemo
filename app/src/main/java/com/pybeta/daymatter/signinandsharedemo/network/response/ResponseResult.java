package com.pybeta.daymatter.signinandsharedemo.network.response;

import com.yanzhenjie.nohttp.Headers;

/**
 * 请求网络响应的结果
 * Created by luogj on 2018/2/8.
 */

public class ResponseResult<T> {
    private boolean isSucceed;  // 业务和Http层是否成功。
    private T result;           // 结果。
    private Headers headers;    // Http相应头。
    private String error;       // 错误提示信息。

    public ResponseResult(boolean isSucceed, T result, Headers headers, String error) {
        this.isSucceed = isSucceed;
        this.result = result;
        this.headers = headers;
        this.error = error;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public T getResult() {
        return result;
    }

    public Headers getHeaders() {
        return headers;
    }

    public String getError() {
        return error;
    }
}
