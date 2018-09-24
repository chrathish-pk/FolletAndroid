
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Info implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("dueDate")
    @Expose
    private String dueDate;
    @SerializedName("bibID")
    @Expose
    private Integer bibID;
    @SerializedName("materialType")
    @Expose
    private Integer materialType;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("coverURL")
    @Expose
    private String coverURL;


    public Info(String title, String dueDate, Integer bibID, Integer materialType, String barcode, String coverURL) {
        this.title = title;
        this.dueDate = dueDate;
        this.bibID = bibID;
        this.materialType = materialType;
        this.barcode = barcode;
        this.coverURL = coverURL;
    }

    protected Info(Parcel in) {
        title = in.readString();
        dueDate = in.readString();
        if (in.readByte() == 0) {
            bibID = null;
        } else {
            bibID = in.readInt();
        }
        if (in.readByte() == 0) {
            materialType = null;
        } else {
            materialType = in.readInt();
        }
        barcode = in.readString();
        coverURL = in.readString();
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getBibID() {
        return bibID;
    }

    public void setBibID(Integer bibID) {
        this.bibID = bibID;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(dueDate);
        if (bibID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bibID);
        }
        if (materialType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(materialType);
        }
        dest.writeString(barcode);
        dest.writeString(coverURL);
    }
}
