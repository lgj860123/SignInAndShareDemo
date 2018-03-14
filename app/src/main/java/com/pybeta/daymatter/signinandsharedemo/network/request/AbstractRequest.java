package com.pybeta.daymatter.signinandsharedemo.network.request;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pybeta.daymatter.signinandsharedemo.network.response.ResponseResult;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * Created by luogj on 2018/2/8.
 */

public abstract class AbstractRequest <T> extends RestRequest<ResponseResult<T>> {
    private static final String TAG = "AbstractRequest";
    private String data = "{\"noKEy\":\"没有包含这个KEY\"}";
    public AbstractRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    public ResponseResult<T> parseResponse(Headers headers, byte[] bytes) throws Exception {
        int responseCode = headers.getResponseCode(); // 响应码。
        // 响应码等于200，Http层成功。
        if (responseCode == 200) {
            if (bytes == null || bytes.length == 0) {
                // 服务器包体为空。
                return new ResponseResult<>(true, null, headers, null);
            } else {
                String bodyString = StringRequest.parseResponseString(headers, bytes);
                String decryptData = RequestConfig.decryptData(bodyString);
                try {
                    JSONObject bodyObject = JSON.parseObject(decryptData);
                    int resultCode = bodyObject.getJSONObject("Result").getIntValue("resultCode");
                    // 业务层成功。
                    if (resultCode == 0) {
                        if(bodyObject.containsKey("data")){
                            data = bodyObject.getString("data");
                            Log.i(TAG, "##### parseResponse: "+data);
                        }
                        T result = getResult(data);
                        return new ResponseResult<>(true, result, headers, null);
                    } else {
                        String error = bodyObject.getJSONObject("Result").getString("errorType");
                        Log.i(TAG, "##### parseResponse: 错误信息： "+error);
                        return new ResponseResult<>(false, null, headers, error);
                    }
                } catch (Exception e) {
                    // 解析异常，测试时通过，正式发布后就是服务器的锅。
                    String error = "解析异常";
                    e.printStackTrace();
                    Log.i(TAG,"ss  "+e.getMessage());
                    return new ResponseResult<>(false, null, headers, null);
                }
            }
        } else { // 其它响应码，如果和服务器没有约定，那就是服务器发生错误了。
            String error = "服务器返回数据格式错误，请稍后重试";
            return new ResponseResult<>(false, null, headers, error);
        }
    }

    protected abstract T getResult(String responseBody) throws Exception;
}
