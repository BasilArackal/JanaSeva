package com.lmntrx.shishubavan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.lmntrx.shishubavan.DatabaseModels.AmbulanceTable;
import com.lmntrx.shishubavan.DatabaseModels.BloodBanksTable;
import com.lmntrx.shishubavan.DatabaseModels.ChildAbuseTable;
import com.lmntrx.shishubavan.DatabaseModels.HighwayPoliceTable;
import com.lmntrx.shishubavan.DatabaseModels.RailwayPoliceTable;
import com.lmntrx.shishubavan.DatabaseModels.VigilanceTable;
import com.lmntrx.shishubavan.DatabaseModels.ExciseTable;
import com.lmntrx.shishubavan.DatabaseModels.FireForceTable;
import com.lmntrx.shishubavan.DatabaseModels.JanasevaTable;
import com.lmntrx.shishubavan.DatabaseModels.PoliceTable;
import com.lmntrx.shishubavan.DatabaseModels.SexualAssaultTable;
import com.lmntrx.shishubavan.DatabaseModels.StrayDogsTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Created by livin on 27/4/16.
 */
public class MotherOfDatabases {

    public Context ctx = null;

    final static String TAG = MotherOfDatabases.class.getSimpleName();

    MotherOfDatabases(Context ctx){

        this.ctx = ctx;

    }

