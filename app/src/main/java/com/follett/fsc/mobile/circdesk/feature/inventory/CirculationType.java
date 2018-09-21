/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CirculationType implements Parcelable {

    @SerializedName("circulationTypeName") @Expose
    private String circulationTypeName;

    protected CirculationType(Parcel in) {
        circulationTypeName = in.readString();
    }

    public static final Creator<CirculationType> CREATOR = new Creator<CirculationType>() {
        @Override
        public CirculationType createFromParcel(Parcel in) {
            return new CirculationType(in);
        }

        @Override
        public CirculationType[] newArray(int size) {
            return new CirculationType[size];
        }
    };

    public String getCirculationTypeName() {
        return circulationTypeName;
    }

    public void setCirculationTypeName(String circulationTypeName) {
        this.circulationTypeName = circulationTypeName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(circulationTypeName);
    }
}