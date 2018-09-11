/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.local.prefs;

public interface AppPrefHelper {
    
    public void setString(String key, String value);
    
    public String getString(String key);
    
    public void setInt(String key, int value);
    
    public int getInt(String key);
    
    public void setBoolean(String key, Boolean value);
    
    public Boolean getBoolean(String key);
    
    public void removeValues(String key);
    
    public void removeAllSession();
}
