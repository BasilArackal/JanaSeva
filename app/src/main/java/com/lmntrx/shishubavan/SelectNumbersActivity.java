package com.lmntrx.shishubavan;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectNumbersActivity extends AppCompatActivity {

    ArrayList<NumbersListModel> arrayList;
    ListView listView;
    String TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_select_numbers);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar!=null;
        switch (getIntent().getIntExtra("LONG_PRESSED_VIEW_ID",-1)){
            case R.id.card_ambulance:
                TYPE = Boss.TYPE_AMBULANCE;
                actionBar.setTitle("Ambulances");
                break;
            case R.id.card_animalAbuse:
                TYPE = Boss.TYPE_STRAY_DOGS;
                actionBar.setTitle("Stray Dogs Control");
                break;
            case R.id.card_childAbuse:
                TYPE = Boss.TYPE_CHILD_ABUSE;
                actionBar.setTitle("Child Helplines");
                break;
            case R.id.card_firetruck:
                TYPE = Boss.TYPE_FIRETRUCK;
                actionBar.setTitle("FireForce Control Rooms");
                break;
            case R.id.card_police:
                TYPE = Boss.TYPE_POLICE;
                actionBar.setTitle("Police Control Rooms");
                break;
            case R.id.card_sexualAbuse:
                TYPE = Boss.TYPE_SEXUAL_ASSAULT;
                actionBar.setTitle("Women Cell");
                break;
            case R.id.card_shishubavan:
                TYPE = Boss.TYPE_SHISHUBAVAN;
                actionBar.setTitle("Janaseva Sisubhavan");
                break;
        }
        if (TYPE!=null)
            arrayList = MotherOfDatabases.getAllPlaces(TYPE);
        listView = (ListView)findViewById(R.id.list);
        NumbersListAdaptor numbersListAdaptor = new NumbersListAdaptor(this,arrayList,TYPE);
        listView.setAdapter(numbersListAdaptor);
    }

    public void checkBoxChecked(View view) {
        CheckBox checkBox = (CheckBox)view;
        String list[] = MotherOfDatabases.getEnabledNumbers(TYPE);
        if (list.length<5){
            MotherOfDatabases.UpdateItem(TYPE,checkBox.getText().toString(),checkBox.isChecked());
        }else {
            if (checkBox.isChecked()) {
                checkBox.toggle();
                Toast.makeText(SelectNumbersActivity.this, "Only 5 numbers can be chosen!", Toast.LENGTH_SHORT).show();
            }else{
                if (list.length>1){
                    MotherOfDatabases.UpdateItem(TYPE,checkBox.getText().toString(),checkBox.isChecked());
                }else {
                    checkBox.toggle();
                    Toast.makeText(SelectNumbersActivity.this, "Choose one number at least!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}
