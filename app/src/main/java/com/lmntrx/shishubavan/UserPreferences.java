package com.lmntrx.shishubavan;

import android.content.Context;
import android.content.SharedPreferences;

/***
 * Created by livin on 28/4/16.
 */

public class UserPreferences {

    private static final String USER_CHOICE = "USER_CHOICE";
    private static final String FIRST_OPEN = "FIRST_OPEN";
    private static final String IS_WARNING_ON = "IS_WARNING_ON";
    private static final String PREFERENCES_NAME = "ApplicationPrefs";

    public static Boolean isThisFirstOpen(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(FIRST_OPEN,true)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(FIRST_OPEN,false);
            editor.apply();
            return true;
        }else {
            return false;
        }
    }

    public static void writeUserChoice(int id, Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_CHOICE,id);
        editor.apply();
    }

    public static int readUserChoice(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(USER_CHOICE,-1);

    }

    public static Boolean isWarningOn(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_WARNING_ON,true);
    }


    public static void toggleWarningStatus(Context ctx, Boolean status){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_WARNING_ON,status);
        editor.apply();
    }


}
