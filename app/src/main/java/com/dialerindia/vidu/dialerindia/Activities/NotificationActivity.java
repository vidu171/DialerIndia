package com.dialerindia.vidu.dialerindia.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dialerindia.vidu.dialerindia.R;

import butterknife.ButterKnife;

public class NotificationActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

    }
}
