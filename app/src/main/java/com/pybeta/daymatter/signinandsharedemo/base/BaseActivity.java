package com.pybeta.daymatter.signinandsharedemo.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.pybeta.daymatter.signinandsharedemo.bean.EventBean;
import com.pybeta.daymatter.signinandsharedemo.utils.GeneralUtils;
import com.pybeta.daymatter.signinandsharedemo.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.x;

import java.util.Locale;


/**
 * 所有activity的父类
 * Created by luogj on 2018/2/2.
 */

public class BaseActivity extends AppCompatActivity {
    protected Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        init();
        initLanguageChange();
    }


    private void init() {
        //初始化注解
        x.view().inject(this);
        setTranslucentStatus(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLanguageChange();
    }

    /**
     * 设置状态栏透明
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 我不知道为什么要加这个
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0 全透明实现
            Window window = getWindow();
            window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );
            window.setStatusBarColor( Color.TRANSPARENT );
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 4.4 全透明状态栏
            Window window = getWindow();
            WindowManager.LayoutParams winParams = window.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            window.setAttributes( winParams );
        }
    }


    /**
     * 初始化语言
     */
    public void initLanguageChange() {
        SpUtils spUtils = SpUtils.getInstance(activity);
        int languageType = spUtils.getLanguageType();
        if (languageType == 0){
            initLanguage(Locale.SIMPLIFIED_CHINESE);
        }else if (languageType == 1){
            initLanguage(Locale.TRADITIONAL_CHINESE);
        }else if (languageType == 2){
            initLanguage(Locale.ENGLISH);
        }
    }

    public void initLanguage(Locale locale) {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        config.setLocale(locale);
        resources.updateConfiguration(config, dm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
