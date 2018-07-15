package com.dialerindia.vidu.dialerindia.Activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.helper.LeadsDBHelper;
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
        Log.w("Dispostiotin ",""+Id);
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
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,110);
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,fYear    );
        calendar.set(Calendar.MONTH,fMonth);
        calendar.set(Calendar.DAY_OF_MONTH,fDay);
        calendar.set(Calendar.HOUR_OF_DAY,fHour);
        calendar.set(Calendar.MINUTE,fMinute);
        calendar.set(Calendar.SECOND,0);
        if(!answeredSwitch.isChecked()){
            new LeadsDBHelper(this).setMissedState(Id);
        };

        if(callbackSwitch.isChecked()){
            Log.w("time",""+calendar.getTimeInMillis());
            new LeadsDBHelper(this).setCallbackTIme(Id,calendar.getTimeInMillis());
            setScheduleNotification(calendar.getTimeInMillis());
        }
        else{
            new LeadsDBHelper(this).setCallbackTIme(Id,0);
        }

    }

    public void setScheduleNotification(long timeinMillis){

        Intent intent = new Intent("com.dialerIndia.notifaction_recieved");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(constants.INTENT_KEY_SCHEDULED_LEAD_ID, Id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,(int) System.currentTimeMillis(), intent, 0);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        long repeat = getRepeatTimeinMillis();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeinMillis, repeat, pendingIntent);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, timeinMillis, pendingIntent);
    }

    public long getRepeatTimeinMillis() {
        int position = PrefsHelper.readPrefInt(this,constants.PREF_SCHEDULED_REPEAT_TIME);
        if(position == constants.MIN_15)
            return  900000;
        if(position == constants.MIN_20)
            return 1200000;
        if(position == constants.MIN_30)
            return 1800000;
        if(position == constants.MIN_45)
            return 2700000;

        return 3600000;

    }
}
