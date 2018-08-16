/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {
    
    private static AppSharedPreferences mSessionInstance = null;
    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;
    
    public static AppSharedPreferences getInstance(Context context) {
        if (mSessionInstance == null) {
            mSessionInstance = new AppSharedPreferences(context);
            return mSessionInstance;
        }
        return mSessionInstance;
    }
    
    private AppSharedPreferences(Context context) {
        String SHARED_PERFERENCE_NAME = "Follett";
        prefs = context.getSharedPreferences(SHARED_PERFERENCE_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.apply();
    }
    
    public void setString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }
    
    public String getString(String key) {
        String DEFAULT_VALUE = "";
        return prefs.getString(key, DEFAULT_VALUE);
    }
    
    public void setInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }
    
    public int getInt(String key) {
        int DEFAULT_INT_VALUE = -1;
        return prefs.getInt(key, DEFAULT_INT_VALUE);
    }
    
    public void setBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }
    
    public Boolean getBoolean(String key) {
        return prefs.getBoolean(key, false);
    }
    
    public void removeValues(String key) {
        editor.remove(key);
        editor.commit();
    }
    
    public void removeAllSession() {
        editor.clear();
        editor.commit();
    }
}
