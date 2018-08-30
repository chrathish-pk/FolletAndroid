/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.net.URL;

public class AppSharedPreferences {
    
    public static final int DEFAULT_HTTP_PORT = 80;
    
    public static final int DEFAULT_SSL_PORT = 443;
    
    public static final int MIN_PORT = 1;
    public static final int MAX_PORT = 65535;
    
    public static final String PREFS_NAME = "api.context.prefs";
    
    public static final String KEY_SERVER_NAME = "serverName";
    public static final String KEY_SERVER_PORT = "serverPort";
    public static final String KEY_SERVER_SSL_PORT = "serverSSLPort";
    public static final String KEY_CONTEXT_NAME = "contextName";
    public static final String SERVER_URI_VALUE = "server_uri_value";
    
    public static final String KEY_SITE_SHORT_NAME = "site";
    public static final String KEY_SITE_NAME = "siteName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_SESSION_ID = "sessionID";
    public static final String KEY_LIST_ID = "listID";
    public static final String KEY_SITE_ID = "siteID";
    public static final String KEY_SESSION_TIMEOUT = "sessionTimeout";
    public static final String KEY_LIST_SIZE = "listSize";
    public static final String KEY_FORCE_SSL_ALWAYS = "forceSSLAlways";
    public static final String KEY_API_VERSION = "apiVersion";
    public static final String KEY_API_VERSION_LAST_CHECK = "apiVersion_lastCheck";
    public static final String KEY_TEST_MODE = "testMode";
    public static final String KEY_GUEST = "guestMode";
    
    public static final String KEY_STICKY_PRODUCT_TYPE = "stickyProductType";
    public static final String KEY_PATRON_NAME = "patronName";
    
    public static final String KEY_PERMISSIONS = "permissions";
    public static final String SESSION_ID = "sessionid";
    public static final String KEY_SELECTED_BARCODE = "selectedBarcode";
    public static final String KEY_BARCODE = "barcode";
    public static final String KEY_PATRON_ID = "patronID";


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
    
    // https://test.follettdestiny.com/common/welcome.jsp?context=saas1_1206107
    public void populateInfoFromURL(URL url) {
        setString(KEY_SERVER_NAME, url.getHost());
        setBoolean(KEY_FORCE_SSL_ALWAYS, "https".equalsIgnoreCase(url.getProtocol()));
        if (getBoolean(KEY_FORCE_SSL_ALWAYS)) {
            if (url.getPort() != -1) {
                setInt(KEY_SERVER_SSL_PORT, url.getPort());
            }
        } else {
            if (url.getPort() != -1) {
                setInt(KEY_SERVER_PORT, url.getPort());
            }
        }
        setString(KEY_CONTEXT_NAME, pullParamFromURL(url, "context"));
    }
    
    protected static String pullParamFromURL(URL url, String paramName) {
        String result = null;
        if (url.toString()
                .contains(paramName)) {
            result = url.toString();
            result = result.substring(result.indexOf(paramName) + paramName.length() + 1);
            if (result.contains("&")) {
                result = result.substring(0, result.indexOf("&"));
            }
        }
        return result;
    }
}
