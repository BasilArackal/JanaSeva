package com.lmntrx.shishubavan;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.gsm.SmsManager;
import android.util.Log;

/***
 * Created by livin on 28-Apr-16.
 */
public class Boss {

    static String LogTag = "Janaseva->" + Boss.class.getSimpleName();




    public final static String TYPE_POLICE = "TYPE_POLICE";
    public final static String TYPE_FIRETRUCK = "TYPE_FIRETRUCK";
    public final static String TYPE_AMBULANCE = "TYPE_AMBULANCE";
    public final static String TYPE_CHILD_ABUSE = "TYPE_CHILD_ABUSE";
    public final static String TYPE_STRAY_DOGS = "TYPE_STRAY_DOGS";
    public final static String TYPE_SEXUAL_ASSAULT = "TYPE_SEXUAL_ASSAULT";
    public static final String TYPE_SHISHUBAVAN = "TYPE_SHISHUBAVAN";


    public static void call_phone(String phoneNo[], Context ctx, Activity activity){

        Log.i("Status", "call made");
        Uri number = Uri.parse("tel:"+phoneNo[0]);
        Intent callIntent = new Intent(Intent.ACTION_CALL, number);
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            activity.startActivity(callIntent);
        }else {
            Log.e(LogTag,"No Permission");
        }

    }

    public static void call(int id, Activity activity,Location location) {
        switch (id){

            case R.id.card_ambulance: call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_AMBULANCE,Application.getContext()),Application.getContext(),activity);
                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_AMBULANCE,Application.getContext()),location,Boss.TYPE_AMBULANCE);
                break;
            case R.id.card_animalAbuse:call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_STRAY_DOGS,Application.getContext()),Application.getContext(),activity);
                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_STRAY_DOGS,Application.getContext()),location,Boss.TYPE_STRAY_DOGS);
                break;
            case R.id.card_childAbuse:call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_CHILD_ABUSE,Application.getContext()),Application.getContext(),activity);
                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_CHILD_ABUSE,Application.getContext()),location,Boss.TYPE_CHILD_ABUSE);
                break;
            case R.id.card_police:call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_POLICE,Application.getContext()),Application.getContext(),activity);
                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_POLICE,Application.getContext()),location,Boss.TYPE_POLICE);
                break;
            case R.id.card_firetruck:call_phone(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_FIRETRUCK,Application.getContext()),Application.getContext(),activity);
                sendTextMessageIfPossible(MotherOfDatabases.getPhoneNumbersOf(Boss.TYPE_FIRETRUCK,Application.getContext()),location,Boss.TYPE_FIRETRUCK);
                break;

            default: callCustom(id);
                break;
        }
    }

    private static void callCustom(int id) {
        //TODO:Later
    }

    public static void sendTextMessageIfPossible(String[] telNumbers, Location location, String msgType) {

        String messageBody = "";
        SmsManager smsMgr = SmsManager.getDefault();

        for (String telNumber : telNumbers){

            switch (msgType){
                case Boss.TYPE_AMBULANCE:
                    messageBody = "Urgent Ambulance requirement for "+telNumber+"\nLocation:http://maps.google.com/?q="+location.getLatitude()+","+location.getLongitude();
                    break;
                case Boss.TYPE_CHILD_ABUSE:
                    messageBody = "Urgent!! \nChild Abuse reported by "+telNumber+"\nLocation:http://maps.google.com/?q="+location.getLatitude()+","+location.getLongitude();
                    break;
                case Boss.TYPE_FIRETRUCK:
                    messageBody = "Urgent FireForce requirement for "+telNumber+"\nLocation:http://maps.google.com/?q="+location.getLatitude()+","+location.getLongitude();
                    break;
                case Boss.TYPE_POLICE:
                    messageBody = "Urgent Police requirement for "+telNumber+"\nLocation:http://maps.google.com/?q="+location.getLatitude()+","+location.getLongitude();
                    break;
                case Boss.TYPE_SEXUAL_ASSAULT:
                    messageBody = "Urgent!! \nSexual Assault reported by  "+telNumber+"\nLocation:http://maps.google.com/?q="+location.getLatitude()+","+location.getLongitude();
                    break;
                case Boss.TYPE_STRAY_DOGS:
                    messageBody = "Urgent!! \nStray Dogs Attack reported by "+telNumber+"\nLocation:http://maps.google.com/?q="+location.getLatitude()+","+location.getLongitude();
                    break;
            }
            smsMgr.sendTextMessage(telNumber, null, messageBody, null, null);
        }
    }

}
