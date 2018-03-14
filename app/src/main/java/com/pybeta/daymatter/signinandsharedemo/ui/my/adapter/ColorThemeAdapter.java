package com.pybeta.daymatter.signinandsharedemo.ui.my.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.bean.ColorThemeBean;

import java.util.List;

/**
 * 颜色主题适配器
 * Created by luogj on 2018/2/26.
 */

public class ColorThemeAdapter extends BaseQuickAdapter<ColorThemeBean,BaseViewHolder> {

    public ColorThemeAdapter(@LayoutRes int layoutResId, @Nullable List<ColorThemeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ColorThemeBean item) {
        RelativeLayout rl_colorTheme_itemBg = helper.getView(R.id.rl_colorTheme_itemBg);
        String themePosition = item.getThemePosition();
        switch (themePosition){
            case "1":
                rl_colorTheme_itemBg.setBackgroundResource(R.drawable.view_color_theme_blue);
                break;
            case "2":
                rl_colorTheme_itemBg.setBackgroundResource(R.drawable.view_color_theme_green);
                break;
            case "3":
                rl_colorTheme_itemBg.setBackgroundResource(R.drawable.view_color_theme_pink);
                break;
            case "4":
                rl_colorTheme_itemBg.setBackgroundResource(R.drawable.view_color_theme_yellow);
                break;
        }

        ImageView iv_itemSelect_icon = helper.getView(R.id.iv_colorTheme_itemSelect_icon);
        boolean select = item.isSelect();
        if (select){
            iv_itemSelect_icon.setVisibility(View.VISIBLE);
        }else {
            iv_itemSelect_icon.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_colorTheme_itemDesc,item.getThemeName());
    }
}
