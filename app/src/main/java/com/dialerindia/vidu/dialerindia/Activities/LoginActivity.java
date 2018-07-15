package com.dialerindia.vidu.dialerindia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.dialerindia.vidu.dialerindia.DAO.LoginDAO;
import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.username)
    EditText username_edt_txt;

    @BindView(R.id.Password)
    EditText password_edit_text;

    @OnClick(R.id.Button_Login)
    public void Login() {
        showLoadingDialog(this, "Trying to Log you in");
        String username = username_edt_txt.getText().toString().trim();
        String pass = password_edit_text.getText().toString().trim();
        if (username.isEmpty() || pass.isEmpty()) {
            Toast("Fields can not be empty");
            hideLoadingDialog();
            return;
        }
        Future<Boolean> login = LoginDAO
                .getInstance()
                .tryLogin(username, pass, LoginActivity.this);
        while (!login.isDone()) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            boolean loginsuccess = login.get();
            if (loginsuccess) {
                hideLoadingDialog();
                Intent I = new Intent(this, MainActivity.class);
                startActivity(I);
            } else {
                hideLoadingDialog();
                Toast("Please check the username and password, Make sure you are connected to the internet");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast("Please check the username and password, Make sure you are connected to the internet");
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast("Please check the username and password, Make sure you are connected to the internet");
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (PrefsHelper.readPrefString(this, constants.PREF_USER_ID) != null) {
            Intent I = new Intent(this, MainActivity.class);
            startActivity(I);
        }
    }
}
