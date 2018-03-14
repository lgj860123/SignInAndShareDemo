package com.pybeta.daymatter.signinandsharedemo.ui.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 登录界面
 * Created by luogj on 2018/2/7.
 */
@ContentView(R.layout.activity_sign_in)
public class SignInActivity extends BaseActivity {
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_password)
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        et_phoneListener();
        et_passwordListener();
    }

    /**
     * 初始化界面
     */
    private void initView() {

    }

    /**
     * 手机号码编辑框监听器
     */
    private void et_phoneListener() {
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 密码编辑框监听器
     */
    private void et_passwordListener() {
        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Event(value = {R.id.ib_back,R.id.tv_signIn,R.id.tv_forgetPassword,R.id.tv_register,
    R.id.ib_signIn_WX,R.id.ib_signIn_QQ,R.id.ib_signIn_WB},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.ib_back:
                finish();
                break;
            case R.id.tv_signIn:
                signIn();
                break;
            case R.id.tv_forgetPassword:
                goToForgetPasswordView();
                break;
            case R.id.tv_register:
                goToRegisterView();
                break;
            case R.id.ib_signIn_QQ:
                signInByQQ();
                break;
            case R.id.ib_signIn_WX:
                signInByWX();
                break;
            case R.id.ib_signIn_WB:
                signInByWB();
                break;
        }

    }

    /**
     * 登录
     */
    private void signIn() {

    }

    /**
     * 进入忘记密码界面
     */
    private void goToForgetPasswordView() {

    }

    /**
     * 进入注册界面
     */
    private void goToRegisterView() {
        Intent intent_register = new Intent(activity,RegisterActivity.class);
        startActivity(intent_register);
    }

    /**
     * 使用QQ登录
     */
    private void signInByQQ() {

    }

    /**
     * 使用微信登录
     */
    private void signInByWX() {

    }

    /**
     * 使用微博登录
     */
    private void signInByWB() {

    }
}
