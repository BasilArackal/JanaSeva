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

    //Create table on initial launch
    public void createTable(String table_name){

        try {

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ table_name+
                    " (ID integer primary key, PHONE_NUMBER integer, LOCATION varchar, " +
                    "LAT varchar, LNG varchar);");

            File database = ctx.getDatabasePath(NAME_DB);

            if (database.exists()){
                Toast.makeText(ctx,"DATABASE "+NAME_DB+" EXISTS :)",Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(ctx,"DATABASE "+NAME_DB+" MISSING :(",Toast.LENGTH_LONG).show();
        }catch (Exception e ){

            Log.e(MotherOfDatabases.class.getSimpleName(),"Database couldn't be created!");

        }

    }

    //Add rows on initial launch
    public void addRowsTo(String table_name){

        BufferedReader reader = null;

        switch (table_name){
            case NAME_AMBULANCE_TABLE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.a)));
                break;
            case NAME_FIRE_FORCE_TABLE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.fs)));
                break;
            case NAME_POLICE_TABLE:
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.ps)));
                break;
        }


        if (sqLiteDatabase.isOpen()){

            String line;
            try {
                assert reader != null;
                while ((line = reader.readLine()) != null ){
                    String phno = line.substring(0,line.indexOf(";"));
                    String place = line.substring(line.indexOf(";")+1,line.indexOf(":"));
                    String lat = line.substring(line.indexOf(":")+1,line.indexOf(","));
                    String lng = line.substring(line.indexOf(",")+1,line.indexOf("&"));
                    sqLiteDatabase.execSQL("INSERT INTO "+table_name+
                            " (PHONE_NUMBER,LOCATION,LAT,LNG) VALUES ('"+phno+"','"+
                            place+"','"+lat+"','"+lng+"');");
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

    }

    //Search for phone number
    public String getPhone(String table_name, Location l){

        String lat = l.getLatitude()+"";
        String lng = l.getLongitude()+"";

        Cursor cursor = sqLiteDatabase.rawQuery("Select PHONE_NUMBER from "+table_name+" where (LAT between "+
                floor(lat) +" and "+ ceal(lat) + ") && (LNG between "+floor(lng) +" and "+ ceal(lng) + ");",null);

        cursor.moveToFirst();

        String phonenos = "";

        if ((cursor.getCount() > 0)){

            do{

                phonenos += cursor.getString(cursor.getColumnIndex("PHONE_NUMBER")) + "\n";


            }while (cursor.moveToNext());

            cursor.close();
            return phonenos;

        }else{
            cursor.close();
            return "-1";  // no results found
        }
    }

    private String ceal(String lat) {

        /*
        * Function to lower the lat/lng
        * */

        return null;
    }

    private String floor(String lat) {

        /*
        * Function to round up the lat/lng
        * */

        return null;
    }


    public void closeDB(){
        sqLiteDatabase.close();
    }

}
