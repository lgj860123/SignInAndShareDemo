package com.pybeta.daymatter.signinandsharedemo.custom;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.utils.AppUtils;
import com.pybeta.daymatter.signinandsharedemo.utils.SkinUtils;

import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatRadioButton;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * 支持换肤的tabButton
 * Created by luogj on 2018/2/5.
 */

public class SkinTabButton extends SkinCompatRadioButton implements SkinCompatSupportable {

    private int radius = 0;
    private int offset = 0;
    private Paint paint = null;
    private boolean isShow = false;
    private int topDrawableWidth = 0;
    private int textStringResId = INVALID_ID;
    private Class mSkinClazz;
    private Resources mSkinResources;
    private Context mContext;
    private float mDrawablePadding;
    private float mDrawableTop;
    private float mDrawableBottom;
    private float mSkinDrawablePadding;
    private float mSkinDrawableTop;
    private float mSkinDrawableBottom;

    public SkinTabButton(Context context) {
        this(context,null);
    }

    public SkinTabButton(Context context, AttributeSet attrs) {
        this(context, attrs,android.R.attr.radioButtonStyle);
    }

    public SkinTabButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setButtonDrawable(null);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SkinTabButton);
        textStringResId = a.getResourceId(R.styleable.SkinTabButton_text, INVALID_ID);
        radius = (int) a.getDimension(R.styleable.SkinTabButton_dotSize, AppUtils.dpToPx(context, 4));
        a.recycle();

        skinRefresh();

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        offset = radius * 2;

        Drawable[] drawables = null;
        try {
            drawables = getCompoundDrawables();
            if(drawables == null || drawables[1] == null){
                return;
            }
            topDrawableWidth = drawables[1].getIntrinsicWidth();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            drawables = null;
        }
        applyText();
    }

    private void skinRefresh() {
        mSkinClazz = SkinUtils.customViewChangeSkin(mContext);
        mSkinResources = SkinUtils.addOtherResourcesToMain(mContext);
    }

    private void applyText() {
        textStringResId = SkinCompatHelper.checkResourceId(textStringResId);
        mDrawablePadding = getResources().getDimension(R.dimen.DrawablePadding);
        mDrawableTop = getResources().getDimensionPixelSize(R.dimen.DrawableTop);
        mDrawableBottom = getResources().getDimensionPixelSize(R.dimen.DrawableBottom);
        mSkinDrawablePadding=mDrawablePadding;
        mSkinDrawableTop=mDrawableTop;
        mSkinDrawableBottom=mDrawableBottom;

        mDrawablePadding = SkinUtils.getCustomViewSkinSize(mContext, "skinTabButton_drawable_padding", mSkinClazz, mSkinResources, mSkinDrawablePadding);
        mDrawableTop=SkinUtils.getCustomViewSkinSize(mContext, "skinTabButton_drawable_top", mSkinClazz, mSkinResources, mSkinDrawableTop);
        mDrawableBottom=SkinUtils.getCustomViewSkinSize(mContext, "skinTabButton_drawable_bottom", mSkinClazz, mSkinResources, mSkinDrawableBottom);
        if (textStringResId != INVALID_ID) {
            String textString = SkinUtils.getString(this, textStringResId);

            if (TextUtils.isEmpty(textString)) {
                setText("");
                setCompoundDrawablePadding(-AppUtils.dpToPx(getContext(), 6.5f));
            } else {
                setText(textString);

                setCompoundDrawablePadding(-AppUtils.dpToPx(getContext(),mDrawablePadding));
                setPadding(0, AppUtils.dpToPx(getContext(), mDrawableTop),0, AppUtils.dpToPx(getContext(),mDrawableBottom));
            }
        } else {
            setText("");
            setCompoundDrawablePadding(-AppUtils.dpToPx(getContext(), 6.5f));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isShow) {
            canvas.drawCircle((getWidth()/2+topDrawableWidth/2), offset, radius, paint);
        }
    }
}
