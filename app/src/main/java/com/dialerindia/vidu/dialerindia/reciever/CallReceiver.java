package com.dialerindia.vidu.dialerindia.reciever;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.Constants.Constants;
import com.dialerindia.vidu.dialerindia.Activities.Dispositions;
import com.dialerindia.vidu.dialerindia.helper.LeadsDBHelper;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import java.util.Date;

public class CallReceiver  extends PhoneCallReciever{

    Constants constants = new Constants();
    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {

    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
    }

    @Override
    protected void onOutgoingCallEnded(Context context, String number, Date start, Date end) {
        Log.w("Reciever","Recieved call");
        if(PrefsHelper.readPrefString(context,constants.PREF_LAST_NUMBER)!=null && PrefsHelper.readPrefString(context,constants.PREF_LAST_NUMBER).equals(number.trim())) {
            PrefsHelper.writePrefString(context,constants.PREF_LAST_NUMBER, null);
            new LeadsDBHelper(context).setCalledState(PrefsHelper.readPrefInt(context,constants.PREF_LEAD_ID));
            Intent intent = new Intent(context, Dispositions.class);
//                         intent.putExtra(constants.INTENT_KEY_NUMBER,number.trim());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (PrefsHelper.readPrefBool(context, constants.PREF_AUTOMATIC_CALLING)) {
                intent.putExtra("automatic", true);
            } else {
                intent.putExtra("automatic", false);
            }
            context.startActivity(intent);
        }

    }

    @Override
    protected void onMissedCall(Context context, String number, Date start) {

    }



}
