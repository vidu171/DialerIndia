package com.dialerindia.vidu.dialerindia;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class BaseActivity extends AppCompatActivity {

    Constants constants = new Constants();

    public void Toast(String Message){
        Toast.makeText(getApplicationContext(),Message,Toast.LENGTH_LONG).show();
    }

    public void setBackground(Context context){
        ImageView img = findViewById(R.id.background);
        Glide.with(context).load(R.drawable.back2).into(img);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
