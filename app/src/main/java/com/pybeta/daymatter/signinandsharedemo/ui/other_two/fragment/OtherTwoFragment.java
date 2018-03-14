package com.pybeta.daymatter.signinandsharedemo.ui.other_two.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseFragment;
import com.pybeta.daymatter.signinandsharedemo.custom.SkinTabButton;
import com.pybeta.daymatter.signinandsharedemo.interface_s.MainTabClickListener;

import org.xutils.view.annotation.ContentView;

/**
 * 其他2
 * Created by luogj on 2018/2/6.
 */

@ContentView(R.layout.fragment_other_two)
public class OtherTwoFragment extends BaseFragment implements MainTabClickListener {
    private FragmentManager fm;

    public static OtherTwoFragment newOtherTwoFragment(FragmentManager fm) {
        Bundle bundle = new Bundle();
        OtherTwoFragment fragment = new OtherTwoFragment();
        fragment.setArguments(bundle);
        fragment.fm = fm;
        return fragment;
    }

    @Override
    public void onTabClick(SkinTabButton tabButton, int tag) {

    }
}
