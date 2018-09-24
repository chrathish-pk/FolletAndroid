/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class District implements Parcelable {
    
    public District(String contextName, String districtName) {
        this.contextName = contextName;
        this.districtName = districtName;
    }
    
    @SerializedName("contextName") @Expose private String contextName;
    @SerializedName("districtName") @Expose private String districtName;
    
    protected District(Parcel in) {
        contextName = in.readString();
        districtName = in.readString();
    }
    
    public static final Creator<District> CREATOR = new Creator<District>() {
        @Override
        public District createFromParcel(Parcel in) {
            return new District(in);
        }
        
        @Override
        public District[] newArray(int size) {
            return new District[size];
        }
    };
    
    public String getContextName() {
        return contextName;
    }
    
    public void setContextName(String contextName) {
        this.contextName = contextName;
    }
    
    public String getDistrictName() {
        return districtName;
    }
    
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        
        parcel.writeString(contextName);
        parcel.writeString(districtName);
    }
}
