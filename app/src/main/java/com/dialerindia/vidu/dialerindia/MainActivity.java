package com.dialerindia.vidu.dialerindia;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.ImageView;

import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    Constants constants = new Constants();
    @OnClick(R.id.AddLeads)
    public void Addleads(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
        startActivity(I);
    }

    @OnClick(R.id.MyLeads)
    public void MyLeads(){
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_ALL);
        startActivity(I);
    }


    @OnClick(R.id.FollowUp)
    public void FollowUp(){
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
//        startActivity(I);
    }


    @OnClick(R.id.answered)
    public void Answered(){
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_ANSWERED);
        startActivity(I);
    }


    @OnClick(R.id.notAnswered)
    public void notAnswered(){
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_NOT_ANSWERED);
        startActivity(I);
    }


    @OnClick(R.id.Pending)
    public void Pending(){
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_PENDING);
        startActivity(I);
    }

    @OnClick(R.id.profile)
    public void Profile(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
//        startActivity(I);
    }


    @OnClick(R.id.Notifications)
    public void Notifications(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
//        startActivity(I);
    }

    @OnClick(R.id.settings)
    public void Settings(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
//        startActivity(I);
    }

    @OnClick(R.id.termsAndConnection)
    public void termsAndCondition(){
        Intent I = new Intent(MainActivity.this, AddLeadsActivity.class);
//        startActivity(I);
    }

    @BindView(R.id.background)
    ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        isCallPermission();
        createNotificationChannel();
        setBackground(this);
        checkNoitificationCAncelled();

    }

    private void checkNoitificationCAncelled() {
        if(getIntent().getBooleanExtra(constants.INTENT_KEY_NOTIFICATION_CANCEL,false)){
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.cancel(PrefsHelper.readPrefInt(this,constants.PREF_NOTIFICATION_ID));
            PrefsHelper.writePrefBool(this,constants.PREF_AUTOMATIC_CALLING,false);
        }
    }

    public void isCallPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_ID), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
