/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InventoryList  implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("partialID")
    @Expose
    private Integer partialID;
    @SerializedName("dateStarted")
    @Expose
    private String dateStarted;

    private boolean isSelected;

    public InventoryList(String name, Integer partialID, String dateStarted, boolean isSelected) {
        this.name = name;
        this.partialID = partialID;
        this.dateStarted = dateStarted;
        this.isSelected = isSelected;
    }

    protected InventoryList(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            partialID = null;
        } else {
            partialID = in.readInt();
        }
        dateStarted = in.readString();
        isSelected = in.readByte() != 0;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartialID() {
        return partialID;
    }

    public void setPartialID(Integer partialID) {
        this.partialID = partialID;
    }

    public String getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (partialID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(partialID);
        }
        dest.writeString(dateStarted);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}