    public static String[] getPhoneNumbersOf(String alertType,Context ctx){
        String phoneNo[] = null;
        BufferedReader reader = null;
        int i = 0;

        switch (alertType){
            case Boss.TYPE_AMBULANCE:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.ambulance_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.ambulance)));
                break;
            case Boss.TYPE_FIRETRUCK:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.ff_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.fireforce)));
                break;
            case Boss.TYPE_POLICE:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.police_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.police)));
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.sx_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.sexualassault)));
                break;
            case Boss.TYPE_CHILD_ABUSE:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.ca_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.childabuse)));
                break;
            case Boss.TYPE_STRAY_DOGS:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.st_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.straydogs)));
                break;
            case Boss.TYPE_SHISHUBAVAN:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.js_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.shishubavan)));
                break;
            case Boss.TYPE_VIGILANCE:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.vi_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.vigilance)));
                break;
            case Boss.TYPE_EXCISE:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.ex_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.excise)));
                break;
            case Boss.TYPE_BLOOD_BANKS:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.bb_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.bloodbanks)));
                break;
            case Boss.TYPE_HIGHWAY:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.hp_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.highway)));
                break;
            case Boss.TYPE_RAILWAY:
                phoneNo = new String[ctx.getResources().getInteger(R.integer.ra_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.railway)));
                break;
        }
            String line;
            try {
                assert reader != null;
                while ((line = reader.readLine()) != null && phoneNo.length>0){
                    phoneNo[i] = line.substring(0,line.indexOf(";"));
                    i++;
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
        String places[] = null;
        BufferedReader reader = null;
        int i = 0;

        switch (alertType){
            case Boss.TYPE_AMBULANCE:
                places = new String[ctx.getResources().getInteger(R.integer.ambulance_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.ambulance)));
                break;
            case Boss.TYPE_FIRETRUCK:
                places = new String[ctx.getResources().getInteger(R.integer.ff_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.fireforce)));
                break;
            case Boss.TYPE_POLICE:
                places = new String[ctx.getResources().getInteger(R.integer.police_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.police)));
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                places = new String[ctx.getResources().getInteger(R.integer.sx_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.sexualassault)));
                break;
            case Boss.TYPE_CHILD_ABUSE:
                places = new String[ctx.getResources().getInteger(R.integer.ca_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.childabuse)));
                break;
            case Boss.TYPE_STRAY_DOGS:
                places= new String[ctx.getResources().getInteger(R.integer.st_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.straydogs)));
                break;
            case Boss.TYPE_SHISHUBAVAN:
                places = new String[ctx.getResources().getInteger(R.integer.js_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.shishubavan)));
                break;
            case Boss.TYPE_VIGILANCE:
                places = new String[ctx.getResources().getInteger(R.integer.vi_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.vigilance)));
                break;
            case Boss.TYPE_EXCISE:
                places= new String[ctx.getResources().getInteger(R.integer.ex_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.excise)));
                break;
            case Boss.TYPE_BLOOD_BANKS:
                places = new String[ctx.getResources().getInteger(R.integer.bb_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.bloodbanks)));
                break;
            case Boss.TYPE_HIGHWAY:
                places= new String[ctx.getResources().getInteger(R.integer.hp_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.highway)));
                break;
            case Boss.TYPE_RAILWAY:
                places = new String[ctx.getResources().getInteger(R.integer.ra_array_size)];
                reader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.railway)));
                break;
        }
        String line;
        try {
            assert reader != null;
            while ((line = reader.readLine()) != null ){
                try {
                    places[i] = line.substring(line.indexOf(";")+1,line.indexOf("&"));
                } catch (ArrayIndexOutOfBoundsException e2) {
                    Log.e(TAG, "readPlaces(): "+alertType+e2);
                }
                i++;
            }
        } catch (IOException e) {
            Log.e(TAG, "readPlaces(): "+e);
        }
        finally {
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

    public static void populateDB(Context ctx){
        //Polices
        String nos[]=getPhoneNumbersOf(Boss.TYPE_POLICE,ctx);
        String places[]=placeNamesOf(Boss.TYPE_POLICE,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    PoliceTable item = new PoliceTable(places[i],nos[i], false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //Ambulances
        nos=getPhoneNumbersOf(Boss.TYPE_AMBULANCE,ctx);
        places=placeNamesOf(Boss.TYPE_AMBULANCE,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    AmbulanceTable item = new AmbulanceTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //CHILD_ABUSE
        nos=getPhoneNumbersOf(Boss.TYPE_CHILD_ABUSE,ctx);
        places=placeNamesOf(Boss.TYPE_CHILD_ABUSE,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    ChildAbuseTable item = new ChildAbuseTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //Firetruck
        nos=getPhoneNumbersOf(Boss.TYPE_FIRETRUCK,ctx);
        places=placeNamesOf(Boss.TYPE_FIRETRUCK,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    FireForceTable item = new FireForceTable(places[i],nos[i],true);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //SexualAssault
        nos=getPhoneNumbersOf(Boss.TYPE_SEXUAL_ASSAULT,ctx);
        places=placeNamesOf(Boss.TYPE_SEXUAL_ASSAULT,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    SexualAssaultTable item = new SexualAssaultTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //StrayDogs
        nos=getPhoneNumbersOf(Boss.TYPE_STRAY_DOGS,ctx);
        places=placeNamesOf(Boss.TYPE_STRAY_DOGS,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    StrayDogsTable item = new StrayDogsTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //SHISHUBAVAN
        nos=getPhoneNumbersOf(Boss.TYPE_SHISHUBAVAN,ctx);
        places=placeNamesOf(Boss.TYPE_SHISHUBAVAN,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    JanasevaTable item = new JanasevaTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //VIGILANCE
        nos=getPhoneNumbersOf(Boss.TYPE_VIGILANCE,ctx);
        places=placeNamesOf(Boss.TYPE_VIGILANCE,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    VigilanceTable item = new VigilanceTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //EXCISE
        nos=getPhoneNumbersOf(Boss.TYPE_EXCISE,ctx);
        places=placeNamesOf(Boss.TYPE_EXCISE,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    ExciseTable item = new ExciseTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //BLOOD_BANKS
        nos=getPhoneNumbersOf(Boss.TYPE_BLOOD_BANKS,ctx);
        places=placeNamesOf(Boss.TYPE_BLOOD_BANKS,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    BloodBanksTable item = new BloodBanksTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //HIGHWAY
        nos=getPhoneNumbersOf(Boss.TYPE_HIGHWAY,ctx);
        places=placeNamesOf(Boss.TYPE_HIGHWAY,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    HighwayPoliceTable item = new HighwayPoliceTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //RAILWAY
        nos=getPhoneNumbersOf(Boss.TYPE_RAILWAY,ctx);
        places=placeNamesOf(Boss.TYPE_RAILWAY,ctx);
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (places[i]!=null && nos[i]!=null && !places[i].equals("null") && !nos[i].equals("null")){
                    RailwayPoliceTable item = new RailwayPoliceTable(places[i],nos[i],false);
                    item.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }

    private static List getEnabledControlRooms(String TYPE){
        List list = null;
        switch(TYPE){
            case Boss.TYPE_AMBULANCE:
                list = new Select()
                        .from(AmbulanceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_CHILD_ABUSE:
                list = new Select()
                        .from(ChildAbuseTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_FIRETRUCK:
                list = new Select()
                        .from(FireForceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_POLICE:
                list = new Select()
                        .from(PoliceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                list = new Select()
                        .from(SexualAssaultTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_STRAY_DOGS:
                list = new Select()
                        .from(StrayDogsTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_SHISHUBAVAN:
                list = new Select()
                        .from(JanasevaTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_VIGILANCE:
                list = new Select()
                        .from(VigilanceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_EXCISE:
                list = new Select()
                        .from(ExciseTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_BLOOD_BANKS:
                list = new Select()
                        .from(BloodBanksTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_HIGHWAY:
                list = new Select()
                        .from(HighwayPoliceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_RAILWAY:
                list = new Select()
                        .from(RailwayPoliceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
        }

        return list;
    }

    private static List getAllControlRooms(String TYPE){
        List list = null;
        switch(TYPE){
            case Boss.TYPE_AMBULANCE:
                list = new Select()
                        .from(AmbulanceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_CHILD_ABUSE:
                list = new Select()
                        .from(ChildAbuseTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_FIRETRUCK:
                list = new Select()
                        .from(FireForceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_POLICE:
                list = new Select()
                        .from(PoliceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                list = new Select()
                        .from(SexualAssaultTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_STRAY_DOGS:
                list = new Select()
                        .from(StrayDogsTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_SHISHUBAVAN:
                list = new Select()
                        .from(JanasevaTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_VIGILANCE:
                list = new Select()
                        .from(VigilanceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_EXCISE:
                list = new Select()
                        .from(ExciseTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_BLOOD_BANKS:
                list = new Select()
                        .from(BloodBanksTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_HIGHWAY:
                list = new Select()
                        .from(HighwayPoliceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
            case Boss.TYPE_RAILWAY:
                list = new Select()
                        .from(RailwayPoliceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                break;
        }

        return list;
    }

    public static String[] getEnabledNumbers(String TYPE,Context context){
        List list = getEnabledControlRooms(TYPE);
        String nos[] = new String[list.size()+1];
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                nos[0] = context.getString(R.string.ambulance_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    AmbulanceTable policeTable = (AmbulanceTable) list.get(i);
                    if (list.get(i)!=null)
                    nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_STRAY_DOGS:
                nos[0] = context.getString(R.string.shishubavan_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    StrayDogsTable policeTable = (StrayDogsTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_SHISHUBAVAN:
                nos[0] = context.getString(R.string.shishubavan_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    JanasevaTable policeTable = (JanasevaTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_CHILD_ABUSE:
                nos[0] = context.getString(R.string.child_helpline_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    ChildAbuseTable policeTable = (ChildAbuseTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                nos[0] = context.getString(R.string.women_helpline_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    SexualAssaultTable policeTable = (SexualAssaultTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_POLICE:
                nos[0] = context.getString(R.string.police_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    PoliceTable policeTable = (PoliceTable)list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_FIRETRUCK:
                nos[0] = context.getString(R.string.fire_force_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    FireForceTable policeTable = (FireForceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_VIGILANCE:
                nos[0] = context.getString(R.string.vigilance_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    VigilanceTable policeTable = (VigilanceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_EXCISE:
                nos[0] = context.getString(R.string.excise_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    ExciseTable policeTable = (ExciseTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_BLOOD_BANKS:
                nos[0] = context.getString(R.string.blood_bank_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    BloodBanksTable policeTable = (BloodBanksTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_HIGHWAY:
                nos[0] = context.getString(R.string.highway_police_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    HighwayPoliceTable policeTable = (HighwayPoliceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_RAILWAY:
                nos[0] = context.getString(R.string.railway_alert_default_number);
                for (int i = 0, j=1; i < list.size() ; i++,j++) {
                    RailwayPoliceTable policeTable = (RailwayPoliceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
        }
        return nos;
    }

    public static String[] getEnabledPlaces(String TYPE){
        List list = getEnabledControlRooms(TYPE);
        String nos[] = new String[list.size()];
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    AmbulanceTable policeTable = (AmbulanceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_STRAY_DOGS:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    StrayDogsTable policeTable = (StrayDogsTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_SHISHUBAVAN:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    JanasevaTable policeTable = (JanasevaTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_CHILD_ABUSE:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    ChildAbuseTable policeTable = (ChildAbuseTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    SexualAssaultTable policeTable = (SexualAssaultTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_POLICE:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    PoliceTable policeTable = (PoliceTable)list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_FIRETRUCK:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    FireForceTable policeTable = (FireForceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_VIGILANCE:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    VigilanceTable policeTable = (VigilanceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_EXCISE:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    ExciseTable policeTable = (ExciseTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_BLOOD_BANKS:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    BloodBanksTable policeTable = (BloodBanksTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_HIGHWAY:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    HighwayPoliceTable policeTable = (HighwayPoliceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_RAILWAY:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    RailwayPoliceTable policeTable = (RailwayPoliceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
        }
        return nos;
    }

    public static ArrayList<NumbersListModel> getAllPlaces(String TYPE){
        List list = getAllControlRooms(TYPE);
        ArrayList<NumbersListModel> arrayList = new ArrayList<>();
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    AmbulanceTable policeTable = (AmbulanceTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_STRAY_DOGS:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    StrayDogsTable policeTable = (StrayDogsTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_SHISHUBAVAN:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    JanasevaTable policeTable = (JanasevaTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_CHILD_ABUSE:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    ChildAbuseTable policeTable = (ChildAbuseTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    SexualAssaultTable policeTable = (SexualAssaultTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_POLICE:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    PoliceTable policeTable = (PoliceTable)list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_FIRETRUCK:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    FireForceTable policeTable = (FireForceTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_VIGILANCE:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    VigilanceTable policeTable = (VigilanceTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_EXCISE:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    ExciseTable policeTable = (ExciseTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_BLOOD_BANKS:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    BloodBanksTable policeTable = (BloodBanksTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_HIGHWAY:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    HighwayPoliceTable policeTable = (HighwayPoliceTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
            case Boss.TYPE_RAILWAY:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    RailwayPoliceTable policeTable = (RailwayPoliceTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,new NumbersListModel(policeTable.placeName,policeTable.number,policeTable.enabled));
                    else j--;
                }
                break;
        }
        return arrayList;
    }

    public static void UpdateItem(String TYPE,String text,Boolean enabled){
        String no = text.substring(text.indexOf(":")+2);
        From select;
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                select = new Select().from(AmbulanceTable.class).where("Number = ?", no);
                AmbulanceTable ambulanceTable = AmbulanceTable.load(AmbulanceTable.class,select.executeSingle().getId());
                ambulanceTable.enabled = enabled;
                ambulanceTable.save();
                break;
            case Boss.TYPE_STRAY_DOGS:
                select = new Select().from(StrayDogsTable.class).where("Number = ?", no);
                StrayDogsTable s = StrayDogsTable.load(StrayDogsTable.class,select.executeSingle().getId());
                s.enabled = enabled;
                s.save();
                break;
            case Boss.TYPE_SHISHUBAVAN:
                select = new Select().from(JanasevaTable.class).where("Number = ?", no);
                JanasevaTable j = JanasevaTable.load(JanasevaTable.class,select.executeSingle().getId());
                j.enabled = enabled;
                j.save();
                break;
            case Boss.TYPE_CHILD_ABUSE:
                select = new Select().from(ChildAbuseTable.class).where("Number = ?", no);
                ChildAbuseTable c = ChildAbuseTable.load(ChildAbuseTable.class,select.executeSingle().getId());
                c.enabled = enabled;
                c.save();
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                select = new Select().from(SexualAssaultTable.class).where("Number = ?", no);
                SexualAssaultTable sx = SexualAssaultTable.load(SexualAssaultTable.class,select.executeSingle().getId());
                sx.enabled = enabled;
                sx.save();
                break;
            case Boss.TYPE_POLICE:
                select = new Select().from(PoliceTable.class).where("Number = ?", no);
                PoliceTable p = PoliceTable.load(PoliceTable.class,select.executeSingle().getId());
                p.enabled = enabled;
                p.save();
                break;
            case Boss.TYPE_FIRETRUCK:
                select = new Select().from(FireForceTable.class).where("Number = ?", no);
                FireForceTable f = FireForceTable.load(FireForceTable.class,select.executeSingle().getId());
                f.enabled = enabled;
                f.save();
                break;

            case Boss.TYPE_VIGILANCE:
                select = new Select().from(VigilanceTable.class).where("Number = ?", no);
                VigilanceTable cu = VigilanceTable.load(VigilanceTable.class,select.executeSingle().getId());
                cu.enabled = enabled;
                cu.save();
                break;
            case Boss.TYPE_EXCISE:
                select = new Select().from(ExciseTable.class).where("Number = ?", no);
                ExciseTable ex = ExciseTable.load(ExciseTable.class,select.executeSingle().getId());
                ex.enabled = enabled;
                ex.save();
                break;
            case Boss.TYPE_BLOOD_BANKS:
                select = new Select().from(BloodBanksTable.class).where("Number = ?", no);
                BloodBanksTable b = BloodBanksTable.load(BloodBanksTable.class,select.executeSingle().getId());
                b.enabled = enabled;
                b.save();
                break;
            case Boss.TYPE_HIGHWAY:
                select = new Select().from(HighwayPoliceTable.class).where("Number = ?", no);
                HighwayPoliceTable hp = HighwayPoliceTable.load(HighwayPoliceTable.class,select.executeSingle().getId());
                hp.enabled = enabled;
                hp.save();
                break;
            case Boss.TYPE_RAILWAY:
                select = new Select().from(RailwayPoliceTable.class).where("Number = ?", no);
                RailwayPoliceTable rp = RailwayPoliceTable.load(RailwayPoliceTable.class,select.executeSingle().getId());
                rp.enabled = enabled;
                rp.save();
                break;
        }
    }

    public static void deleteOldDB() {

        SQLiteDatabase db = ActiveAndroid.getDatabase();
        List<String> tables = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tableName = cursor.getString(1);
            if (!tableName.equals("android_metadata") &&
                    !tableName.equals("sqlite_sequence")) {
                tables.add(tableName);
            }
            cursor.moveToNext();
        }
        cursor.close();
        for (String tableName : tables) {
            db.execSQL("DELETE FROM " + tableName);
        }

    }

    public static String[] getCustomSmsNumbers(Context context){
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("JSDB",Context.MODE_PRIVATE,null);
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from CustomSMSNumbersTable",null);
            String numbersAndNames[];
            Set<String> set = new HashSet<>();
            if (cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do {
                    set.add(cursor.getString(1) + ":" + cursor.getString(0));
                }while (cursor.moveToNext());
            }
            numbersAndNames = new String[set.size()];
            set.toArray(numbersAndNames);
            if (cursor != null)
                cursor.close();
            return numbersAndNames;
        }catch (Exception e){
            return null;
        }

    }

    public static void addCustomSmsNumber(Context context, String name, String number){
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("JSDB",Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CustomSMSNumbersTable(Name varchar, Number INTEGER(15) PRIMARY KEY);");
        try {
            number = number.replace("(","");
            number = number.replace(")","");
            number = number.replace(" ","");
            sqLiteDatabase.execSQL("INSERT INTO CustomSMSNumbersTable values('" + name + "'," + number + ");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
