/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.interfaces;

public interface BasicNavigator {
    public void connectOnClick();
    public void asyncTaskResult(boolean result);
    public void displayErrorDialog(String message);
    public void navigationToNextFragment(int fragmentNumber);
}
