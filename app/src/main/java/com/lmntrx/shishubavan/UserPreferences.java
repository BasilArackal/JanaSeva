package com.lmntrx.shishubavan;

import android.content.Context;
import android.content.SharedPreferences;

/***
 * Created by livin on 28/4/16.
 */

public class UserPreferences {

    public static Boolean isThisFirstOpen(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("ApplicationPrefs",Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("FIRST_OPEN",true)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("FIRST_OPEN",false);
            editor.apply();
            return true;
        }else {
            return false;
        }
    }


}
