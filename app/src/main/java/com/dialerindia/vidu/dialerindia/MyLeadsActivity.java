package com.dialerindia.vidu.dialerindia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dialerindia.vidu.dialerindia.Adapters.LeadsViewAdapter;
import com.dialerindia.vidu.dialerindia.database.LeadsDBHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyLeadsActivity extends BaseActivity {

    LeadsViewAdapter mAdapter;

    @BindView(R.id.recyclerview)
    RecyclerView myLeadsRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_leads_activity);
        ButterKnife.bind(this);

        mAdapter = new LeadsViewAdapter(this, new LeadsDBHelper(this).getAllLeadsFromSQL());
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        myLeadsRecyclerView.setLayoutManager(mLayoutManager);
        myLeadsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myLeadsRecyclerView.setAdapter(mAdapter);


    }

}
