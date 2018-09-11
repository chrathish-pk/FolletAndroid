/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.local.prefs;

public class AppPreferencesHelper implements AppPrefHelper {
    
    private AppSharedPreferences mAppSharedPreferences;
    
    public AppPreferencesHelper(AppSharedPreferences appSharedPreferences) {
        mAppSharedPreferences = appSharedPreferences;
    }
    
    @Override
    public void setString(String key, String value) {
        mAppSharedPreferences.setString(key, value);
    }
    
    @Override
    public String getString(String key) {
        return mAppSharedPreferences.getString(key);
    }
    
    @Override
    public void setInt(String key, int value) {
        mAppSharedPreferences.setInt(key, value);
    }
    
    @Override
    public int getInt(String key) {
        return mAppSharedPreferences.getInt(key);
    }
    
    @Override
    public void setBoolean(String key, Boolean value) {
        mAppSharedPreferences.setBoolean(key, value);
    }
    
    @Override
    public Boolean getBoolean(String key) {
        return mAppSharedPreferences.getBoolean(key);
    }
    
    @Override
    public void removeValues(String key) {
        mAppSharedPreferences.removeValues(key);
    }
    
    @Override
    public void removeAllSession() {
        mAppSharedPreferences.removeAllSession();
    }
}
