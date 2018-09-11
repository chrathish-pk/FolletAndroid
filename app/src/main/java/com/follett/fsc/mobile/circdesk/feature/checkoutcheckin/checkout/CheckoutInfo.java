/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by muthulakshmi on 29/08/18.
 */

public class CheckoutInfo implements Parcelable {

    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("bibID")
    @Expose
    private String bibID;
    @SerializedName("coverURL")
    @Expose
    private String coverURL;
    @SerializedName("dueDate")
    @Expose
    private String dueDate;
    @SerializedName("materialType")
    @Expose
    private String materialType;
    @SerializedName("title")
    @Expose
    private String title;

    protected CheckoutInfo(Parcel in) {
        barcode = in.readString();
        bibID = in.readString();
        coverURL = in.readString();
        dueDate = in.readString();
        materialType = in.readString();
        title = in.readString();
    }

    public static final Creator<CheckoutInfo> CREATOR = new Creator<CheckoutInfo>() {
        @Override
        public CheckoutInfo createFromParcel(Parcel in) {
            return new CheckoutInfo(in);
        }

        @Override
        public CheckoutInfo[] newArray(int size) {
            return new CheckoutInfo[size];
        }
    };

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBibID() {
        return bibID;
    }

    public void setBibID(String bibID) {
        this.bibID = bibID;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(barcode);
        dest.writeString(bibID);
        dest.writeString(coverURL);
        dest.writeString(dueDate);
        dest.writeString(materialType);
        dest.writeString(title);
    }
}
