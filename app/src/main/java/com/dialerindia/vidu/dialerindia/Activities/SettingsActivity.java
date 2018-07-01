package com.dialerindia.vidu.dialerindia.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dialerindia.vidu.dialerindia.R;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity{
    @BindView(R.id.spinnerrecordingFormat)
    Spinner RecordingFormat;


    @BindView(R.id.spinnercallbackfrequency)
    Spinner AlertFrequency;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);


        String[] datos = {"mp3","amr"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        RecordingFormat.setAdapter(adaptador);

        int repeat  = PrefsHelper.readPrefInt(this,constants.PREF_SCHEDULED_REPEAT_TIME);


        String[] datos2 = {"15 minutes","20 minutes", "30 minutes", "45 minutes", "1 hour"};

        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos2);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        AlertFrequency.setAdapter(adaptador2);
        AlertFrequency.setSelection(repeat);
        AlertFrequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PrefsHelper.writePrefInt(SettingsActivity.this,constants.PREF_SCHEDULED_REPEAT_TIME, position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }
}
