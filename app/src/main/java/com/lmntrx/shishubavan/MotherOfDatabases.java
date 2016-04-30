package com.lmntrx.shishubavan;

import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/***
 * Created by livin on 27/4/16.
 */
public class MotherOfDatabases {

    public Context ctx = null;
    SQLiteDatabase sqLiteDatabase = null;

    final static String TAG = MotherOfDatabases.class.getSimpleName();

    public final static String NAME_DB = "EMERGENCY_NUMBERS_DB";

    public final static String NAME_POLICE_TABLE = "POLICE_TABLE";
    public final static String NAME_FIRE_FORCE_TABLE = "FIRE_FORCE_TABLE";
    public final static String NAME_AMBULANCE_TABLE = "AMBULANCE_TABLE";

    MotherOfDatabases(Context ctx){

        this.ctx = ctx;

        sqLiteDatabase = ctx.openOrCreateDatabase(NAME_DB,Context.MODE_PRIVATE,null);

    }
/*
    //Create table on initial launch
    public void createTable(String table_name){

        try {

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ table_name+
                    " (ID integer primary key, PHONE_NUMBER integer, LOCATION varchar);");

        }catch (Exception e ){

            Log.e(MotherOfDatabases.class.getSimpleName(),"Database couldn't be created!");

        }

    }*/

    /*//Add rows on initial launch
    public void addRowsTo(String table_name){

        BufferedReader reader = null;

        switch (table_name){
            case NAME_AMBULANCE_TABLE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.ambulance)));
                break;
            case NAME_FIRE_FORCE_TABLE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.fireforce)));
                break;
            case NAME_POLICE_TABLE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.police)));
                break;
        }


        if (sqLiteDatabase.isOpen()){

            String line;
            try {
                assert reader != null;
                while ((line = reader.readLine()) != null ){
                    String phno = line.substring(0,line.indexOf(";"));
                    String place = line.substring(line.indexOf(";")+1,line.indexOf("&"));
                    sqLiteDatabase.execSQL("INSERT INTO "+table_name+
                            " (PHONE_NUMBER,LOCATION) VALUES ('"+phno+"','"+
                            place+"');");
                }
            } catch (IOException e) {
                Log.e(TAG, "addRowsTo(): "+e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "addRowsTo()->finally: "+e);
                    }
                }
            }
        }

    }*/

    /*//Search for phone number
    public int[] getPhoneNumbers(String table_name){


        Cursor cursor = sqLiteDatabase.rawQuery("Select PHONE_NUMBER from "+table_name+";",null);

        cursor.moveToFirst();

        int phonenos[] = new int[cursor.getCount()], i=0;

        if ((cursor.getCount() > 0)){

            do{

                phonenos[i] = cursor.getInt(cursor.getColumnIndex("PHONE_NUMBER"));
                i++;


            }while (cursor.moveToNext());

            cursor.close();

        }
        return phonenos;
    }*/

    public static String[] getPhoneNumbersOf(String alertType,Context ctx){
        String phoneNo[] = new String[10];
        BufferedReader reader = null;
        int i = 0;

        switch (alertType){
            case Boss.TYPE_AMBULANCE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.ambulance)));
                break;
            case Boss.TYPE_FIRETRUCK:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.fireforce)));
                break;
            case Boss.TYPE_POLICE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.police)));
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.sexualassault)));
                break;
            case Boss.TYPE_CHILD_ABUSE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.childabuse)));
                break;
            case Boss.TYPE_STRAY_DOGS:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.straydogs)));
                break;
            case Boss.TYPE_SHISHUBAVAN:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.shishubavan)));
                break;
        }
            String line;
            try {
                assert reader != null;
                while ((line = reader.readLine()) != null ){
                    phoneNo[i] = line.substring(0,line.indexOf(";"));
                    i++;
                    //String place = line.substring(line.indexOf(";")+1,line.indexOf("&"));
                }
            } catch (IOException e) {
                Log.e(TAG, "readPhoneNos(): "+e);
            } catch (NullPointerException e1){
                Log.e(TAG, "readPhoneNos(): "+e1.getMessage()+"");
            }finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "readPhoneNos->finally: "+e);
                    }
                }
            }

        return phoneNo;
    }

    public static String[] placeNamesOf(String alertType,Context ctx){
        String places[] = new String[10];
        BufferedReader reader = null;
        int i = 0;

        switch (alertType){
            case Boss.TYPE_AMBULANCE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.ambulance)));
                break;
            case Boss.TYPE_FIRETRUCK:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.fireforce)));
                break;
            case Boss.TYPE_POLICE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.police)));
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.sexualassault)));
                break;
            case Boss.TYPE_CHILD_ABUSE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.childabuse)));
                break;
            case Boss.TYPE_STRAY_DOGS:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.straydogs)));
                break;
            case Boss.TYPE_SHISHUBAVAN:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.shishubavan)));
                break;
        }
        String line;
        try {
            assert reader != null;
            while ((line = reader.readLine()) != null ){
                places[i] = line.substring(line.indexOf(";")+1,line.indexOf("&"));
                i++;
            }
        } catch (IOException e) {
            Log.e(TAG, "readPlaces(): "+e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "readPlaces->finally: "+e);
                }
            }
        }

        return places;
    }


    public void closeDB(){
        sqLiteDatabase.close();
    }

}
