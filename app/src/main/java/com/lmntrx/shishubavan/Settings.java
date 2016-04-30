package com.lmntrx.shishubavan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void radio(View view)
    {
        final RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        final RadioButton callAndSmsButton=(RadioButton)findViewById(R.id.radioButton);
        callAndSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=1;
            }
        });
        RadioButton callOnlyButton=(RadioButton)findViewById(R.id.radioButton2);
        callOnlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectdId=2;
            }
        });

    }
}
