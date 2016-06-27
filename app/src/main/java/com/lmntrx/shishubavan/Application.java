package com.lmntrx.shishubavan;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.lmntrx.shishubavan.DatabaseModels.AmbulanceTable;
import com.lmntrx.shishubavan.DatabaseModels.BloodBanksTable;
import com.lmntrx.shishubavan.DatabaseModels.ChildAbuseTable;
import com.lmntrx.shishubavan.DatabaseModels.ExciseTable;
import com.lmntrx.shishubavan.DatabaseModels.FireForceTable;
import com.lmntrx.shishubavan.DatabaseModels.HighwayPoliceTable;
import com.lmntrx.shishubavan.DatabaseModels.JanasevaTable;
import com.lmntrx.shishubavan.DatabaseModels.PoliceTable;
import com.lmntrx.shishubavan.DatabaseModels.RailwayPoliceTable;
import com.lmntrx.shishubavan.DatabaseModels.SexualAssaultTable;
import com.lmntrx.shishubavan.DatabaseModels.StrayDogsTable;
import com.lmntrx.shishubavan.DatabaseModels.VigilanceTable;

/***
 * Created by livin on 28-Apr-16.
 */
public class Application extends com.activeandroid.app.Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Configuration.Builder config = new Configuration.Builder(this);
        config.addModelClasses(AmbulanceTable.class, BloodBanksTable.class, ChildAbuseTable.class, ExciseTable.class, FireForceTable.class, HighwayPoliceTable.class, JanasevaTable.class, PoliceTable.class, RailwayPoliceTable.class, SexualAssaultTable.class, StrayDogsTable.class, VigilanceTable.class);
        ActiveAndroid.initialize(config.create());
    }

    public static Context getContext() {
        return mContext;
    }
}
