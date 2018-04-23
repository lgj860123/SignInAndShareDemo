package com.pybeta.daymatter.signinandsharedemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
import com.pybeta.daymatter.signinandsharedemo.utils.PermissionUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * 主界面
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
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
        initData();
    }

    private void initData() {
        
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

    /*****************安卓系统大于6.0动态获取权限部分开始**************/
    //定义照相权限
//    private static final int REQUEST_CAMERA = 0;
//    //定义联系人的权限
//    private static final int REQUEST_CONTACTS = 1;
//    //定义SD卡的读写权限
//    private static String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_CONTACTS,
//            Manifest.permission.WRITE_CONTACTS};
//
//    public void showCamera(View view) {
//        Log.i(TAG, "Show camera button pressed. Checking permission.");
//        // BEGIN_INCLUDE(camera_permission)
//        // Check if the Camera permission is already available.
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Camera permission has not been granted.
//            requestCameraPermission();
//        } else {
//            // Camera permissions is already available, show the camera preview.
//            Log.i(TAG,"CAMERA permission has already been granted. Displaying camera preview.");
//            showCameraPreview();
//        }
//        // END_INCLUDE(camera_permission)
//
//    }
//
//    private void requestCameraPermission() {
//        Log.i(TAG, "CAMERA permission has NOT been granted. Requesting permission.");
//        // BEGIN_INCLUDE(camera_permission_request)
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)) {
//            // Provide an additional rationale to the user if the permission was not granted
//            // and the user would benefit from additional context for the use of the permission.
//            // For example if the user has previously denied the permission.
//            Log.i(TAG,"Displaying camera permission rationale to provide additional context.");
//            Snackbar.make(mLayout, R.string.permission_camera_rationale,
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction(R.string.ok, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            ActivityCompat.requestPermissions(MainActivity.this,
//                                    new String[]{Manifest.permission.CAMERA},
//                                    REQUEST_CAMERA);
//                        }
//                    })
//                    .show();
//        } else {
//            // Camera permission has not been granted yet. Request it directly.
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
//                    REQUEST_CAMERA);
//        }
//        // END_INCLUDE(camera_permission_request)
//    }
//
//    public void showContacts(View v) {
//        Log.i(TAG, "Show contacts button pressed. Checking permissions.");
//
//        // Verify that all required contact permissions have been granted.
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Contacts permissions have not been granted.
//            Log.i(TAG, "Contact permissions has NOT been granted. Requesting permissions.");
//            requestContactsPermissions();
//
//        } else {
//
//            // Contact permissions have been granted. Show the contacts fragment.
//            Log.i(TAG,
//                    "Contact permissions have already been granted. Displaying contact details.");
//            showContactDetails();
//        }
//    }
//
//    /**
//     * Requests the Contacts permissions.
//     * If the permission has been denied previously, a SnackBar will prompt the user to grant the
//     * permission, otherwise it is requested directly.
//     */
//    private void requestContactsPermissions() {
//        // BEGIN_INCLUDE(contacts_permission_request)
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.READ_CONTACTS)
//                || ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.WRITE_CONTACTS)) {
//
//            // Provide an additional rationale to the user if the permission was not granted
//            // and the user would benefit from additional context for the use of the permission.
//            // For example, if the request has been denied previously.
//            Log.i(TAG,
//                    "Displaying contacts permission rationale to provide additional context.");
//
//            // Display a SnackBar with an explanation and a button to trigger the request.
//            Snackbar.make(mLayout, R.string.permission_contacts_rationale,
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction(R.string.ok, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            ActivityCompat
//                                    .requestPermissions(MainActivity.this, PERMISSIONS_CONTACT,
//                                            REQUEST_CONTACTS);
//                        }
//                    })
//                    .show();
//        } else {
//            // Contact permissions have not been granted yet. Request them directly.
//            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
//        }
//        // END_INCLUDE(contacts_permission_request)
//    }
//
//
//
//    private void showCameraPreview() {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.sample_content_fragment, CameraPreviewFragment.newInstance())
//                .addToBackStack("contacts")
//                .commit();
//    }
//
//
//    private void showContactDetails() {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.sample_content_fragment, ContactsFragment.newInstance())
//                .addToBackStack("contacts")
//                .commit();
//    }
//
//
//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_CAMERA) {
//            // BEGIN_INCLUDE(permission_result)
//            // Received permission result for camera permission.
//            Log.i(TAG, "Received response for Camera permission request.");
//
//            // Check if the only required permission has been granted
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Camera permission has been granted, preview can be displayed
//                Log.i(TAG, "CAMERA permission has now been granted. Showing preview.");
//                Snackbar.make(mLayout, R.string.permision_available_camera,
//                        Snackbar.LENGTH_SHORT).show();
//            } else {
//                Log.i(TAG, "CAMERA permission was NOT granted.");
//                Snackbar.make(mLayout, R.string.permissions_not_granted,
//                        Snackbar.LENGTH_SHORT).show();
//
//            }
//            // END_INCLUDE(permission_result)
//
//        } else if (requestCode == REQUEST_CONTACTS) {
//            Log.i(TAG, "Received response for contact permissions request.");
//
//            // We have requested multiple permissions for contacts, so all of them need to be
//            // checked.
//            if (PermissionUtil.verifyPermissions(grantResults)) {
//                // All required permissions have been granted, display contacts fragment.
//                Snackbar.make(mLayout, R.string.permision_available_contacts,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
//            } else {
//                Log.i(TAG, "Contacts permissions were NOT granted.");
//                Snackbar.make(mLayout, R.string.permissions_not_granted,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
//            }
//
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }

    /*****************安卓系统大于6.0动态获取权限部分结束**************/

}
