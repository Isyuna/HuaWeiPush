package com.zhy.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.zhy.common.utils.ToastUtil;

public class LoginActivity extends AppCompatActivity {
    LoginFactory factory = new LoginFactory();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factory.createLogin(LoginType.ACCOUNT)
                        .login("account", "password",
                                new LoginCallBack() {
                                    @Override
                                    public void success(String data) {
                                        ToastUtil.showShort(LoginActivity.this, "成功");
                                    }

                                    @Override
                                    public void error(String error) {

                                    }
                                });
            }
        });
        findViewById(R.id.btn_face).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factory.createLogin(LoginType.FACE)
                        .loginByFace("image", new LoginByFaceCallBack() {
                            @Override
                            public void success(String data) {

                            }

                            @Override
                            public void error(String error) {

                            }
                        });
            }
        });
    }
}