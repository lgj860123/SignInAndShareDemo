package com.pybeta.daymatter.signinandsharedemo.custom;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.utils.AppUtils;
import com.pybeta.daymatter.signinandsharedemo.utils.SkinUtils;

import skin.support.widget.SkinCompatRelativeLayout;

/**
 * 换肤的相对布局
 * Created by luogj on 2018/2/6.
 */

public class SkinRelativeLayout extends SkinCompatRelativeLayout {

    private Context mContext;
    private float mMargin_top;
    private float mSkinMargin_top;
    private Class mSkinClazz;
    private Resources mSkinResources;

    public SkinRelativeLayout(Context context) {
        this(context,null);
    }

    public SkinRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        skinRefresh();
        setSkin();
    }

    private void skinRefresh() {
        mSkinClazz = SkinUtils.customViewChangeSkin( mContext );
        mSkinResources = SkinUtils.addOtherResourcesToMain( mContext );
    }

    private void setSkin() {
        mMargin_top = getResources().getDimension(R.dimen.MarginTop);
        Log.i("setSkin", "setSkin:first "+mMargin_top);
        mMargin_top= SkinUtils.getCustomViewSkinSize( mContext, "skinTabButton_margin_top", mSkinClazz, mSkinResources, mMargin_top );
        Log.i("setSkin", "setSkin:after "+mMargin_top);
        setPadding(0,getSize(mMargin_top),0,0);
    }

    public int getSize(float dimen){
        return  AppUtils.dpToPx(getContext(), dimen);
    }

    @Override
    public void applySkin() {
        super.applySkin();
        skinRefresh();
        setSkin();
    }

}
