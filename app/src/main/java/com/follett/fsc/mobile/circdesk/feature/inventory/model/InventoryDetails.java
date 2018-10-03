package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InventoryDetails implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("completePercentage")
    @Expose
    private String completePercentage;
    @SerializedName("partialID")
    @Expose
    private Integer partialID;

    public InventoryDetails(String name, String completePercentage, int partialID) {
        this.name = name;
        this.completePercentage = completePercentage;
        this.partialID = partialID;
    }

    protected InventoryDetails(Parcel in) {
        name = in.readString();
        completePercentage = in.readString();
        partialID = in.readInt();
    }

    public static final Creator<InventoryDetails> CREATOR = new Creator<InventoryDetails>() {
        @Override
        public InventoryDetails createFromParcel(Parcel in) {
            return new InventoryDetails(in);
        }

        @Override
        public InventoryDetails[] newArray(int size) {
            return new InventoryDetails[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompletePercentage() {
        return completePercentage;
    }

    public void setCompletePercentage(String completePercentage) {
        this.completePercentage = completePercentage;
    }

    public Integer getPartialID() {
        return partialID;
    }

    public void setPartialID(Integer partialID) {
        this.partialID = partialID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeString(completePercentage);
        dest.writeInt(partialID);
    }
}
