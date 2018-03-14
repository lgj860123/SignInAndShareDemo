package com.pybeta.daymatter.signinandsharedemo.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.utils.AppUtils;

import skin.support.widget.SkinCompatView;

/**
 * 自定义假的状态栏，便于换肤
 * Created by luogj on 2018/2/7.
 */

public class StatusBarCustom extends SkinCompatView{

    private int statusBarHeight;
    private boolean showAlways = false;
    private int showAfterSdkVersionInt = 123454321;

    public StatusBarCustom(Context context) {
        this(context,null);
    }

    public StatusBarCustom(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StatusBarCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CustomStatusBarView, defStyleAttr, defStyleAttr);
        try {
            showAlways = a.getBoolean(R.styleable.CustomStatusBarView_showAlways, showAlways);
            if (!showAlways) {
                showAfterSdkVersionInt = a.getInt(R.styleable.CustomStatusBarView_showAfterSdkVersionInt, showAfterSdkVersionInt);
            }
        } finally {
            a.recycle();
        }
        initView();
    }

    private void initView() {
        if (isInEditMode()) {
            statusBarHeight = 0;
            return ;
        }
        if (Build.VERSION.SDK_INT < 19) {
            statusBarHeight = 0;
            return ;
        }
        statusBarHeight = getStatusBarHeight(getContext());
    }

    private int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        // 获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            // 根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        if (statusBarHeight == -1) {
            try {
                Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                Object object = clazz.newInstance();
                int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
                statusBarHeight = context.getResources().getDimensionPixelSize(height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (statusBarHeight == -1) {
            statusBarHeight = AppUtils.dpToPx(context, 25);
        }
        return statusBarHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int h = resolveSize(statusBarHeight, heightMeasureSpec);
        setMeasuredDimension(w, h);
    }
}
