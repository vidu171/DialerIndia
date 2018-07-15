package com.dialerindia.vidu.dialerindia.Fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dialerindia.vidu.dialerindia.Activities.MainActivity;
import com.dialerindia.vidu.dialerindia.Activities.MyLeadsActivity;
import com.dialerindia.vidu.dialerindia.Activities.NotificationActivity;
import com.dialerindia.vidu.dialerindia.Activities.ScheduledLeadsActivity;
import com.dialerindia.vidu.dialerindia.Constants.Constants;
import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainPage extends Fragment implements TextToSpeech.OnInitListener {

    Constants constants = new Constants();
    @OnClick(R.id.MyLeads)
    void myLeads(){

        Intent I = new Intent(getContext(), MyLeadsActivity.class);
        I.putExtra(constants.INTENT_KEY_TYPE, constants.LEADS_ALL);
        startActivity(I);
    }

    @OnClick(R.id.Scheduledleads_card)
    void scheduled(){
        Intent I = new Intent(getContext(), ScheduledLeadsActivity.class);
        startActivity(I);
    }

    @OnClick(R.id.notification_card)
    void notification(){
        Intent I = new Intent(getContext(), NotificationActivity.class);
        startActivity(I);
    }


    @BindView(R.id.welcome_user)
    TextView user_welcome_txt;

    View alertLayout;
    private GestureDetector gestureDetector;
    private TextToSpeech tts;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        gestureDetector = new GestureDetector(getActivity(), new SingleTapConfirm());
        Constants constants = new Constants();
        String Name  = PrefsHelper.readPrefString(getContext(), constants.PREF_USER_NAME);
        user_welcome_txt.setText("Hi "+ Name+"!");

        return v;


    }



    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale loc = new Locale("en", "IN");
            tts.setLanguage(loc);
            tts.setPitch(1.0f);
            tts.setSpeechRate(1.0f);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }



    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }



}
