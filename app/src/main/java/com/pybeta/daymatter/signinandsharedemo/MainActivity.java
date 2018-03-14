package com.pybeta.daymatter.signinandsharedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pybeta.daymatter.signinandsharedemo.base.BaseActivity;
import com.pybeta.daymatter.signinandsharedemo.bean.EventBean;
import com.pybeta.daymatter.signinandsharedemo.custom.SkinRadioGroup;
import com.pybeta.daymatter.signinandsharedemo.custom.SkinTabButton;
import com.pybeta.daymatter.signinandsharedemo.ui.home_page.fragment.HomePageFragment;
import com.pybeta.daymatter.signinandsharedemo.ui.my.fragment.MyFragment;
import com.pybeta.daymatter.signinandsharedemo.ui.other_one.fragment.OtherOneFragment;
import com.pybeta.daymatter.signinandsharedemo.ui.other_two.fragment.OtherTwoFragment;
import com.pybeta.daymatter.signinandsharedemo.utils.GeneralUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * 主界面
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @ViewInject(R.id.main_root)
    RelativeLayout main_root;
    @ViewInject(R.id.main_radioGroup)
    SkinRadioGroup main_radioGroup;
    @ViewInject(R.id.main_tab_homePage)
    SkinTabButton main_tab_homePage;
    @ViewInject(R.id.main_tab_otherOne)
    SkinTabButton main_tab_otherOne;
    @ViewInject(R.id.main_tab_otherTwo)
    SkinTabButton main_tab_otherTwo;
    @ViewInject(R.id.main_tab_my)
    SkinTabButton main_tab_my;

    @ViewInject(R.id.main_tab_add)
    ImageButton main_tab_add;

    @ViewInject(R.id.main_tab_content)
    FrameLayout main_tab_content;

    private HomePageFragment homePageFragment;
    private OtherOneFragment otherOneFragment;
    private OtherTwoFragment otherTwoFragment;
    private MyFragment myFragment;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "##### onCreate: ");
        context = this;
        EventBus.getDefault().register(this);
        initMainView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initMainView() {

        homePageFragment = HomePageFragment.newHomePageFragment(getSupportFragmentManager());
        otherOneFragment = OtherOneFragment.newOtherOneFragment(getSupportFragmentManager());
        otherTwoFragment = OtherTwoFragment.newOtherTwoFragment(getSupportFragmentManager());
        myFragment = MyFragment.newMyFragment(getSupportFragmentManager());

        main_radioGroup.setOnCheckedChangeListener(onTabCheckedChangeListener);
        main_tab_homePage.setOnClickListener(onTabClickListener);
        main_tab_otherOne.setOnClickListener(onTabClickListener);
        main_tab_add.setOnClickListener(onTabClickListener);
        main_tab_otherTwo.setOnClickListener(onTabClickListener);
        main_tab_my.setOnClickListener(onTabClickListener);

        checkHomePage();
    }

    /**
     * 进入应用，首页默认为选中
     */
    private void checkHomePage() {
        main_radioGroup.clearCheck();
        main_tab_homePage.setChecked(true);
    }

    private RadioGroup.OnCheckedChangeListener onTabCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

            switch (checkedId){
                case R.id.main_tab_homePage:
                    switchFragment(homePageFragment, "home_page");
                    main_root.setBackgroundResource(R.drawable.view_home_page_bg);
                    break;
                case R.id.main_tab_otherOne:
                    switchFragment(otherOneFragment, "other_one");
                    main_root.setBackgroundResource(R.drawable.view_other_one_bg);
                    break;
                case R.id.main_tab_otherTwo:
                    switchFragment(otherTwoFragment, "other_two");
                    main_root.setBackgroundResource(R.drawable.view_other_two_bg);
                    break;
                case R.id.main_tab_my:
                    switchFragment(myFragment, "my");
                    main_root.setBackgroundResource(R.drawable.view_my_bg);
                    break;
            }
        }
    };

    private void switchFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!fragment.isAdded() && null == fm.findFragmentByTag(tag)) {
            fm.executePendingTransactions();
            ft.add(R.id.main_tab_content, fragment, tag);
        }
        if (!homePageFragment.isHidden())
            ft.hide(homePageFragment);

        if (!otherOneFragment.isHidden())
            ft.hide(otherOneFragment);

        if (!otherTwoFragment.isHidden()) {
            ft.hide(otherTwoFragment);
        }
        if (!myFragment.isHidden()) {
            ft.hide(myFragment);
        }
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }


    private View.OnClickListener onTabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_tab_homePage:
                    if (homePageFragment.isAdded()) {
                        homePageFragment.onTabClick((SkinTabButton) v, 1);
                    }
                    break;
                case R.id.main_tab_otherOne:
                    if (otherOneFragment.isAdded()) {
                        otherOneFragment.onTabClick((SkinTabButton) v, 2);
                    }
                    break;
                case R.id.main_tab_otherTwo:
                    if (otherTwoFragment.isAdded()) {
                        otherTwoFragment.onTabClick((SkinTabButton) v, 3);
                    }
                    break;
                case R.id.main_tab_my:
                    if (myFragment.isAdded()) {
                        myFragment.onTabClick((SkinTabButton) v, 4);
                    }
                    break;
                case R.id.main_tab_add:
                    Intent intent = new Intent(context,AddActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    /*******eventBus开始部分：切换语言时，通过eventBus重新开启MainActivity*******/
    //如果不关闭mainActivity重新启动的，切换语言的时候有些是却换不过来的，这也是仿微信的切换语言的

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onMainEvent(EventBean eventBean) {
        String eventDesc = eventBean.getEventDesc();
        if (eventDesc.contains(GeneralUtils.LANGUAGE_CHANGE)){
            finish();
            Intent intent = new Intent(activity,MainActivity.class);
            startActivity(intent);
        }
    }
    /****************************eventBus结束部分**************************/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /*****************************退出应用start***************************/
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), getString(R.string.quit_app), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
    /******************************退出应用end**************************/

}
