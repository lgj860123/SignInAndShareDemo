package com.pybeta.daymatter.signinandsharedemo.ui.my.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseFragment;
import com.pybeta.daymatter.signinandsharedemo.custom.SkinTabButton;
import com.pybeta.daymatter.signinandsharedemo.custom.ToastCustom;
import com.pybeta.daymatter.signinandsharedemo.interface_s.MainTabClickListener;
import com.pybeta.daymatter.signinandsharedemo.ui.my.activity.RegisterActivity;
import com.pybeta.daymatter.signinandsharedemo.ui.my.activity.SettingActivity;
import com.pybeta.daymatter.signinandsharedemo.ui.my.activity.SignInActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 我的
 * Created by luogj on 2018/2/6.
 */
@ContentView(R.layout.fragment_my)
public class MyFragment extends BaseFragment implements MainTabClickListener {

    private FragmentManager fm;
    private Activity activity;

    public static MyFragment newMyFragment(FragmentManager fm) {
        Bundle bundle = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        fragment.fm = fm;
        return fragment;
    }

    @Override
    public void onTabClick(SkinTabButton tabButton, int tag) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Event(value = {R.id.but_signIn,R.id.ib_setting},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.but_signIn:
                goToSignInView();
                break;
            case R.id.ib_setting:
                goToSettingView();
                break;
        }
    }

    /**
     * 进入设置界面
     */
    private void goToSettingView() {
        Intent intent_setting = new Intent(activity,SettingActivity.class);
        startActivity(intent_setting);
    }

    /**
     * 进入登录界面
     */
    private void goToSignInView() {
        Intent intent_signIn = new Intent(activity,SignInActivity.class);
        startActivity(intent_signIn);
    }

}
