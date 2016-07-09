package com.lmntrx.shishubavan;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("My prefs",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("visited",false);
        if (getResources().getBoolean(R.bool.SHOW_SPLASH) && UserPreferences.shouldShowSplash(this))
            UserPreferences.setShouldShowSplash(this,true);
        if(!sharedPreferences.getBoolean("visited",false) || (UserPreferences.shouldShowSplash(this))) {
            UserPreferences.setShouldShowSplash(this,false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                    editor.putBoolean("visited", true);
                    editor.apply();
                    finish();
                }
            }, 5000);
        }
        else
        {
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
