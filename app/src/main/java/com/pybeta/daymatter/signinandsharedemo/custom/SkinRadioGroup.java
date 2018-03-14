package com.pybeta.daymatter.signinandsharedemo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import skin.support.widget.SkinCompatRadioGroup;


/**
 * 支持换肤的RadioGroup
 * Created by luogj on 2018/2/5.
 */

public class SkinRadioGroup extends SkinCompatRadioGroup {


    public SkinRadioGroup(Context context) {
        this(context,null);
    }

    public SkinRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        setLayoutParams(params);
    }
}
