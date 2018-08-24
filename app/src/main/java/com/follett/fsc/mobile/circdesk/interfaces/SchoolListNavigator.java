/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.interfaces;

public interface SchoolListNavigator {
    public void connectOnClick();
    public void displayErrorDialog(String message);
    public void navigationToNextFragment(int fragmentNumber);
}
