package com.pybeta.daymatter.signinandsharedemo.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.pybeta.daymatter.signinandsharedemo.R;

/**
 * Created by luogj on 2018/2/26.
 */

public class AutoRelativeLayout extends RelativeLayout {

    private float mRatio = 0.0f;
    public AutoRelativeLayout(Context context) {
        this(context,null);
    }

    public AutoRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.auto_layout);
        mRatio = typedArray.getFloat(R.styleable.auto_layout_ratio, 0.0f);
        typedArray.recycle();
    }

    public void setRatio(float ratio){
        mRatio = ratio;
        Log.i("onMeasure: ",mRatio +" setRatio ");

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize;
        if(heightMode != MeasureSpec.EXACTLY  && mRatio != 0.0f ){
            heightSize = (int) (widthSize * mRatio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
