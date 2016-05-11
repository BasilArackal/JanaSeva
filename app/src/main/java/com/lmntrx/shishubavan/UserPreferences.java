package com.lmntrx.shishubavan;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/***
 * Created by livin on 28/4/16.
 */

public class UserPreferences {

    private static final String USER_CHOICE = "USER_CHOICE";
    private static final String FIRST_OPEN = "FIRST_OPEN";
    private static final String IS_WARNING_ON = "IS_WARNING_ON";
    private static final String DATABASE_VERSION = "DATABASE_VERSION";
    private static final String SHOW_SPLASH = "SHOW_SPLASH";
    private static final String CHILD_LOCK_ENABLED = "CHILD_LOCK_ENABLED";
    private static final String SAVED_PIN = "SAVED_PIN";
    private static final String FIRST_OPEN_AFTER_UPDATE_1_5_3 = "FIRST_OPEN_AFTER_UPDATE_1_5_3";
    private static final String PREFERENCES_NAME = "ApplicationPrefs";
    private static final String PREFERENCES_PIN = "PinPrefs";


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


    public static void updateDBVersion(Context ctx, int version){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(DATABASE_VERSION,version);
        editor.apply();
    }

    public static int getCurrentDBVersion(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(DATABASE_VERSION,-1);
    }

    public static Boolean shouldShowSplash(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(SHOW_SPLASH,true);
    }

    public static void setShouldShowSplash(Context ctx, Boolean status){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SHOW_SPLASH,status);
        editor.apply();
    }

    public static boolean isChildLockEnabled(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_PIN,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(CHILD_LOCK_ENABLED,false);
    }

    public static void setChildLock(Context ctx, Boolean enabled){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_PIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CHILD_LOCK_ENABLED,enabled);
        editor.apply();
    }

    public static String getChildLockPin(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_PIN,Context.MODE_PRIVATE);
        return sharedPreferences.getString(SAVED_PIN,null);
    }

    public static void setChildLockPin(Context ctx, String newPin ){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_PIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_PIN,newPin);
        editor.apply();
    }

    public static void clearAllPrefs(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFERENCES_PIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        sharedPreferences = ctx.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear().apply();
    }

    public static boolean isThisFirstOpenAfterUpdate1_5_3(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("Main_Prefs",Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(FIRST_OPEN_AFTER_UPDATE_1_5_3,true)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(FIRST_OPEN_AFTER_UPDATE_1_5_3,false);
            editor.apply();
            return true;
        }else {
            return false;
        }
    }



}
