package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InventorySelectionCriteria implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("startedByUser")
    @Expose
    private String startedByUser;
    @SerializedName("dateStarted")
    @Expose
    private String dateStarted;
    @SerializedName("partialID")
    @Expose
    private Integer partialID;
    @SerializedName("itemList")
    @Expose
    private List<SelectionCriteriaItemList> itemList = null;

    public InventorySelectionCriteria(List<SelectionCriteriaItemList> itemList) {
        this.itemList = itemList;
    }

    protected InventorySelectionCriteria(Parcel in) {
        name = in.readString();
        startedByUser = in.readString();
        dateStarted = in.readString();
        partialID = in.readInt();
        itemList = in.createTypedArrayList(SelectionCriteriaItemList.CREATOR);
    }

    public static final Creator<InventorySelectionCriteria> CREATOR = new Creator<InventorySelectionCriteria>() {
        @Override
        public InventorySelectionCriteria createFromParcel(Parcel in) {
            return new InventorySelectionCriteria(in);
        }

        @Override
        public InventorySelectionCriteria[] newArray(int size) {
            return new InventorySelectionCriteria[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartedByUser() {
        return startedByUser;
    }

    public void setStartedByUser(String startedByUser) {
        this.startedByUser = startedByUser;
    }

    public String getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Integer getPartialID() {
        return partialID;
    }

    public void setPartialID(Integer partialID) {
        this.partialID = partialID;
    }

    public List<SelectionCriteriaItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<SelectionCriteriaItemList> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeString(startedByUser);
        dest.writeString(dateStarted);
        dest.writeInt(partialID);
        dest.writeTypedList(itemList);
    }
}
