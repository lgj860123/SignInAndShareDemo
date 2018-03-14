package com.pybeta.daymatter.signinandsharedemo.ui.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseActivity;
import com.pybeta.daymatter.signinandsharedemo.bean.ColorThemeBean;
import com.pybeta.daymatter.signinandsharedemo.bean.PersonalityThemeBean;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 样式与浏览界面
 * Created by luogj on 2018/2/28.
 */
@ContentView(R.layout.activity_preview_theme)
public class ThemePreviewActivity extends BaseActivity{

    public static final String COLOR_THEME = "color_theme";
    public static final String PERSONALITY_THEME = "personality_theme";

    @ViewInject(R.id.iv_themePreview)
    ImageView iv_themePreview;

    @ViewInject(R.id.tv_themePreview_use)
    TextView tv_themePreview_use;
    //颜色主题对象
    private ColorThemeBean colorThemeBean;
    //个性主题对象
    private PersonalityThemeBean personalityThemeBean;
    private String colorThemePosition;
    private String personalityThemeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra(COLOR_THEME)){
            colorThemeBean = intent.getParcelableExtra(COLOR_THEME);
            colorThemePosition = colorThemeBean.getThemePosition();
            Log.i("initData", "##### initData: 颜色主题：" +colorThemeBean.toString() );
        }else if (intent.hasExtra(PERSONALITY_THEME)){
            personalityThemeBean = intent.getParcelableExtra(PERSONALITY_THEME);
            personalityThemeId = personalityThemeBean.getId();
            Log.i("initData", "##### initData: 个性主题：" +personalityThemeBean.toString());
        }


    }

    private void initView() {
        if (!TextUtils.isEmpty(colorThemePosition)){
            switch (colorThemePosition){
                case "1":
                    Glide.with(activity).load(R.mipmap.theme_show_blue).into(iv_themePreview);
                    break;
                case "2":
                    Glide.with(activity).load(R.mipmap.theme_show_green).into(iv_themePreview);
                    break;
                case "3":
                    Glide.with(activity).load(R.mipmap.theme_show_pink).into(iv_themePreview);
                    break;
                case "4":
                    Glide.with(activity).load(R.mipmap.theme_show_yellow).into(iv_themePreview);
                    break;

            }
        }else if (!TextUtils.isEmpty(personalityThemeId)){
            switch (personalityThemeId){
                case "5":
                    Glide.with(activity).load(R.mipmap.preview_theme_five).into(iv_themePreview);
                    break;
                case "6":
                    Glide.with(activity).load(R.mipmap.preview_theme_six).into(iv_themePreview);
                    break;
                case "7":
                    Glide.with(activity).load(R.mipmap.preview_theme_seven).into(iv_themePreview);
                    break;
                case "8":
                    Glide.with(activity).load(R.mipmap.preview_theme_eight).into(iv_themePreview);
                    break;
                case "9":
                    Glide.with(activity).load(R.mipmap.preview_theme_nine).into(iv_themePreview);
                    break;
                case "10":
                    Glide.with(activity).load(R.mipmap.preview_theme_ten).into(iv_themePreview);
                    break;

            }
        }
    }


    @Event(value = {R.id.ib_themePreview_back,R.id.tv_themePreview_use},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.ib_themePreview_back:
                finish();
                break;
            case R.id.tv_themePreview_use:
                break;
        }
    }
}
