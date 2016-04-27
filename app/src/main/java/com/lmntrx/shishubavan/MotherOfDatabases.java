package com.lmntrx.shishubavan;

import android.content.Context;
import android.content.Loader;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

/**
 * Created by livin on 27/4/16.
 */
public class MotherOfDatabases {

    public Context ctx = null;
    SQLiteDatabase sqLiteDatabase = null;

    public final static String NAME_DB = "EMERGENCY_NUMBERS_DB";

    public final static String NAME_POLICE_TABLE = "POLICE_TABLE";
    public final static String NAME_FIRE_FORCE_TABLE = "FIRE_FORCE_TABLE";
    public final static String NAME_AMBULANCE_TABLE = "AMBULANCE_TABLE";


    MotherOfDatabases(Context ctx){
        this.ctx = ctx;
    }

    public void createDatabase(String table_name){

        try {

            sqLiteDatabase = ctx.openOrCreateDatabase(NAME_DB,Context.MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ table_name+" (ID integer primary key, PHONE_NUMBER integer, LOCATION varchar, COORDINATES varchar);");

            File database = ctx.getDatabasePath(NAME_DB);

            if (database.exists()){
                Toast.makeText(ctx,"DATABASE "+NAME_DB+" EXISTS :)",Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(ctx,"DATABASE "+NAME_DB+" MISSING :(",Toast.LENGTH_LONG).show();
        }catch (Exception e ){

            Log.e(MotherOfDatabases.class.getSimpleName(),"Database couldn't be created!");

        }

    }

    public void addRow(String table_name){

    }

    public void closeDB(){
        sqLiteDatabase.close();
    }

}
