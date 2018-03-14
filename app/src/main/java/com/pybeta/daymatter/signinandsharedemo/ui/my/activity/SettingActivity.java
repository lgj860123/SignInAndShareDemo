package com.pybeta.daymatter.signinandsharedemo.ui.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseActivity;
import com.pybeta.daymatter.signinandsharedemo.utils.SpUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 设置界面
 * Created by luogj on 2018/2/9.
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity{
    private static final int LANGUAGE_CODE = 100;
    private static final int THEME_CODE = 200;

    @ViewInject(R.id.tv_language)
    TextView tv_language;
    @ViewInject(R.id.tv_selectorTheme)
    TextView tv_selectorTheme;

    private String languageName = "中文简体";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        SpUtils spUtils = SpUtils.getInstance(activity);
        String select_languageName = spUtils.getLanguageName();
        if (!TextUtils.isEmpty(select_languageName)){
            tv_language.setText(select_languageName);
        }else {
            tv_language.setText(languageName);
        }

    }

    @Event(value = {R.id.ib_setting_back,R.id.rl_language,R.id.rl_theme},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.ib_setting_back:
                finish();
                break;
            case R.id.rl_language:
                goToLanguageView();
                break;
            case R.id.rl_theme:
                goToThemeView();
                break;
        }

    }



    /**
     * 进入选择语言种类界面
     */
    private void goToLanguageView() {
        Intent intent_language = new Intent(activity,LanguageActivity.class);
        startActivityForResult(intent_language,LANGUAGE_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LANGUAGE_CODE){
            String language_name = data.getStringExtra("language_name");
            tv_language.setText(language_name);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 进入个性主题界面
     */
    private void goToThemeView() {
        Intent intent_theme = new Intent(activity,ThemeSelectorActivity.class);
        startActivityForResult(intent_theme,THEME_CODE);

    }
}
