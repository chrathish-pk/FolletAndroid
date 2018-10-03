/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.apicommon;

public class FollettApiConstants {
    
    public static final int LOGIN_REQUEST_CODE = 1000;
    
    public static final int SCAN_PATRON_REQUEST_CODE = LOGIN_REQUEST_CODE + 1;
    
    public static final int CHECK_OUT_REQUEST_CODE = SCAN_PATRON_REQUEST_CODE + 1;
    
    public static final int PATRON_STATUS_REQUEST_CODE = CHECK_OUT_REQUEST_CODE + 1;
    
    public static final int TITLE_DETAILS_REQUEST_CODE = PATRON_STATUS_REQUEST_CODE + 1;
    
    public static final int ITEM_STATUS_REQUEST_CODE = TITLE_DETAILS_REQUEST_CODE + 1;
    
    public static final int INPROGRESS_INVENTORY_REQUEST_CODE = ITEM_STATUS_REQUEST_CODE + 1;
    
    public static final int INVENTORY_DETAILS_REQUEST_CODE = INPROGRESS_INVENTORY_REQUEST_CODE + 1;
    
    public static final int CHECKIN_REQUEST_CODE = INVENTORY_DETAILS_REQUEST_CODE + 1;
    
    public static final int CIRCULATION_TYPE_REQUEST_CODE = CHECKIN_REQUEST_CODE + 1;

    public static final int CREATE_INVENTORY_REQUEST_CODE = CIRCULATION_TYPE_REQUEST_CODE + 1;

    public static final int GET_SELECTED_INVENTORY_REQUEST_CODE = CREATE_INVENTORY_REQUEST_CODE + 1;

    
    
    public static final String SERVICE_ISSUE = "Service issue. Please try again later.";
    
    public static final String PERMISSION_DENIED = "You don't have permission to access this functionality.";
    
    public static final String SOCKET_EXCEPTION = "Time out error. Please try again later.";
    
    public static final String IO_EXCEPTION = "Internet issue. Please try again when you have a better connection.";
    
    
    
}
