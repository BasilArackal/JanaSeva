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
    private static final String PREFERENCES_POLICE_NUMBERS = "PolicePrefs";
    private static final String PREFERENCES_FIRE_FORCE_NUMBERS = "FireForcePrefs";
    private static final String PREFERENCES_AMBULANCE_NUMBERS = "AmbulancePrefs";
    private static final String PREFERENCES_STRAY_DOGS_NUMBERS = "StrayDogsPrefs";
    private static final String PREFERENCES_SEXUAL_ASSAULT_NUMBERS = "SexualAssaultPrefs";
    private static final String PREFERENCES_CHILD_ABUSE_NUMBERS = "ChildAbusePrefs";
    private static final String PREFERENCES_JANASEVA_NUMBERS = "JanasevaPrefs";

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

    public static void updatePhoneNumbers(Context ctx,int key,String PlaceName, Boolean enabled, String TYPE){

        SharedPreferences sharedPreferences = null;

        switch (TYPE){
            case PREFERENCES_POLICE_NUMBERS:sharedPreferences = ctx.getSharedPreferences(PREFERENCES_POLICE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_AMBULANCE_NUMBERS:sharedPreferences = ctx.getSharedPreferences(PREFERENCES_AMBULANCE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_CHILD_ABUSE_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_CHILD_ABUSE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_FIRE_FORCE_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_FIRE_FORCE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_JANASEVA_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_JANASEVA_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_SEXUAL_ASSAULT_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_SEXUAL_ASSAULT_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_STRAY_DOGS_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_STRAY_DOGS_NUMBERS,Context.MODE_PRIVATE);
                break;
        }
        if (sharedPreferences!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key+"",PlaceName+":"+enabled);
            editor.apply();
        }
    }

    public static String readPhoneNumbers(Context ctx, int key, String TYPE){

        SharedPreferences sharedPreferences = null;

        switch (TYPE){
            case PREFERENCES_POLICE_NUMBERS:sharedPreferences = ctx.getSharedPreferences(PREFERENCES_POLICE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_AMBULANCE_NUMBERS:sharedPreferences = ctx.getSharedPreferences(PREFERENCES_AMBULANCE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_CHILD_ABUSE_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_CHILD_ABUSE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_FIRE_FORCE_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_FIRE_FORCE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_JANASEVA_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_JANASEVA_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_SEXUAL_ASSAULT_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_SEXUAL_ASSAULT_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_STRAY_DOGS_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_STRAY_DOGS_NUMBERS,Context.MODE_PRIVATE);
                break;
        }
        if (sharedPreferences!=null){
            return sharedPreferences.getString(key+"","-1");
        }else return "-1";
    }

    public static int readPhoneNumberCount(Context ctx, String TYPE){

        SharedPreferences sharedPreferences = null;

        switch (TYPE){
            case PREFERENCES_POLICE_NUMBERS:sharedPreferences = ctx.getSharedPreferences(PREFERENCES_POLICE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_AMBULANCE_NUMBERS:sharedPreferences = ctx.getSharedPreferences(PREFERENCES_AMBULANCE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_CHILD_ABUSE_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_CHILD_ABUSE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_FIRE_FORCE_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_FIRE_FORCE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_JANASEVA_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_JANASEVA_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_SEXUAL_ASSAULT_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_SEXUAL_ASSAULT_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_STRAY_DOGS_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_STRAY_DOGS_NUMBERS,Context.MODE_PRIVATE);
                break;
        }
        if (sharedPreferences!=null){
            return sharedPreferences.getInt("COUNT",-1);
        }else return -1;
    }

    public static void storePhoneNumberCount(Context ctx,int count, String TYPE){

        SharedPreferences sharedPreferences = null;

        switch (TYPE){
            case PREFERENCES_POLICE_NUMBERS:sharedPreferences = ctx.getSharedPreferences(PREFERENCES_POLICE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_AMBULANCE_NUMBERS:sharedPreferences = ctx.getSharedPreferences(PREFERENCES_AMBULANCE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_CHILD_ABUSE_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_CHILD_ABUSE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_FIRE_FORCE_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_FIRE_FORCE_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_JANASEVA_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_JANASEVA_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_SEXUAL_ASSAULT_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_SEXUAL_ASSAULT_NUMBERS,Context.MODE_PRIVATE);
                break;
            case PREFERENCES_STRAY_DOGS_NUMBERS: sharedPreferences = ctx.getSharedPreferences(PREFERENCES_STRAY_DOGS_NUMBERS,Context.MODE_PRIVATE);
                break;
        }
        if (sharedPreferences!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("COUNT",count);
            editor.apply();
        }
    }




}
