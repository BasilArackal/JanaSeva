package com.lmntrx.shishubavan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class Pin extends Activity {

    EditText pinTXT;
    Animation shake;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        pinTXT = (EditText) findViewById(R.id.pinEntry);

        pinTXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pin = pinTXT.getText().toString();
                if (pin.length()==4){
                    verify(pin);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void verify(String pin) {
        String originalPin = UserPreferences.getChildLockPin(Pin.this);
        if (pin.equals(originalPin)){
            startActivity(new Intent(Pin.this,MainActivity.class));
            vibrator.vibrate(50);
            Pin.this.finish();
        }else {
            Snackbar.make(pinTXT,"Invalid Pin",Snackbar.LENGTH_SHORT);
            pinTXT.setText("");
            pinTXT.startAnimation(shake);
        }


    }
}
