package com.pybeta.daymatter.signinandsharedemo.interface_s;

import com.pybeta.daymatter.signinandsharedemo.bean.ResultDataBean;

/**
 * Created by luogj on 2018/2/8.
 */

public interface ResponseCallback {
    void onSucceed(String tag, ResultDataBean resultDataBean);
    void error(String tag,String errorMessage );
    //TODO 如果需要可以把一下加上
//    void onFailed(String tag);
//    void onFinish(String tag);
//    void onStart(String tag);
}
