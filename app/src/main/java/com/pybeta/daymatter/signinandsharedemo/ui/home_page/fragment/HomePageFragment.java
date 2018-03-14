package com.pybeta.daymatter.signinandsharedemo.ui.home_page.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseFragment;
import com.pybeta.daymatter.signinandsharedemo.bean.ResultDataBean;
import com.pybeta.daymatter.signinandsharedemo.custom.SkinTabButton;
import com.pybeta.daymatter.signinandsharedemo.interface_s.MainTabClickListener;
import com.pybeta.daymatter.signinandsharedemo.interface_s.ResponseCallback;
import com.pybeta.daymatter.signinandsharedemo.network.request.RequestNetwork;
import com.pybeta.daymatter.signinandsharedemo.network.request.SetRequestParams;

import org.xutils.view.annotation.ContentView;

/**
 * 首页
 * Created by luogj on 2018/2/6.
 */
@ContentView(R.layout.fragment_home_page)
public class HomePageFragment extends BaseFragment implements MainTabClickListener{

    private static final String TAG = "HomePageFragment";
    private FragmentManager fm;

    public static HomePageFragment newHomePageFragment(FragmentManager fm) {
        Bundle bundle = new Bundle();
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(bundle);
        fragment.fm = fm;
        return fragment;
    }

    @Override
    public void onTabClick(SkinTabButton tabButton, int tag) {
        if (tag != 1) {
            if (this.isAdded() && !this.isHidden()) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(this);
                ft.commit();
            }
        }
        if (tag == 1) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.show(this);
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        getTestData();
        Log.i(TAG, "##### onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void getTestData() {
        SetRequestParams.getTestData(activity, RequestNetwork.TEST, new ResponseCallback() {
            @Override
            public void onSucceed(String tag, ResultDataBean resultDataBean) {
                Log.i(TAG, "##### onSucceed: " +resultDataBean.getAd().toString());
            }

            @Override
            public void error(String tag, String errorMessage) {
                Log.i(TAG, "##### error: "+errorMessage);
            }
        });
    }
}
