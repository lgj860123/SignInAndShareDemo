package com.pybeta.daymatter.signinandsharedemo.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pybeta.daymatter.signinandsharedemo.utils.SpUtils;

import org.xutils.x;

import java.util.Locale;

/**
 * 所有fragment的基类
 * Created by luogj on 2018/2/6.
 */

public class BaseFragment extends Fragment {

    protected Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentView();
    }

    /**
     * 初始化fragment界面
     */
    private void initFragmentView() {
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initLanguageChange();
    }


    private void initLanguageChange() {
        SpUtils spUtils = SpUtils.getInstance(activity);
        int languageType = spUtils.getLanguageType();
        if (languageType == 0){
            initLanguage(Locale.SIMPLIFIED_CHINESE);
        }else if (languageType == 1){
            initLanguage(Locale.TRADITIONAL_CHINESE);
        }else if (languageType == 2){
            initLanguage(Locale.ENGLISH);
        }
    }

    public void initLanguage(Locale locale) {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        config.setLocale(locale);
        resources.updateConfiguration(config, dm);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
