package com.dialerindia.vidu.dialerindia;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dialerindia.vidu.dialerindia.database.LeadsDBHelper;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dispositions extends BaseActivity{

    String Number;

    private int mYear, mMonth, mDay, mHour, mMinute;
    private int fYear, fMonth, fDay, fHour, fMinute;

    int Id;
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

    @BindView(R.id.date_txt)
    TextView date_txt;


    @BindView(R.id.time_txt)
    TextView time_txt;

    @OnClick(R.id.btn_date)
    public void onDateSelected(){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        fYear = year;
                        fMonth = monthOfYear;
                        fDay = dayOfMonth;
                        date_txt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.btn_time)
    public void onTImeSelected(){
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        fHour=hourOfDay;
                        fMinute=minute;
                        time_txt.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disposition);
        ButterKnife.bind(this);
        setBackground(this);
        checkPending();
        initialiseButton();
        setSwitchListener();
        Id=PrefsHelper.readPrefInt(this,constants.PREF_LEAD_ID);
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
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,mYear);
        c.set(Calendar.MONTH,mMonth);
        c.set(Calendar.DAY_OF_MONTH,mDay);
        c.set(Calendar.HOUR_OF_DAY,mHour);
        c.set(Calendar.MINUTE,mMinute);
        c.set(Calendar.SECOND,0);
        if(!answeredSwitch.isChecked()){
            new LeadsDBHelper(this).setMissedState(Id);
        }
        if(callbackSwitch.isChecked()){
            Log.w("time",""+c.getTimeInMillis());
            new LeadsDBHelper(this).setCallbackTIme(Id,c.getTimeInMillis());
        }
    }

    public void ScheduleNotification(){
        Intent intent = new Intent(this, ScheduledLeadsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(constants.INTENT_KEY_NOTIFICATION_CANCEL, true);
        intent.putExtra(constants.INTENT_KEY_SCHEDULED_ID, Id);
        int notification_id = (int) System.currentTimeMillis();
        PrefsHelper.writePrefInt(this, constants.PREF_SCHEDULED_NOTIFICATION_ID, notification_id);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, getString(R.string.channel_ID))
                .setSmallIcon(R.drawable.ic_call_white)
                .setContentTitle("Automatic call enabled")
                .setContentText("Please Tap here to disable automatic calling")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notification_id, mBuilder.build());

    }
}
