package com.dialerindia.vidu.dialerindia;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dialerindia.vidu.dialerindia.callrecord.CallRecord;
import com.dialerindia.vidu.dialerindia.classes.Leads;
import com.dialerindia.vidu.dialerindia.database.LeadsDBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddLeadsActivity extends BaseActivity {

    @BindView(R.id.eName)
    EditText eName;

    @BindView(R.id.eContact1)
    EditText eContact1;

    @BindView(R.id.eContact2)
    EditText eContact2;

    @BindView(R.id.eEmail)
    EditText eEmail;

    @BindView(R.id.eAddress)
    EditText eAddress;

    @BindView(R.id.eCity)
    EditText eCity;


    @BindView(R.id.eGroup)
    EditText eGroup;

    @OnClick(R.id.bAddLeads)
    public void AddLeads(){
        if(Check(eName.getText().toString(), eContact1.getText().toString(), eContact2.getText().toString(),eEmail.getText().toString(),eAddress.getText().toString(), eCity.getText().toString(), eGroup.getText().toString())){
            Leads createLead = new Leads(eName.getText().toString(), eContact1.getText().toString(), eContact2.getText().toString(),eEmail.getText().toString(),eAddress.getText().toString(), eCity.getText().toString(), eGroup.getText().toString());
            LeadsDBHelper dbHelper = new LeadsDBHelper(this);
            dbHelper.AddData(createLead);
            Intent I = new Intent(this,MyLeadsActivity.class);
            I.putExtra(constants.INTENT_KEY_TYPE,constants.LEADS_ALL);
            startActivity(I);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leads);
        ButterKnife.bind(this);

//        setBackground(this);
    }



    public Boolean Check(String Name, String Contact1, String Contact2, String Email, String Address, String City, String Group){
        if(TextUtils.isEmpty(Name))
        {
            eName.setError("Name cannot be left empty");
            return false;
        }

        else
        if (TextUtils.isEmpty(Contact1)){
            eContact1.setError("Contact 1 cannot be left empty");
            return false;
        }



        else
        if (TextUtils.isEmpty(City)){
            eCity.setError("City cannot be left empty");
            return false;
        }


        else
        if (TextUtils.isEmpty(Group)){
            eGroup.setError("Group cannot be left empty");
            return false;
        }

        else {return true;}

    }


}
