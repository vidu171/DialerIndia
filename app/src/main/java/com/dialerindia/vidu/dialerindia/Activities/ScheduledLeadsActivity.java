package com.dialerindia.vidu.dialerindia.Activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.dialerindia.vidu.dialerindia.Adapters.ScheduledLeadsViewAdapter;
import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.helper.LeadsDBHelper;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScheduledLeadsActivity extends BaseActivity {

    public static final String ACTION_IN = "android.intent.action.PHONE_STATE";
    public static final String ACTION_OUT = "android.intent.action.NEW_OUTGOING_CALL";



    ScheduledLeadsViewAdapter mAdapter;

    AutomaticCall automaticCall = new AutomaticCall();

    @BindView(R.id.recyclerview)
    RecyclerView myLeadsRecyclerView;

    @BindView(R.id.automaticCalling)
    Button automaticbutton;

    @OnClick(R.id.automaticCalling)
    public void AutomaticCalling() {
            PrefsHelper.writePrefBool(this, constants.PREF_AUTOMATIC_CALLING, true);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(constants.INTENT_KEY_NOTIFICATION_CANCEL, true);
            int notification_id = (int) System.currentTimeMillis();
            PrefsHelper.writePrefInt(this, constants.PREF_NOTIFICATION_ID, notification_id);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, getString(R.string.channel_ID))
                    .setSmallIcon(R.drawable.ic_call)
                    .setContentTitle("Automatic call enabled")
                    .setContentText("Please Tap here to disable automatic calling")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setOngoing(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(notification_id, mBuilder.build());
            automaticCall.makecall(this);
        }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_leads_activity);
        ButterKnife.bind(this);

        setBackground(this);
        initiateAdapter();
        checkPending();

    }

    private void checkPending() {
        LeadsDBHelper dbHelper = new LeadsDBHelper(this);
        if (!dbHelper.checkPending()) {
            automaticbutton.setText("No Pending calls Add lead");
            automaticbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ScheduledLeadsActivity.this, AddLeadsActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


    public void initiateAdapter() {
            mAdapter = new ScheduledLeadsViewAdapter(this, new LeadsDBHelper(this).getScheduledLeadsFromSQL());
            final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            myLeadsRecyclerView.setLayoutManager(mLayoutManager);
            myLeadsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            myLeadsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
