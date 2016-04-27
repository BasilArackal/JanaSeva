package com.lmntrx.shishubavan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MotherOfDatabases DB_HANDLER = new MotherOfDatabases(this);
        DB_HANDLER.createDatabase(MotherOfDatabases.NAME_POLICE_DB,MotherOfDatabases.NAME_POLICE_TABLE);
    }
}
