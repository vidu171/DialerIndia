package com.dialerindia.vidu.dialerindia.reciever;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.Constants.Constants;
import com.dialerindia.vidu.dialerindia.classes.Leads;
import com.dialerindia.vidu.dialerindia.helper.LeadsDBHelper;

import java.util.ArrayList;

public class OnBoot extends BroadcastReceiver {
    Constants constants = new Constants();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w("Reciever", "OnBoot");
        LeadsDBHelper dbHelper = new LeadsDBHelper(context);
        ArrayList<Leads> listScheduledLeads = dbHelper.getScheduledLeadsFromSQL();
        for(Leads lead : listScheduledLeads){
            ScheduleIIntent(lead.id,context,lead.CallbackTime);
        }
    }

    public void ScheduleIIntent(int Id, Context context, long timeinMillis){
        Intent intent = new Intent("com.dialerIndia.notifaction_recieved");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(constants.INTENT_KEY_SCHEDULED_LEAD_ID, Id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis() + Id, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeinMillis, pendingIntent);
    }

}
