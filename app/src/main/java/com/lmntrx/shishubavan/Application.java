package com.lmntrx.shishubavan;

import android.content.Context;

/***
 * Created by livin on 28-Apr-16.
 */
public class Application extends android.app.Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
