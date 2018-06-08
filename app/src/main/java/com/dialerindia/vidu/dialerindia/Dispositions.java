package com.dialerindia.vidu.dialerindia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.dialerindia.vidu.dialerindia.database.LeadsDBHelper;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dispositions extends BaseActivity{

    String Number;


    @BindView(R.id.callbackSwitch)
    Switch callbackSwitch;

    @BindView(R.id.callbacklayout)
    LinearLayout callbacklayout;

//    @BindView(R.id.busy)
//    Switch callbackSwitch;

    @BindView(R.id.answered)
    Switch answeredSwitch;


    @BindView(R.id.cancelautocall)
    LinearLayout cancelautocallButton;

    @BindView(R.id.disposition)
    LinearLayout nextbutton;


    @BindView(R.id.dispositiontxt)
    TextView dispositiontxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disposition);
        ButterKnife.bind(this);
        setBackground(this);
        checkPending();
        initialiseButton();
        setSwitchListener();
        Number = getIntent().getStringExtra(constants.INTENT_KEY_NUMBER);
    }

    private void checkPending() {
        LeadsDBHelper dbHelper = new LeadsDBHelper(this);
        if(!dbHelper.checkPending()){
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.cancel(PrefsHelper.readPrefInt(this,constants.PREF_NOTIFICATION_ID));
            PrefsHelper.writePrefBool(this,constants.PREF_AUTOMATIC_CALLING,false);
        }
    }

    private void setSwitchListener() {
        callbackSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    callbacklayout.setVisibility(View.VISIBLE);
                }
                else {
                    callbacklayout.setVisibility(View.GONE);
                }
            }
        });
    }

    public void initialiseButton(){
        Boolean automatic= PrefsHelper.readPrefBool(this,constants.PREF_AUTOMATIC_CALLING);
        Log.w("Button State", ""+automatic);
        if(automatic){

            dispositiontxt.setText("Call next");
            nextbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FillDisposition();
                    new AutomaticCall().makecall(Dispositions.this);
                }
            });
            cancelautocallButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FillDisposition();
                    Intent I = new Intent(Dispositions.this, MainActivity.class);
                    I.putExtra(constants.INTENT_KEY_NOTIFICATION_CANCEL, true);
                    startActivity(I);
                }
            });
        }
        else{
            cancelautocallButton.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,100);
            params.weight = 2.0f;
            nextbutton.setLayoutParams(params);
            dispositiontxt.setText("Done!");
            nextbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FillDisposition();
                    Intent I  = new Intent(Dispositions.this,MyLeadsActivity.class);
                    I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(I);
                }
            });
        }

    }

    public void FillDisposition(){

        if(!answeredSwitch.isChecked()){
            new LeadsDBHelper(this).setMissedState(PrefsHelper.readPrefInt(this,constants.PREF_NUMBER_ID));
        }
    }
}
