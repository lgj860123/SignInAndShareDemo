package com.pybeta.daymatter.signinandsharedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 此界面是用来过度启动应用时出现空白，或者黑屏
 * Created by luogj on 2018/2/5.
 */

public class OverActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
