package com.dialerindia.vidu.dialerindia;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    public void Toast(String Message){
        Toast.makeText(getApplicationContext(),Message,Toast.LENGTH_LONG).show();
    }
}
