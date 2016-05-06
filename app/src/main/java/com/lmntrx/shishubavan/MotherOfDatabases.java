package com.lmntrx.shishubavan;

import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
                List<AmbulanceTable> aList = new Select()
                        .from(AmbulanceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = aList;
                break;
            case Boss.TYPE_CHILD_ABUSE:
                List<ChildAbuseTable> cList = new Select()
                        .from(ChildAbuseTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = cList;
                break;
            case Boss.TYPE_FIRETRUCK:
                List<FireForceTable> fList = new Select()
                        .from(FireForceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = fList;
                break;
            case Boss.TYPE_POLICE:
                List<PoliceTable> pList = new Select()
                        .from(PoliceTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = pList;
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                List<SexualAssaultTable> sxList = new Select()
                        .from(SexualAssaultTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = sxList;
                break;
            case Boss.TYPE_STRAY_DOGS:
                List<SexualAssaultTable> stList = new Select()
                        .from(SexualAssaultTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = stList;
                break;
            case Boss.TYPE_SHISHUBAVAN:
                List<JanasevaTable> jsList = new Select()
                        .from(JanasevaTable.class)
                        .where("Enabled = ?", true)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = jsList;
                break;
        }

        return list;
    }

    private static List getAllControlRooms(String TYPE){
        List list = null;
        switch(TYPE){
            case Boss.TYPE_AMBULANCE:
                List<AmbulanceTable> aList = new Select()
                        .from(AmbulanceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = aList;
                break;
            case Boss.TYPE_CHILD_ABUSE:
                List<ChildAbuseTable> cList = new Select()
                        .from(ChildAbuseTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = cList;
                break;
            case Boss.TYPE_FIRETRUCK:
                List<FireForceTable> fList = new Select()
                        .from(FireForceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = fList;
                break;
            case Boss.TYPE_POLICE:
                List<PoliceTable> pList = new Select()
                        .from(PoliceTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = pList;
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                List<SexualAssaultTable> sxList = new Select()
                        .from(SexualAssaultTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = sxList;
                break;
            case Boss.TYPE_STRAY_DOGS:
                List<StrayDogsTable> stList = new Select()
                        .from(StrayDogsTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = stList;
                break;
            case Boss.TYPE_SHISHUBAVAN:
                List<JanasevaTable> jsList = new Select()
                        .from(JanasevaTable.class)
                        .orderBy("PlaceName ASC")
                        .execute();
                list = jsList;
                break;
        }

        return list;
    }

    public static String[] getEnabledNumbers(String TYPE){
        List list = getEnabledControlRooms(TYPE);
        String nos[] = new String[5];
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                for (int i = 0, j=0; i < 5 ; i++,j++) {
                    AmbulanceTable policeTable = (AmbulanceTable) list.get(i);
                    if (list.get(i)!=null)
                    nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_STRAY_DOGS:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    StrayDogsTable policeTable = (StrayDogsTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_SHISHUBAVAN:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    JanasevaTable policeTable = (JanasevaTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_CHILD_ABUSE:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    ChildAbuseTable policeTable = (ChildAbuseTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    SexualAssaultTable policeTable = (SexualAssaultTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_POLICE:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    PoliceTable policeTable = (PoliceTable)list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.number;
                    else j--;
                }
                break;
            case Boss.TYPE_FIRETRUCK:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
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
        String nos[] = new String[5];
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    AmbulanceTable policeTable = (AmbulanceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_STRAY_DOGS:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
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
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    ChildAbuseTable policeTable = (ChildAbuseTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    SexualAssaultTable policeTable = (SexualAssaultTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_POLICE:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    PoliceTable policeTable = (PoliceTable)list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
            case Boss.TYPE_FIRETRUCK:
                for (int i = 0,j=0; i < 5 ; i++,j++) {
                    FireForceTable policeTable = (FireForceTable) list.get(i);
                    if (list.get(i)!=null)
                        nos[j] = policeTable.placeName;
                    else j--;
                }
                break;
        }
        return nos;
    }

    public static ArrayList<String> getAllPlaces(String TYPE){
        List list = getAllControlRooms(TYPE);
        ArrayList<String> arrayList = new ArrayList<>();
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    AmbulanceTable policeTable = (AmbulanceTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,policeTable.placeName+"|"+policeTable.enabled);
                    else j--;
                }
                break;
            case Boss.TYPE_STRAY_DOGS:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    StrayDogsTable policeTable = (StrayDogsTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,policeTable.placeName+"|"+policeTable.enabled);
                    else j--;
                }
                break;
            case Boss.TYPE_SHISHUBAVAN:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    JanasevaTable policeTable = (JanasevaTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,policeTable.placeName+"|"+policeTable.enabled);
                    else j--;
                }
                break;
            case Boss.TYPE_CHILD_ABUSE:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    ChildAbuseTable policeTable = (ChildAbuseTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,policeTable.placeName+"|"+policeTable.enabled);
                    else j--;
                }
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    SexualAssaultTable policeTable = (SexualAssaultTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,policeTable.placeName+"|"+policeTable.enabled);
                    else j--;
                }
                break;
            case Boss.TYPE_POLICE:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    PoliceTable policeTable = (PoliceTable)list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,policeTable.placeName+"|"+policeTable.enabled);
                    else j--;
                }
                break;
            case Boss.TYPE_FIRETRUCK:
                for (int i = 0, j = 0; i < list.size() ; i++, j++) {
                    FireForceTable policeTable = (FireForceTable) list.get(i);
                    if (list.get(i)!=null)
                        arrayList.add(j,policeTable.placeName+"|"+policeTable.enabled);
                    else j--;
                }
                break;
        }
        return arrayList;
    }

    public static void UpdateItem(String TYPE,String PlaceName,Boolean enabled){
        switch (TYPE){
            case Boss.TYPE_AMBULANCE:
                new Update(AmbulanceTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .execute();
                break;
            case Boss.TYPE_STRAY_DOGS:
                new Update(StrayDogsTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .execute();
                break;
            case Boss.TYPE_SHISHUBAVAN:
                new Update(JanasevaTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .execute();
                break;
            case Boss.TYPE_CHILD_ABUSE:
                new Update(ChildAbuseTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .execute();
                break;
            case Boss.TYPE_SEXUAL_ASSAULT:
                new Update(SexualAssaultTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .execute();
                break;
            case Boss.TYPE_POLICE:
                new Update(PoliceTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .execute();
                break;
            case Boss.TYPE_FIRETRUCK:
                new Update(FireForceTable.class)
                        .set("Enabled = ?",enabled)
                        .where("PlaceName = ?", PlaceName)
                        .execute();
                break;
        }
    }

}
