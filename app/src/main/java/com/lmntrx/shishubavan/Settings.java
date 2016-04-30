package com.lmntrx.shishubavan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton callAndSms, callonly, smsOnly;
    CheckBox warningOnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        callAndSms=(RadioButton)findViewById(R.id.callAndSmsRB);
        callonly=(RadioButton)findViewById(R.id.callOnlyRB);
        smsOnly=(RadioButton)findViewById(R.id.smsOnlyRB);
        warningOnStatus=(CheckBox) findViewById(R.id.warningCB);

        warningOnStatus.setChecked(UserPreferences.isWarningOn(Settings.this));

        warningOnStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserPreferences.toggleWarningStatus(Settings.this,isChecked);
            }
        });

        int seletedID = UserPreferences.readUserChoice(Settings.this);

        if (seletedID!=-1){
            RadioButton selectedRB = (RadioButton) findViewById(seletedID);
            if (selectedRB != null) {
                selectedRB.setChecked(true);
            }
        }else callAndSms.setChecked(true);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.callAndSmsRB:
                        if (callAndSms.isChecked())
                        UserPreferences.writeUserChoice(R.id.callAndSmsRB,Settings.this);
                        break;
                    case R.id.callOnlyRB:
                        if (callonly.isChecked())
                            UserPreferences.writeUserChoice(R.id.callOnlyRB,Settings.this);
                        break;
                    case R.id.smsOnlyRB:
                        if (smsOnly.isChecked())
                            UserPreferences.writeUserChoice(R.id.smsOnlyRB,Settings.this);
                        break;
                }
            }
        });
    }
}
