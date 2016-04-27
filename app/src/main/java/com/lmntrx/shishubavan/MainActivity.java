package com.lmntrx.shishubavan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {

    MotherOfDatabases DB_HANDLER = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_HANDLER = new MotherOfDatabases(this);
        DB_HANDLER.createDatabase(MotherOfDatabases.NAME_POLICE_TABLE);
    }



    public void addButton(View view)
    {
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        ImageView dangerButton=new ImageView(this);
        dangerButton.setImageResource(R.drawable.danger);
        dangerButton.setLayoutParams(new ActionBar.LayoutParams(300,300));
        gridLayout.addView(dangerButton);

    }

    @Override
    protected void onDestroy() {
        DB_HANDLER.closeDB();
        super.onDestroy();
    }
}
