
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

public class Patron implements Parcelable{

    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("lastFirstMiddleName")
    @Expose
    private String lastFirstMiddleName;
    @SerializedName("patronID")
    @Expose
    private String patronID;
    @SerializedName("patronPictureFileName")
    @Expose
    private String patronPictureFileName;


    public Patron(String barcode, String lastFirstMiddleName, String patronID, String patronPictureFileName) {
        this.barcode = barcode;
        this.lastFirstMiddleName = lastFirstMiddleName;
        this.patronID = patronID;
        this.patronPictureFileName = patronPictureFileName;
    }

    protected Patron(Parcel in) {
        barcode = in.readString();
        lastFirstMiddleName = in.readString();
        patronID = in.readString();
        patronPictureFileName = in.readString();
    }

    public static final Creator<Patron> CREATOR = new Creator<Patron>() {
        @Override
        public Patron createFromParcel(Parcel in) {
            return new Patron(in);
        }

        @Override
        public Patron[] newArray(int size) {
            return new Patron[size];
        }
    };

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLastFirstMiddleName() {
        return lastFirstMiddleName;
    }

    public void setLastFirstMiddleName(String lastFirstMiddleName) {
        this.lastFirstMiddleName = lastFirstMiddleName;
    }

    public String getPatronID() {
        return patronID;
    }

    public void setPatronID(String patronID) {
        this.patronID = patronID;
    }

    public String getPatronPictureFileName() {
        return patronPictureFileName;
    }

    public void setPatronPictureFileName(String patronPictureFileName) {
        this.patronPictureFileName = patronPictureFileName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(barcode);
        dest.writeString(lastFirstMiddleName);
        dest.writeString(patronID);
        dest.writeString(patronPictureFileName);
    }
}
