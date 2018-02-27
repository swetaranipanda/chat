package com.sallychat;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.google.gson.GsonBuilder;
import com.sallychat.database.schemas.DBConfig;

import java.util.Date;


/**
 * Created by Girish
 */
public class BaseApplication extends Application {

    public static Context context;
    private static final String TAG = "BaseApplication";


    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.context = getApplicationContext();
        DBConfig.createDatabase(context);
        gsonBuilder();


    }


    public static void gsonBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, GsonUtils.customDateJsonSerializer);
        gsonBuilder.registerTypeAdapter(Date.class, GsonUtils.customDateJsonDeserializer);
        GsonUtils.gson = gsonBuilder.create();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(AppContants.SHARED_PREF_NAME, context.MODE_PRIVATE);
    }

    public static String getString(Context context, String key) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }

    public static Boolean getBoolean(Context context, String key) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void putString(Context context, String key, String value) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public static void putLong(Context context, String key, Long value) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putLong(key, value).commit();
    }


    public static void putBoolean(Context context, String key, Boolean value) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static void putInt(Context context, String key, int value) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static void putLong(Context context, String key, long value) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putLong(key, value).commit();
    }

    public static int getInt(Context context, String key) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }

    public static long getLong(Context context, String key) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getLong(key, 0);
    }

    public static void putDate(Context context, String key, Date value) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (value != null) {
            long millis = value.getTime();
            sharedPreferences.edit().putLong(key, millis).commit();
        }
    }

    public static Date getDate(Context context, String key) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        Long millis = sharedPreferences.getLong(key, 0);
        Date myDate = null;
        if (millis != 0) {
            myDate = new Date(millis);
        }
        return myDate;
    }

    public static void removeKey(Context context, String key) {
        if (context == null) {
            context = getAppContext();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        Editor editor = sharedPreferences.edit().remove(key);
        editor.commit();
    }


    public static Context getAppContext() {
        return BaseApplication.context;
    }


}