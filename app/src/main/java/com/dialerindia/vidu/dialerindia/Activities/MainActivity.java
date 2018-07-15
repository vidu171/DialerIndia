package com.dialerindia.vidu.dialerindia.Activities;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.LinearLayout;

import com.dialerindia.vidu.dialerindia.DAO.FetchLeadsDAO;
import com.dialerindia.vidu.dialerindia.Fragments.MainPage;
import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    MainPage fragmentMainPage;


    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout sothreeLayout;

    @OnClick(R.id.myLeads)
    void myLeads() {
        showLoadingDialogOnUIThread(MainActivity.this, "Updating Leads");
        fetchLeads();
    }


    @OnClick(R.id.follow_up)
    void follow_up() {
        Intent I = new Intent(MainActivity.this, ScheduledLeadsActivity.class);
        startActivity(I);
    }

    @OnClick(R.id.Pending)
    void pending() {
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_PENDING);
        startActivity(I);

    }

    @OnClick(R.id.answered)
    void answered() {
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_ANSWERED);
        startActivity(I);
    }

    @OnClick(R.id.notAnswered)
    void not_answered() {
        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_NOT_ANSWERED);
        startActivity(I);
    }

    @OnClick(R.id.settings)
    void settings() {
        Intent I = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(I);
    }

    @OnClick(R.id.terms_and_condition_textview)
    void terms() {
        Intent I = new Intent(MainActivity.this, TermsAndConditionActivity.class);
        startActivity(I);
    }


    @BindView(R.id.toolbar_progress_bar)
    LinearLayout progressBarLayout;


    @OnClick(R.id.hamburger)
    public void slideUpToggle() {
        sothreeLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        /* Sets the main fragment of the page*/
        replaceFrag();

        isCallPermission();
        createNotificationChannel();
        checkNoitificationCAncelled();



        /*Setup of the navigation Menu */
    }


    public void replaceFrag() {
        fragmentMainPage = new MainPage();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fadein, R.anim.fadeout).replace(R.id.mainFragment, fragmentMainPage, "Dashboard");
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        if (sothreeLayout.getPanelState().equals(SlidingUpPanelLayout.PanelState.EXPANDED) || sothreeLayout.getPanelState().equals(SlidingUpPanelLayout.PanelState.ANCHORED)) {
            sothreeLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else
            super.onBackPressed();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void checkNoitificationCAncelled() {
        if (getIntent().getBooleanExtra(constants.INTENT_KEY_NOTIFICATION_CANCEL, false)) {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.cancel(PrefsHelper.readPrefInt(this, constants.PREF_NOTIFICATION_ID));
            PrefsHelper.writePrefBool(this, constants.PREF_AUTOMATIC_CALLING, false);
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

    public void fetchLeads() {
        String user = PrefsHelper.readPrefString(this, constants.PREF_USER_NAME);
        ListenableFuture<Boolean> FetchLeads = FetchLeadsDAO
                .getInstance()
                .FetchData(user);
        Futures.addCallback(FetchLeads, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean result) {
                hideLoadingDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                hideLoadingDialog();
            }
        });
//        Intent I = new Intent(MainActivity.this, MyLeadsActivity.class);
//        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_ALL);
//        startActivity(I);

    }


}
