package com.pybeta.daymatter.signinandsharedemo.ui.my.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.bean.PersonalityThemeBean;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 个性主题适配器
 * Created by luogj on 2018/2/26.
 */

public class PersonalityThemeAdapter extends BaseQuickAdapter<PersonalityThemeBean,BaseViewHolder> {

    public PersonalityThemeAdapter(@LayoutRes int layoutResId, @Nullable List<PersonalityThemeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonalityThemeBean item) {
        ImageView iv_personalityItem_bg = helper.getView(R.id.iv_personalityItem_bg);
        RoundedCornersTransformation cornersTransformation = new RoundedCornersTransformation(mContext, 16, 0, RoundedCornersTransformation.CornerType.ALL);
        String itemId = item.getId();
        switch (itemId){
            case "5":
                Glide.with(mContext).load(R.mipmap.personality_five).bitmapTransform(cornersTransformation).into(iv_personalityItem_bg);
                break;
            case "6":
                Glide.with(mContext).load(R.mipmap.personality_six).bitmapTransform(cornersTransformation).into(iv_personalityItem_bg);
                break;
            case "7":
                Glide.with(mContext).load(R.mipmap.personality_seven).bitmapTransform(cornersTransformation).into(iv_personalityItem_bg);
                break;
            case "8":
                Glide.with(mContext).load(R.mipmap.personality_eight).bitmapTransform(cornersTransformation).into(iv_personalityItem_bg);
                break;
            case "9":
                Glide.with(mContext).load(R.mipmap.personality_nine).bitmapTransform(cornersTransformation).into(iv_personalityItem_bg);
                break;
            case "10":
                Glide.with(mContext).load(R.mipmap.personality_ten).bitmapTransform(cornersTransformation).into(iv_personalityItem_bg);
                break;
        }

        ImageView iv_itemSelect_icon = helper.getView(R.id.iv_itemSelect_icon);
        boolean select = item.isSelect();
        if (select){
            iv_itemSelect_icon.setVisibility(View.VISIBLE);
        }else {
            iv_itemSelect_icon.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_itemDesc,item.getThemeName());
    }
}
