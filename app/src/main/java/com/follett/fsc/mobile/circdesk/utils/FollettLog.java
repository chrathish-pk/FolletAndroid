/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.utils;

import android.util.Log;

public class FollettLog {

    private FollettLog() {
    }

    /**
     * Send an ERROR log message.
     *
     * @param tag     Used to identify the source of a log message.
     * @param message The message you would like logged.
     */
    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    /**
     * Send an INFO log message.
     *
     * @param tag     Used to identify the source of a log message.
     * @param message The message you would like logged.
     */
    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    /**
     * Send a DEBUG log message.
     *
     * @param tag     Used to identify the source of a log message.
     * @param message The message you would like logged.
     */
    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param tag     Used to identify the source of a log message.
     * @param message The message you would like logged.
     */
    public static void v(String tag, String message) {
        Log.v(tag, message);
    }

    /**
     * Send a WARN log message.
     *
     * @param tag     Used to identify the source of a log message.
     * @param message The message you would like logged.
     */
    public static void w(String tag, String message) {
        Log.w(tag, message);
    }


}
