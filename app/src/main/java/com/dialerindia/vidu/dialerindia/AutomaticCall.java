package com.dialerindia.vidu.dialerindia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.classes.Leads;
import com.dialerindia.vidu.dialerindia.database.LeadsDBHelper;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

public class AutomaticCall {

    public static final String ACTION_IN = "android.intent.action.PHONE_STATE";
    public static final String ACTION_OUT = "android.intent.action.NEW_OUTGOING_CALL";
    Constants constants = new Constants();
    @SuppressLint("MissingPermission")
    public void makecall(Context myContext) {
        Leads firstUncalledLead = new LeadsDBHelper(myContext).getFirstUncalledLead();

        if (firstUncalledLead != null) {
            PrefsHelper.writePrefString(myContext,constants.PREF_LAST_NUMBER,firstUncalledLead.Contact1);
            PrefsHelper.writePrefInt(myContext,constants.PREF_LEAD_ID,firstUncalledLead.id);
            Log.w("CAll Made to","" + firstUncalledLead.id);
            String Number = firstUncalledLead.Contact1;
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Number));

            myContext.startActivity(intent);
        }
    }

    @SuppressLint("MissingPermission")
    public void makecallbyContact(Context myContext, String Contact, int id) {
        Log.w("Service","called");
        if (Contact != null) {
            PrefsHelper.writePrefString(myContext,"LatestNumber",Contact.trim());
            PrefsHelper.writePrefInt(myContext,constants.PREF_LEAD_ID,id);
            Log.w("CAll Made to","" + id);
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Contact));
            myContext.startActivity(intent);
        }
    }

}
