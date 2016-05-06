package com.lmntrx.shishubavan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SelectNumbersActivity extends AppCompatActivity {

    ArrayList<NumbersListModel> arrayList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_numbers);
        String TYPE = null;
        switch (getIntent().getIntExtra("LONG_PRESSED_VIEW_ID",-1)){
            case R.id.card_ambulance:TYPE = Boss.TYPE_AMBULANCE;
                break;
            case R.id.card_animalAbuse:TYPE = Boss.TYPE_STRAY_DOGS;
                break;
            case R.id.card_childAbuse:TYPE = Boss.TYPE_CHILD_ABUSE;
                break;
            case R.id.card_firetruck:TYPE = Boss.TYPE_FIRETRUCK;
                break;
            case R.id.card_police:TYPE = Boss.TYPE_POLICE;
                break;
            case R.id.card_sexualAbuse:TYPE = Boss.TYPE_SEXUAL_ASSAULT;
                break;
            case R.id.card_shishubavan:TYPE = Boss.TYPE_SHISHUBAVAN;
                break;
        }
        if (TYPE!=null)
            arrayList = MotherOfDatabases.getAllPlaces(TYPE);
        listView = (ListView)findViewById(R.id.list);
        NumbersListAdaptor numbersListAdaptor = new NumbersListAdaptor(this,arrayList,TYPE);
        listView.setAdapter(numbersListAdaptor);
    }
}
