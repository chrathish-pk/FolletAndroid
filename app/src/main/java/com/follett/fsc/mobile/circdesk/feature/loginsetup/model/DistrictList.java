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

import java.util.List;

public class DistrictList implements Parcelable {
    
    public DistrictList(List<District> districts) {
        this.districts = districts;
    }
    
    @SerializedName("districts") @Expose private List<District> districts = null;
    
    protected DistrictList(Parcel in) {
    }
    
    public static final Creator<DistrictList> CREATOR = new Creator<DistrictList>() {
        @Override
        public DistrictList createFromParcel(Parcel in) {
            return new DistrictList(in);
        }
        
        @Override
        public DistrictList[] newArray(int size) {
            return new DistrictList[size];
        }
    };
    
    public List<District> getDistricts() {
        return districts;
    }
    
    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
            // Do Nothing
    }
}