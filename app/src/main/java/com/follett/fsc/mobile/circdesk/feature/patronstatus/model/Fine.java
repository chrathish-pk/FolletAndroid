
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fine {
    
    @SerializedName("siteName") @Expose private Object siteName;
    @SerializedName("description") @Expose private String description;
    @SerializedName("reason") @Expose private String reason;
    @SerializedName("fineID") @Expose private Integer fineID;
    @SerializedName("amountOwed") @Expose private String amountOwed;
    
    public Object getSiteName() {
        return siteName;
    }
    
    public void setSiteName(Object siteName) {
        this.siteName = siteName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public Integer getFineID() {
        return fineID;
    }
    
    public void setFineID(Integer fineID) {
        this.fineID = fineID;
    }
    
    public String getAmountOwed() {
        return amountOwed;
    }
    
    public void setAmountOwed(String amountOwed) {
        this.amountOwed = amountOwed;
    }
    
}
