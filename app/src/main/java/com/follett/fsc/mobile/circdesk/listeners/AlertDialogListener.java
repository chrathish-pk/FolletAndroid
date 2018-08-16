/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */

package com.follett.fsc.mobile.circdesk.listeners;


public interface AlertDialogListener {
    
    void onPositiveButtonClick(int statusCode);
    
    void onNegativeButtonClick(int statusCode);
}
