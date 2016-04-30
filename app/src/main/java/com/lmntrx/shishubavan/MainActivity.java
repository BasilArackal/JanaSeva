package com.lmntrx.shishubavan;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {

    MotherOfDatabases DB_HANDLER = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_HANDLER = new MotherOfDatabases(this);
        if (UserPreferences.isThisFirstOpen(this)){
            DB_HANDLER.createTable(MotherOfDatabases.NAME_POLICE_TABLE);
            DB_HANDLER.createTable(MotherOfDatabases.NAME_AMBULANCE_TABLE);
            DB_HANDLER.createTable(MotherOfDatabases.NAME_FIRE_FORCE_TABLE);
            DB_HANDLER.addRowsTo(MotherOfDatabases.NAME_FIRE_FORCE_TABLE);
            DB_HANDLER.addRowsTo(MotherOfDatabases.NAME_POLICE_TABLE);
            DB_HANDLER.addRowsTo(MotherOfDatabases.NAME_AMBULANCE_TABLE);
        }
    }




    public void addButton(final View view) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        ImageView dangerButton = new ImageView(this);
        dangerButton.setImageResource(R.drawable.danger);
        dangerButton.setLayoutParams(new ActionBar.LayoutParams(300, 300));
        dangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(v);
            }
        });
        gridLayout.addView(dangerButton);
    }

    public void makeCall(View view) {
        Boss.call(view.getId(),MainActivity.this);
    }

    @Override
    protected void onDestroy() {
        DB_HANDLER.closeDB();
        super.onDestroy();
    }
}
