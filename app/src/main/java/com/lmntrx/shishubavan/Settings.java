package com.lmntrx.shishubavan;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton callAndSms, callOnly, smsOnly;
    CheckBox warningOnStatus;
    String newPin;
    CheckBox childLockCB;
    Boolean isEnough = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        callAndSms=(RadioButton)findViewById(R.id.callAndSmsRB);
        callOnly =(RadioButton)findViewById(R.id.callOnlyRB);
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
        }else callOnly.setChecked(true);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.callAndSmsRB:
                        if (callAndSms.isChecked())
                        UserPreferences.writeUserChoice(R.id.callAndSmsRB,Settings.this);
                        break;
                    case R.id.callOnlyRB:
                        if (callOnly.isChecked())
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
            builder.setTitle("Enter 4 digit PIN");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            input.setMaxLines(1);
            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length()>4){
                        input.setText(s.subSequence(0,4));
                    }else isEnough = s.length() == 4;
                    input.setSelection(input.getText().length());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if (UserPreferences.getChildLockPin(this)!=null)
                input.setText(UserPreferences.getChildLockPin(this));
            builder.setView(input);
            builder.setCancelable(false);
            builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isEnough){
                        newPin = input.getText().toString();
                        UserPreferences.setChildLockPin(Settings.this,newPin);
                        UserPreferences.setChildLock(Settings.this, childLockCB.isChecked());
                        Snackbar.make(childLockCB,"Enabled Child Lock",Snackbar.LENGTH_SHORT).show();
                        isEnough = false;
                    }else {
                        Toast.makeText(Settings.this,"Please, Enter a 4 Digit PIN",Toast.LENGTH_LONG).show();
                        childLockCB.toggle();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    childLockCB.toggle();
                    dialog.cancel();
                }
            });
            builder.show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("PIN");
            builder.setCancelable(false);
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            input.setMaxLines(1);
            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length()>4){
                        input.setText(s.subSequence(0,4));
                        input.setSelection(input.getText().length());
                        input.startAnimation(AnimationUtils.loadAnimation(Settings.this,R.anim.shake));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            builder.setView(input);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        newPin = input.getText().toString();
                        if (newPin.equals(UserPreferences.getChildLockPin(Settings.this))){
                            UserPreferences.setChildLock(Settings.this, childLockCB.isChecked());
                            Snackbar.make(childLockCB,"Disabled Child Lock",Snackbar.LENGTH_SHORT).show();
                        }else {
                            Snackbar.make(childLockCB,"Wrong PIN",Snackbar.LENGTH_SHORT).show();
                            childLockCB.toggle();
                        }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    childLockCB.toggle();
                    dialog.cancel();
                }
            });
            builder.show();

        }


    }
}
