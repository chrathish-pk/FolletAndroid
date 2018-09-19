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

import java.util.List;

public class CirculationTypeList implements Parcelable {


    @SerializedName("circulationType") @Expose
    private List<CirculationType> circulationTypes = null;

    protected CirculationTypeList(Parcel in) {
    }

    public static final Creator<CirculationTypeList> CREATOR = new Creator<CirculationTypeList>() {
        @Override
        public CirculationTypeList createFromParcel(Parcel in) {
            return new CirculationTypeList(in);
        }

        @Override
        public CirculationTypeList[] newArray(int size) {
            return new CirculationTypeList[size];
        }
    };

    public List<CirculationType> getCirculationTypes() {
        return circulationTypes;
    }

    public void setCirculationTypes(List<CirculationType> circulationTypes) {
        this.circulationTypes = circulationTypes;
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
