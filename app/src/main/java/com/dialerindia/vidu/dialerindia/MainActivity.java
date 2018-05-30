package com.dialerindia.vidu.dialerindia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @OnClick(R.id.AddLeads)
    public void Addleads(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }

    @OnClick(R.id.MyLeads)
    public void MyLeads(){
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
        startActivity(I);
    }


    @OnClick(R.id.FollowUp)
    public void FollowUp(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }


    @OnClick(R.id.answered)
    public void Answered(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }


    @OnClick(R.id.notAnswered)
    public void notAnswered(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }


    @OnClick(R.id.Pending)
    public void Pending(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }

    @OnClick(R.id.profile)
    public void Profile(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }


    @OnClick(R.id.Notifications)
    public void Notifications(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }

    @OnClick(R.id.settings)
    public void Settings(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }

    @OnClick(R.id.termsAndConnection)
    public void termsAndCondition(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}
