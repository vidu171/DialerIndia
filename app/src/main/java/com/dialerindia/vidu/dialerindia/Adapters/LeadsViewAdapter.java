package com.dialerindia.vidu.dialerindia.Adapters;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dialerindia.vidu.dialerindia.Activities.AutomaticCall;
import com.dialerindia.vidu.dialerindia.Constants.Constants;
import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.classes.Leads;
import com.dialerindia.vidu.dialerindia.helper.LeadsDBHelper;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class LeadsViewAdapter  extends RecyclerView.Adapter<LeadsViewAdapter.MyViewHolder>{
    private Context myContext;
    private ArrayList<Leads> myProvider;
    AlertDialog dialog;
    TextView date_txt,time_txt;
    Button bSetReminder;
    Constants constants = new Constants();

    private int mYear, mMonth, mDay, mHour, mMinute;
    private int fYear, fMonth, fDay, fHour, fMinute;



    public  LeadsViewAdapter(Context mContext, ArrayList<Leads> mProvider){
        this.myContext= mContext;
        this.myProvider = mProvider;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.lead_recycle_card,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if(position%2!=0){
            holder.ParentPanel.setBackgroundColor(myContext.getResources().getColor(R.color.bluewhite));
        }
        final Leads currentLead = myProvider.get(position);
        holder.Name.setText(currentLead.Name);
        holder.Contact.setText(currentLead.Contact1);
        holder.City.setText(currentLead.City);
        boolean pending = currentLead.Pending;
        boolean missed = currentLead.Missed;
        if(pending){
            holder.Status.setText("P");
        }
        else if (missed){
            holder.Status.setText("N");
        }
        else{
            holder.Status.setText("A");
        }

        initializeCalenderButtons(holder, currentLead);
    }

    @Override
    public int getItemCount() {
        return myProvider.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout ParentPanel;
        TextView Name,Contact,City, Status;
        ImageView CallNow, CallLater;
        MyViewHolder(View itemView) {
            super(itemView);
            ParentPanel = itemView.findViewById(R.id.parentPanel);
            Name = itemView.findViewById(R.id.Name);
            Contact = itemView.findViewById(R.id.Contact);
            City = itemView.findViewById(R.id.City);
            Status = itemView.findViewById(R.id.status);
            CallLater = itemView.findViewById(R.id.callLater);
            CallNow = itemView.findViewById(R.id.callNow);
        }

    }

    public void initializeCalenderButtons(MyViewHolder holder, final Leads lead){
        final Leads currentLead = lead;
        holder.CallNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AutomaticCall().makecallbyContact(myContext, currentLead.Contact1, currentLead.id);
            }
        });

        holder.CallLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) myContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                View alertLayout = inflater.inflate(R.layout.option_dialogue, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(myContext);
                alert.setView(alertLayout);
                dialog = alert.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                LinearLayout btn_date = alertLayout.findViewById(R.id.btn_date);
                LinearLayout btn_time = alertLayout.findViewById(R.id.btn_time);
                date_txt = alertLayout.findViewById(R.id.date_txt);
                time_txt = alertLayout.findViewById(R.id.time_txt);
                btn_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(myContext,
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
                });

                btn_time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(myContext,
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
                });


                bSetReminder = alertLayout.findViewById(R.id.bSetReminder);
                bSetReminder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FillDisposition(currentLead.id);
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void FillDisposition(int Id){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,fYear);
        calendar.set(Calendar.MONTH,fMonth);
        calendar.set(Calendar.DAY_OF_MONTH,fDay);
        calendar.set(Calendar.HOUR_OF_DAY,fHour);
        calendar.set(Calendar.MINUTE,fMinute);
        calendar.set(Calendar.SECOND,0);

            Log.w("time",""+calendar.getTimeInMillis());
            new LeadsDBHelper(myContext).setCallbackTIme(Id,calendar.getTimeInMillis());
            setScheduleNotification(calendar.getTimeInMillis(), Id);

    }

    public void setScheduleNotification(long timeinMillis, int Id){
        Log.w("setNotif", "Notif");
        Intent intent = new Intent("com.dialerIndia.notifaction_recieved");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(constants.INTENT_KEY_SCHEDULED_LEAD_ID, Id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(myContext, (int) System.currentTimeMillis(), intent, 0);
        AlarmManager alarmManager = (AlarmManager) myContext.getSystemService(Context.ALARM_SERVICE);
        long repeat= getRepeatTimeinMillis();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeinMillis,repeat, pendingIntent);
    }
    public long getRepeatTimeinMillis() {
        int position = PrefsHelper.readPrefInt(myContext,constants.PREF_SCHEDULED_REPEAT_TIME);
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
