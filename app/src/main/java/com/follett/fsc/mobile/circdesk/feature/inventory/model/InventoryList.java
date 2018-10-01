/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class InventoryList implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("partialID")
    @Expose
    private Integer partialID;
    @SerializedName("dateStarted")
    @Expose
    private String dateStarted;


    protected InventoryList(Parcel in) {
        dateStarted = in.readString();
        name = in.readString();
        partialID = in.readInt();
    }

    public static final Creator<InventoryList> CREATOR = new Creator<InventoryList>() {
        @Override
        public InventoryList createFromParcel(Parcel in) {
            return new InventoryList(in);
        }

        @Override
        public InventoryList[] newArray(int size) {
            return new InventoryList[size];
        }
    };

    public String getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartialID() {
        return partialID;
    }

    public void setPartialID(int partialID) {
        this.partialID = partialID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(dateStarted);
        dest.writeString(name);
        dest.writeInt(partialID);
    }
}

