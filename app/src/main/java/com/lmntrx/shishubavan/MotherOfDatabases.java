package com.lmntrx.shishubavan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.lmntrx.shishubavan.DatabaseModels.AmbulanceTable;
import com.lmntrx.shishubavan.DatabaseModels.ChildAbuseTable;
import com.lmntrx.shishubavan.DatabaseModels.FireForceTable;
import com.lmntrx.shishubavan.DatabaseModels.JanasevaTable;
import com.lmntrx.shishubavan.DatabaseModels.PoliceTable;
import com.lmntrx.shishubavan.DatabaseModels.SexualAssaultTable;
import com.lmntrx.shishubavan.DatabaseModels.StrayDogsTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        }
            String line;
            try {
                assert reader != null;
                while ((line = reader.readLine()) != null){
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
        Boolean enabled = true;
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (i>4)
                    enabled = false;
                PoliceTable item = new PoliceTable(places[i],nos[i],enabled);
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //Ambulances
        nos=getPhoneNumbersOf(Boss.TYPE_AMBULANCE,ctx);
        places=placeNamesOf(Boss.TYPE_AMBULANCE,ctx);
        enabled = true;
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (i>4)
                    enabled = false;
                AmbulanceTable item = new AmbulanceTable(places[i],nos[i],enabled);
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //CHILD_ABUSE
        nos=getPhoneNumbersOf(Boss.TYPE_CHILD_ABUSE,ctx);
        places=placeNamesOf(Boss.TYPE_CHILD_ABUSE,ctx);
        enabled = true;
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (i>4)
                    enabled = false;
                ChildAbuseTable item = new ChildAbuseTable(places[i],nos[i],enabled);
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //Firetruck
        nos=getPhoneNumbersOf(Boss.TYPE_FIRETRUCK,ctx);
        places=placeNamesOf(Boss.TYPE_FIRETRUCK,ctx);
        enabled = true;
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (i>4)
                    enabled = false;
                FireForceTable item = new FireForceTable(places[i],nos[i],enabled);
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //SexualAssault
        nos=getPhoneNumbersOf(Boss.TYPE_SEXUAL_ASSAULT,ctx);
        places=placeNamesOf(Boss.TYPE_SEXUAL_ASSAULT,ctx);
        enabled = true;
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (i>4)
                    enabled = false;
                SexualAssaultTable item = new SexualAssaultTable(places[i],nos[i],enabled);
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //StrayDogs
        nos=getPhoneNumbersOf(Boss.TYPE_STRAY_DOGS,ctx);
        places=placeNamesOf(Boss.TYPE_STRAY_DOGS,ctx);
        enabled = true;
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (i>4)
                    enabled = false;
                StrayDogsTable item = new StrayDogsTable(places[i],nos[i],enabled);
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
        //SHISHUBAVAN
        nos=getPhoneNumbersOf(Boss.TYPE_SHISHUBAVAN,ctx);
        places=placeNamesOf(Boss.TYPE_SHISHUBAVAN,ctx);
        enabled = true;
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < nos.length; i++) {
                if (i>4)
                    enabled = false;
                JanasevaTable item = new JanasevaTable(places[i],nos[i],enabled);
                item.save();
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
        }

        return list;
    }

    public static String[] getEnabledNumbers(String TYPE){
        List list = getEnabledControlRooms(TYPE);
        String nos[] = new String[list.size()];
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                for (int i = 0, j=0; i < list.size() ; i++,j++) {
                    AmbulanceTable policeTable = (AmbulanceTable) list.get(i);
                    if (list.get(i)!=null)
                    nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_STRAY_DOGS:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    StrayDogsTable policeTable = (StrayDogsTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_SHISHUBAVAN:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    JanasevaTable policeTable = (JanasevaTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_CHILD_ABUSE:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    ChildAbuseTable policeTable = (ChildAbuseTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    SexualAssaultTable policeTable = (SexualAssaultTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_POLICE:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    PoliceTable policeTable = (PoliceTable)list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_FIRETRUCK:
                for (int i = 0,j=0; i < list.size() ; i++,j++) {
                    FireForceTable policeTable = (FireForceTable) list.get(i);
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
        }
        return arrayList;
    }

    public static void UpdateItem(String TYPE,String text,Boolean enabled){
        String PlaceName = text.substring(0,text.indexOf(":"));
        String no = text.substring(text.indexOf(":")+2);
        From select;
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                select = new Select().from(AmbulanceTable.class).where("Number = ?", no);
                AmbulanceTable ambulanceTable = AmbulanceTable.load(AmbulanceTable.class,select.executeSingle().getId());
                ambulanceTable.enabled = enabled;
                ambulanceTable.save();/*

                new Update(AmbulanceTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .where("Number = ?",no)
                        .execute();*/
                Log.d(TAG,PlaceName + TYPE+enabled);
                break;
            case Boss.TYPE_STRAY_DOGS:
                select = new Select().from(StrayDogsTable.class).where("Number = ?", no);
                StrayDogsTable s = StrayDogsTable.load(StrayDogsTable.class,select.executeSingle().getId());
                s.enabled = enabled;
                s.save();
                /*
                new Update(StrayDogsTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .where("Number = ?",no)
                        .execute();*/
                Log.d(TAG,PlaceName + TYPE);
                break;
            case Boss.TYPE_SHISHUBAVAN:
                select = new Select().from(JanasevaTable.class).where("Number = ?", no);
                JanasevaTable j = JanasevaTable.load(JanasevaTable.class,select.executeSingle().getId());
                j.enabled = enabled;
                j.save();
                /*
                new Update(JanasevaTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .where("Number = ?",no)
                        .execute();
                        */
                Log.d(TAG,PlaceName + TYPE+enabled);
                break;
            case Boss.TYPE_CHILD_ABUSE:
                select = new Select().from(ChildAbuseTable.class).where("Number = ?", no);
                ChildAbuseTable c = ChildAbuseTable.load(ChildAbuseTable.class,select.executeSingle().getId());
                c.enabled = enabled;
                c.save();
                /*
                new Update(ChildAbuseTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .where("Number = ?",no)
                        .execute();*/
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                /*
                new Update(SexualAssaultTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .execute();
                */break;
            case Boss.TYPE_POLICE:
                select = new Select().from(PoliceTable.class).where("Number = ?", no);
                PoliceTable p = PoliceTable.load(PoliceTable.class,select.executeSingle().getId());
                p.enabled = enabled;
                p.save();
                /*new Update(PoliceTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .where("Number = ?",no)
                        .execute();*/
                Log.d(TAG,PlaceName + TYPE);
                break;
            case Boss.TYPE_FIRETRUCK:
                select = new Select().from(FireForceTable.class).where("Number = ?", no);
                FireForceTable f = FireForceTable.load(FireForceTable.class,select.executeSingle().getId());
                f.enabled = enabled;
                f.save();
                /*
                new Update(FireForceTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .where("Number = ?",no)
                        .execute();*/
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
}
