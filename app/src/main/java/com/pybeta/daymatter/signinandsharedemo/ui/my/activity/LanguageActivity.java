package com.pybeta.daymatter.signinandsharedemo.ui.my.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseActivity;
import com.pybeta.daymatter.signinandsharedemo.bean.EventBean;
import com.pybeta.daymatter.signinandsharedemo.bean.LanguageBean;
import com.pybeta.daymatter.signinandsharedemo.interface_s.RecyclerViewItemOnClickListener;
import com.pybeta.daymatter.signinandsharedemo.ui.my.adapter.LanguageAdapter;
import com.pybeta.daymatter.signinandsharedemo.utils.GeneralUtils;
import com.pybeta.daymatter.signinandsharedemo.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * 多语言选择界面
 * Created by luogj on 2018/2/9.
 */
@ContentView(R.layout.activity_language)
public class LanguageActivity extends BaseActivity implements RecyclerViewItemOnClickListener{
    @ViewInject(R.id.rv_language)
    RecyclerView rv_language;

    private List<LanguageBean> languageBeanList = new ArrayList<>();
    //语言种类
    private int languageType = 0;
    private SpUtils spUtils;
    private LanguageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLanguageData();
        initView();
    }

    private void initLanguageData() {
        spUtils = SpUtils.getInstance(activity);
        languageType = spUtils.getLanguageType();
        String[] languageArray = getResources().getStringArray(R.array.language_array);
        for (int i = 0; i < languageArray.length;i++){
            LanguageBean languageBean = new LanguageBean();
            languageBean.setLanguageName(languageArray[i]);
            languageBean.setLanguageType(i);
            if (languageType == i){
                languageBean.setSelect(true);
            }else {
                languageBean.setSelect(false);
            }
            languageBeanList.add(languageBean);
        }
    }


    private void initView() {
        adapter = new LanguageAdapter(activity);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_language.setLayoutManager(manager);
        rv_language.setAdapter(adapter);
        adapter.setLanguageBeanList(languageBeanList);
        adapter.setOnItemClickListener(this);
    }

    @Event(value = {R.id.ib_language_back},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.ib_language_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        for (LanguageBean languageBean : languageBeanList){
            if (position == languageBean.getLanguageType()){
                languageBean.setSelect(true);
            }else {
                languageBean.setSelect(false);
            }
        }
        LanguageBean languageBean = languageBeanList.get(position);
        adapter.notifyDataSetChanged();
        spUtils.saveLanguageType(position);
        initSelectLanguage(position);
        goToSettingView(languageBean);
    }

    private void initSelectLanguage(int position) {
        switch (position){
            case 0:
                initLanguage(Locale.SIMPLIFIED_CHINESE);
                break;
            case 1:
                initLanguage(Locale.TRADITIONAL_CHINESE);
                break;
            case 2:
                initLanguage(Locale.ENGLISH);
                break;
        }
        EventBean eventBean = new EventBean(GeneralUtils.LANGUAGE_CHANGE);
        EventBus.getDefault().post(eventBean);
    }

    public void initLanguage(Locale locale) {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        config.setLocale(locale);
        resources.updateConfiguration(config, dm);
    }



    private void goToSettingView(LanguageBean languageBean) {
        Intent intent = new Intent(activity,SettingActivity.class);
        intent.putExtra("language_name",languageBean.getLanguageName());
        setResult(RESULT_OK,intent);
        spUtils.saveLanguageName(languageBean.getLanguageName());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
