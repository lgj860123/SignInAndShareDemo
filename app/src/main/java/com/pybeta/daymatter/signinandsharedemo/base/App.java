package com.pybeta.daymatter.signinandsharedemo.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.wanjian.cockroach.Cockroach;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

import org.xutils.x;

import skin.support.SkinCompatManager;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.flycotablayout.app.SkinFlycoTabLayoutInflater;

/**
 * Created by luogj on 2018/2/2.
 */

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initSkinSupport(this);
        initNetwork(this);
        x.Ext.init(this);
        initCrash();
    }

    /**
     * 初始化换肤控件
     * @param application
     */
    private void initSkinSupport(Application application) {
        SkinCompatManager.withoutActivity(application)
                .addInflater(new SkinMaterialViewInflater())    // material design
                .addInflater(new SkinConstraintViewInflater())  // ConstraintLayout
                .addInflater(new SkinFlycoTabLayoutInflater())  // H07000223/FlycoTabLayout
                .setSkinStatusBarColorEnable(false)
                .loadSkin();

    }

    /**
     * 初始化网络请求
     * @param application
     */
    private void initNetwork(App application) {
        InitializationConfig config = InitializationConfig.newBuilder(application)
                .connectionTimeout(30 * 1000)       // 全局连接服务器超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)             // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .cacheStore(new DBCacheStore(application).setEnable(true))      // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。如果不使用缓存，setEnable(false)禁用。
                .cookieStore( new DBCookieStore(application).setEnable(true))   // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现CookieStore接口。 如果不维护cookie，setEnable(false)禁用。
                .networkExecutor(new OkHttpNetworkExecutor())                   // 配置网络层，默认URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                .retry(3)                           // 全局重试次数，配置后每个请求失败都会重试x次。
                .build();
        NoHttp.initialize(config);
        Logger.setDebug(true);                      // 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");              // 打印Log的tag。
    }

    /**
     * 初始化这个方法就是防止应用崩溃后重新启动
     */
    private void initCrash() {
        Cockroach.install(new Cockroach.ExceptionHandler() {
            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.e("AndroidRuntime","--->CockroachException:"+thread+"<---",throwable);
//                            Toast.makeText(App.this, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Throwable e) {
                        }
                    }
                });
            }
        });
    }
}
