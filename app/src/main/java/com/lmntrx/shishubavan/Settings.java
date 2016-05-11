package com.lmntrx.shishubavan;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton callAndSms, callonly, smsOnly;
    CheckBox warningOnStatus;
    String newPin;
    CheckBox childLockCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        callAndSms=(RadioButton)findViewById(R.id.callAndSmsRB);
        callonly=(RadioButton)findViewById(R.id.callOnlyRB);
        smsOnly=(RadioButton)findViewById(R.id.smsOnlyRB);
        warningOnStatus=(CheckBox) findViewById(R.id.warningCB);
        childLockCB = (CheckBox)findViewById(R.id.childLockCB);

        childLockCB.setChecked(UserPreferences.isChildLockEnabled(Settings.this));

        warningOnStatus.setChecked(UserPreferences.isWarningOn(Settings.this));

        warningOnStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserPreferences.toggleWarningStatus(Settings.this,isChecked);
            }
        });

        int selectedID = UserPreferences.readUserChoice(Settings.this);

        if (selectedID!=-1){
            RadioButton selectedRB = (RadioButton) findViewById(selectedID);
            if (selectedRB != null) {
                selectedRB.setChecked(true);
            }
        }else callonly.setChecked(true);


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

    public void childLockStatusChange(View view) {
        assert childLockCB != null;
        if (childLockCB.isChecked()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter a PIN Number");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            builder.setView(input);
            builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    newPin = input.getText().toString();
                    UserPreferences.setChildLockPin(Settings.this,newPin);
                    UserPreferences.setChildLock(Settings.this, childLockCB.isChecked());
                    Snackbar.make(childLockCB,"Enabled Child Lock",Snackbar.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("PIN");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            builder.setView(input);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    newPin = input.getText().toString();
                    if (newPin.equals(UserPreferences.getChildLockPin(Settings.this))){
                        UserPreferences.setChildLock(Settings.this, childLockCB.isChecked());
                        Snackbar.make(childLockCB,"Disabled Child Lock",Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

        }


    }
}
