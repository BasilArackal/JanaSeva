package com.lmntrx.shishubavan;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
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
        TextView defaultText = (TextView)findViewById(R.id.default_phoneNumber);
        assert defaultText != null;
        assert actionBar!=null;
        switch (getIntent().getIntExtra("LONG_PRESSED_VIEW_ID",-1)){
            case R.id.card_ambulance:
                TYPE = Boss.TYPE_AMBULANCE;
                actionBar.setTitle("Ambulances");
                defaultText.setText(getString(R.string.ambulance_default_number));
                break;
            case R.id.card_animalAbuse:
                TYPE = Boss.TYPE_STRAY_DOGS;
                actionBar.setTitle("Stray Dogs Control");
                defaultText.setText(getString(R.string.shishubavan_default_number));
                break;
            case R.id.card_childAbuse:
                TYPE = Boss.TYPE_CHILD_ABUSE;
                actionBar.setTitle("Child Helplines");
                defaultText.setText(getString(R.string.child_helpline_default_number));
                break;
            case R.id.card_firetruck:
                TYPE = Boss.TYPE_FIRETRUCK;
                actionBar.setTitle("FireForce Control Rooms");
                defaultText.setText(getString(R.string.fire_force_default_number));
                break;
            case R.id.card_police:
                TYPE = Boss.TYPE_POLICE;
                actionBar.setTitle("Police Control Rooms");
                defaultText.setText(getString(R.string.police_default_number));
                break;
            case R.id.card_sexualAbuse:
                TYPE = Boss.TYPE_SEXUAL_ASSAULT;
                actionBar.setTitle("Women Cell");
                defaultText.setText(getString(R.string.women_helpline_default_number));
                break;
            case R.id.card_shishubavan:
                TYPE = Boss.TYPE_SHISHUBAVAN;
                actionBar.setTitle("Janaseva Sisubhavan");
                defaultText.setText(getString(R.string.shishubavan_default_number));
                break;

            case R.id.card_vigilance:
                TYPE = Boss.TYPE_VIGILANCE;
                actionBar.setTitle("Vigilance");
                defaultText.setText(getString(R.string.vigilance_default_number));
                break;
            case R.id.card_excise:
                TYPE = Boss.TYPE_EXCISE;
                actionBar.setTitle("Excise");
                defaultText.setText(getString(R.string.excise_default_number));
                break;
            case R.id.card_blood_bank:
                TYPE = Boss.TYPE_BLOOD_BANKS;
                actionBar.setTitle("Blood Banks");
                defaultText.setText(getString(R.string.blood_bank_default_number));
                break;
            case R.id.card_highwaypolice:
                TYPE = Boss.TYPE_HIGHWAY;
                actionBar.setTitle("Highway Police");
                defaultText.setText(getString(R.string.highway_police_default_number));
                break;
            case R.id.card_railwaypolice:
                TYPE = Boss.TYPE_RAILWAY;
                actionBar.setTitle("Railway Police");
                defaultText.setText(getString(R.string.railway_alert_default_number));
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
        String list[] = MotherOfDatabases.getEnabledNumbers(TYPE,getApplicationContext());
        if (list.length<=5){
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
