
package com.follett.fsc.mobile.circdesk.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScanPatron  implements Parcelable{

    @SerializedName("scanPatronResult")
    @Expose
    private ScanPatronResult scanPatronResult;

    protected ScanPatron(Parcel in) {
    }

    public static final Creator<ScanPatron> CREATOR = new Creator<ScanPatron>() {
        @Override
        public ScanPatron createFromParcel(Parcel in) {
            return new ScanPatron(in);
        }

        @Override
        public ScanPatron[] newArray(int size) {
            return new ScanPatron[size];
        }
    };

    public ScanPatronResult getScanPatronResult() {
        return scanPatronResult;
    }

    public void setScanPatronResult(ScanPatronResult scanPatronResult) {
        this.scanPatronResult = scanPatronResult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
