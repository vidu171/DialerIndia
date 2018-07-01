package com.dialerindia.vidu.dialerindia.reciever;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.Constants.Constants;
import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.classes.Leads;
import com.dialerindia.vidu.dialerindia.helper.LeadsDBHelper;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

public class ScheduledNotification extends BroadcastReceiver{
    Constants constants = new Constants();
    @Override
    public void onReceive(Context context, Intent I) {
        Log.w("Notification", "recieved");

        Intent intent = new Intent(context, ScheduledNotification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(constants.INTENT_KEY_NOTIFICATION_CANCEL, true);
        intent.putExtra(constants.INTENT_KEY_SCHEDULED_LEAD_ID, I.getIntExtra(constants.INTENT_KEY_SCHEDULED_LEAD_ID, -1));
        Log.w("disposition value", ""+ I.getIntExtra(constants.INTENT_KEY_SCHEDULED_LEAD_ID,-1));
        int notification_id = (int) System.currentTimeMillis();
        LeadsDBHelper dbHelper = new LeadsDBHelper(context);
        Log.w("Okay",""+intent.getIntExtra(constants.INTENT_KEY_SCHEDULED_LEAD_ID,-1));
        Leads lead = dbHelper.getLeadByID(intent.getIntExtra(constants.INTENT_KEY_SCHEDULED_LEAD_ID, -1));
        if (lead != null) {
            PrefsHelper.writePrefInt(context, constants.PREF_SCHEDULED_NOTIFICATION_ID, notification_id);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, context.getString(R.string.channel_ID))
                    .setSmallIcon(R.drawable.ic_call_white)
                    .setContentTitle("Reminder Call: "+lead.Name)
                    .setContentText("Tap to continue")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notification_id, mBuilder.build());
        }
    }
}
