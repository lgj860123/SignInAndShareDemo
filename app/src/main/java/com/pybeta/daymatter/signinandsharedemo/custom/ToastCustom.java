package com.pybeta.daymatter.signinandsharedemo.custom;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pybeta.daymatter.signinandsharedemo.R;

/**
 * 自定义toast
 * Created by luogj on 2018/2/7.
 */

public class ToastCustom  extends Toast{

    private static ToastCustom toastCustom;
    private TextView tv_toastContent;

    public ToastCustom(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_toast_custom,null);
        tv_toastContent = (TextView) view.findViewById(R.id.tv_toastContent);
        setView(view);
    }


    public static ToastCustom shows(Context context, String content, int duration) {
        if(toastCustom == null) {
            toastCustom = new ToastCustom(context.getApplicationContext());
        }
        toastCustom.setGravity(Gravity.CENTER, 0, 0);
        toastCustom.setDuration(duration);
        toastCustom.setContent(content);
        toastCustom.show();
        return toastCustom;
    }

    private void setContent(String content) {
        this.tv_toastContent.setText(content);
    }

}
