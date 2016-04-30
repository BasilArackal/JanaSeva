package com.lmntrx.shishubavan;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/***
 * Created by livin on 28-Apr-16.
 */
public class Boss {

    static String LogTag = "Janaseva->" + Boss.class.getSimpleName();


    public static void call_phone(int phoneNo[], Context ctx, Activity activity){

        Log.i("Status", "call made");
        Uri number = Uri.parse("tel:"+phoneNo[0]);
        Intent callIntent = new Intent(Intent.ACTION_CALL, number);
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            activity.startActivity(callIntent);
        }else {
            Log.e(LogTag,"No Permission");
        }

    }

    public static void call(int id, Activity activity) {
        MotherOfDatabases db = new MotherOfDatabases(Application.getContext());
        switch (id){
            /*
            case R.id.ambulanceButton: call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_AMBULANCE_TABLE),Application.getContext(),activity);
                break;
            case R.id.animalAbuse:call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_AMBULANCE_TABLE),Application.getContext(),activity);
                break;
            case R.id.childAbuseButton:call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_POLICE_TABLE),Application.getContext(),activity);
                break;
            case R.id.policeButton:call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_POLICE_TABLE),Application.getContext(),activity);
                break;
            case R.id.fireTruckButton:call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_FIRE_FORCE_TABLE),Application.getContext(),activity);
                break;

                */

            case R.id.card_ambulance: call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_AMBULANCE_TABLE),Application.getContext(),activity);
                break;
            case R.id.card_animalAbuse:call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_AMBULANCE_TABLE),Application.getContext(),activity);
                break;
            case R.id.card_childAbuse:call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_POLICE_TABLE),Application.getContext(),activity);
                break;
            case R.id.card_police:call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_POLICE_TABLE),Application.getContext(),activity);
                break;
            case R.id.card_firetruck:call_phone(db.getPhoneNumbers(MotherOfDatabases.NAME_FIRE_FORCE_TABLE),Application.getContext(),activity);
                break;


            default: callCustom(id);
                break;
        }
    }

    private static void callCustom(int id) {
        //TODO:Later
    }
}
