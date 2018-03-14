package com.pybeta.daymatter.signinandsharedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.pybeta.daymatter.signinandsharedemo.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 启动页
 * Created by luogj on 2018/2/2.
 */
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {

    @ViewInject(R.id.tv_countDown_time)
    TextView tv_countDown_time;
    private Handler handler = new Handler();
    //启动页能展示的时间
    private int ui_show_time = 3;
    private Context context;

    private Runnable runnableRemind = new Runnable() {
        @Override
        public void run() {
            if (ui_show_time >= 0) {
                tv_countDown_time.setText(ui_show_time + " S");
                ui_show_time--;
                handler.postDelayed(runnableRemind, 1000);
            } else {

                goToMainView();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initWelcomeView();
    }

    /**
     * 初始化启动界面
     */
    private void initWelcomeView() {
        handler.post(runnableRemind);
    }

    @Event(value = {R.id.rl_jump},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.rl_jump:
                goToMainView();
                break;
        }
    }

    /**
     * 进入主界面
     */
    private void goToMainView(){
        handler.removeCallbacks(runnableRemind);
        Intent intent = new Intent(context,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
