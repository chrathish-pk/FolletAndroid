
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

import java.util.List;

public class PatronInfo implements Parcelable {
    
    @SerializedName("assetCheckOuts") @Expose private List<AssetCheckOut> assetCheckOuts = null;
    @SerializedName("patronList") @Expose private List<PatronList> patronList = null;
    @SerializedName("notes") @Expose private List<Object> notes = null;
    @SerializedName("success") @Expose private Boolean success;
    @SerializedName("textbookCheckOuts") @Expose private List<Object> textbookCheckOuts = null;
    @SerializedName("pictureName") @Expose private Object pictureName;
    @SerializedName("barcode") @Expose private String barcode;
    @SerializedName("holds") @Expose private List<Object> holds = null;
    @SerializedName("fineTotalString") @Expose private String fineTotalString;
    @SerializedName("fines") @Expose private List<Fine> fines = null;
    @SerializedName("lastName") @Expose private String lastName;
    @SerializedName("firstName") @Expose private String firstName;
    @SerializedName("checkouts") @Expose private List<Checkout> checkouts = null;
    
    protected PatronInfo(Parcel in) {
        assetCheckOuts = in.createTypedArrayList(AssetCheckOut.CREATOR);
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        barcode = in.readString();
        fineTotalString = in.readString();
        fines = in.createTypedArrayList(Fine.CREATOR);
        lastName = in.readString();
        firstName = in.readString();
        checkouts = in.createTypedArrayList(Checkout.CREATOR);
    }
    
    public static final Creator<PatronInfo> CREATOR = new Creator<PatronInfo>() {
        @Override
        public PatronInfo createFromParcel(Parcel in) {
            return new PatronInfo(in);
        }
        
        @Override
        public PatronInfo[] newArray(int size) {
            return new PatronInfo[size];
        }
    };
    
    public List<AssetCheckOut> getAssetCheckOuts() {
        return assetCheckOuts;
    }
    
    public void setAssetCheckOuts(List<AssetCheckOut> assetCheckOuts) {
        this.assetCheckOuts = assetCheckOuts;
    }
    
    public List<PatronList> getPatronList() {
        return patronList;
    }
    
    public void setPatronList(List<PatronList> patronList) {
        this.patronList = patronList;
    }
    
    public List<Object> getNotes() {
        return notes;
    }
    
    public void setNotes(List<Object> notes) {
        this.notes = notes;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public List<Object> getTextbookCheckOuts() {
        return textbookCheckOuts;
    }
    
    public void setTextbookCheckOuts(List<Object> textbookCheckOuts) {
        this.textbookCheckOuts = textbookCheckOuts;
    }
    
    public Object getPictureName() {
        return pictureName;
    }
    
    public void setPictureName(Object pictureName) {
        this.pictureName = pictureName;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public List<Object> getHolds() {
        return holds;
    }
    
    public void setHolds(List<Object> holds) {
        this.holds = holds;
    }
    
    public String getFineTotalString() {
        return fineTotalString;
    }
    
    public void setFineTotalString(String fineTotalString) {
        this.fineTotalString = fineTotalString;
    }
    
    public List<Fine> getFines() {
        return fines;
    }
    
    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public List<Checkout> getCheckouts() {
        return checkouts;
    }
    
    public void setCheckouts(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
    
        parcel.writeTypedList(assetCheckOuts);
        parcel.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
        parcel.writeString(barcode);
        parcel.writeString(fineTotalString);
        parcel.writeTypedList(fines);
        parcel.writeString(lastName);
        parcel.writeString(firstName);
        parcel.writeTypedList(checkouts);
    }
}
