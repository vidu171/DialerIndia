package com.dialerindia.vidu.dialerindia.services;

import android.content.Context;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.callrecord.CallRecord;
import com.dialerindia.vidu.dialerindia.callrecord.receiver.CallRecordReceiver;

import java.util.Date;

public class MyCallRecordReciever extends CallRecordReceiver {
    public MyCallRecordReciever(CallRecord callRecord) {
        super(callRecord);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        //callRecord.disableSaveFile();
        super.onOutgoingCallStarted(ctx, number, start);
    }
}
