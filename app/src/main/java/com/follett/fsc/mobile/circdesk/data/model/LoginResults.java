/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResults {
    
    @SerializedName("loginResults") @Expose private LoginResultsData loginResults;
    
    public LoginResultsData getLoginResults() {
        return loginResults;
    }
    
    public void setLoginResults(LoginResultsData loginResults) {
        this.loginResults = loginResults;
    }
    
}