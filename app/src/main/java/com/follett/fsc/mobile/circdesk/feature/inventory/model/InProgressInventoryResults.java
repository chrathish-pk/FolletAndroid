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

import java.util.List;

public class InProgressInventoryResults implements Parcelable {

    @SerializedName("inventoryList")
    @Expose
    private List<InventoryList> inventoryList = null;

    protected InProgressInventoryResults(Parcel in) {
        inventoryList = in.createTypedArrayList(InventoryList.CREATOR);
    }

    public static final Creator<InProgressInventoryResults> CREATOR = new Creator<InProgressInventoryResults>() {
        @Override
        public InProgressInventoryResults createFromParcel(Parcel in) {
            return new InProgressInventoryResults(in);
        }

        @Override
        public InProgressInventoryResults[] newArray(int size) {
            return new InProgressInventoryResults[size];
        }
    };

    public List<InventoryList> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<InventoryList> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(inventoryList);
    }
}
