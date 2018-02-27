package com.sallychat.database.schemas;

import android.content.Context;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;


/**
 * Created by Girish
 */
public class DBConfig {

    public static final String DB_NAME = "sally.db";
    public static final int AA_DB_VERSION = 2;

    public static void createDatabase(Context context) {
        Configuration.Builder config = new Configuration.Builder(context);
        config.setDatabaseVersion(AA_DB_VERSION);
        config.setDatabaseName(DB_NAME);
        config.addModelClasses(ChatEntity.class);

        ActiveAndroid.initialize(config.create());
        Log.d("DBVersion", "Version :" + ActiveAndroid.getDatabase().getVersion());

    }

    public static boolean deleteDatabase(Context context) {
        if (context != null) {
            boolean deleteStatus = context.deleteDatabase(DB_NAME);
            Log.d("DbList", "Deleting database :  " + context.databaseList().toString() + " Delete Status :" + deleteStatus);
            return true;
        } else
            return false;
    }


}

