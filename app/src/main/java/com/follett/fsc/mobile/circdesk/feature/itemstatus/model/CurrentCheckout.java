
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.itemstatus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrentCheckout implements Parcelable
{
    @SerializedName("dateDue")
    @Expose
    private String dateDue;
    @SerializedName("dateOut")
    @Expose
    private String dateOut;
    @SerializedName("dateReturned")
    @Expose
    private String dateReturned;
    @SerializedName("checkedOutToName")
    @Expose
    private String checkedOutToName;
    @SerializedName("checkedOutToBarcode")
    @Expose
    private String checkedOutToBarcode;

    protected CurrentCheckout(Parcel in) {
        dateDue = in.readString();
        dateOut = in.readString();
        dateReturned = in.readString();
        checkedOutToName = in.readString();
        checkedOutToBarcode = in.readString();
    }

    public static final Creator<CurrentCheckout> CREATOR = new Creator<CurrentCheckout>() {
        @Override
        public CurrentCheckout createFromParcel(Parcel in) {
            return new CurrentCheckout(in);
        }

        @Override
        public CurrentCheckout[] newArray(int size) {
            return new CurrentCheckout[size];
        }
    };

    public boolean isEmpty() {
        return checkedOutToName == null && checkedOutToBarcode == null;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public String getCheckedOutToName() {
        return checkedOutToName;
    }

    public void setCheckedOutToName(String checkedOutToName) {
        this.checkedOutToName = checkedOutToName;
    }

    public String getCheckedOutToBarcode() {
        return checkedOutToBarcode;
    }

    public void setCheckedOutToBarcode(String checkedOutToBarcode) {
        this.checkedOutToBarcode = checkedOutToBarcode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateDue);
        dest.writeString(dateOut);
        dest.writeString(dateReturned);
        dest.writeString(checkedOutToName);
        dest.writeString(checkedOutToBarcode);
    }
}