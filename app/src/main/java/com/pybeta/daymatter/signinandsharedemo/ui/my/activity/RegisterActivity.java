package com.pybeta.daymatter.signinandsharedemo.ui.my.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.base.BaseActivity;
import com.pybeta.daymatter.signinandsharedemo.utils.RegexUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 注册界面
 * (注册和忘记密码用同一个界面)
 * Created by luogj on 2018/2/7.
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity{
    @ViewInject(R.id.tv_stateBar_title)
    TextView tv_stateBar_title;
    @ViewInject(R.id.et_phoneNumber)
    EditText et_phoneNumber;

    @ViewInject(R.id.et_verificationCode)
    EditText et_verificationCode;

    @ViewInject(R.id.tv_getVerificationCode)
    TextView tv_getVerificationCode;

    @ViewInject(R.id.et_password)
    EditText et_password;

    @ViewInject(R.id.et_confirmPassword)
    EditText et_confirmPassword;

    private Context context;
    private String phoneNumber;
    private String verificationCode;
    private String password;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        boolean isRegister = getIntent().hasExtra("register");
        if (isRegister){
            tv_stateBar_title.setText(R.string.register);
        }else {
            tv_stateBar_title.setText(R.string.forget_password);
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {
        et_phoneNumberListener();
        et_verificationCodeListener();
        et_passwordListener();
        et_confirmPasswordListener();
    }

    /**
     * 手机号码编辑框监听器
     */
    private void et_phoneNumberListener() {
        et_phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneNumber = s.toString().trim();
                if (phoneNumber.length() == 11){
                    boolean validate = RegexUtils.validate(RegexUtils.MOBILE_PATTERN, phoneNumber);
                    if (validate){
                        tv_getVerificationCode.setSelected(true);
                    }else {
                        tv_getVerificationCode.setSelected(false);
                    }
                }else {
                    tv_getVerificationCode.setSelected(false);
                }
            }
        });
    }

    /**
     * 验证码编辑框监听器
     */
    private void et_verificationCodeListener() {
        et_verificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                verificationCode = s.toString().trim();
            }
        });
    }

    /**
     * 密码编辑框监听器
     */
    private void et_passwordListener() {
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString().trim();

            }
        });
    }

    /**
     * 确认密码编辑框监听器
     */
    private void et_confirmPasswordListener() {
        et_confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmPassword = s.toString().trim();
            }
        });
    }

    @Event(value = {R.id.ib_register_back},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.ib_register_back:
                finish();
                break;
        }
    }
}
