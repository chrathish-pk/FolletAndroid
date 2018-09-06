
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Fine implements Parcelable {
    
    @SerializedName("siteName") @Expose private Object siteName;
    @SerializedName("description") @Expose private String description;
    @SerializedName("reason") @Expose private String reason;
    @SerializedName("fineID") @Expose private Integer fineID;
    @SerializedName("amountOwed") @Expose private String amountOwed;
    
    protected Fine(Parcel in) {
        description = in.readString();
        reason = in.readString();
        if (in.readByte() == 0) { fineID = null; } else { fineID = in.readInt(); }
        amountOwed = in.readString();
    }
    
    public static final Creator<Fine> CREATOR = new Creator<Fine>() {
        @Override
        public Fine createFromParcel(Parcel in) {
            return new Fine(in);
        }
        
        @Override
        public Fine[] newArray(int size) {
            return new Fine[size];
        }
    };
    
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
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
    
        parcel.writeString(description);
        parcel.writeString(reason);
        if (fineID == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fineID);
        }
        parcel.writeString(amountOwed);
    }
}
