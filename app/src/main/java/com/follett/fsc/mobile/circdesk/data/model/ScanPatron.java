
package com.follett.fsc.mobile.circdesk.data.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ScanPatron  implements Serializable{

    @SerializedName("scanPatronResult")
    @Expose
    private ScanPatronResult scanPatronResult;

    protected ScanPatron(Parcel in) {
    }



    public ScanPatronResult getScanPatronResult() {
        return scanPatronResult;
    }

    public void setScanPatronResult(ScanPatronResult scanPatronResult) {
        this.scanPatronResult = scanPatronResult;
    }

}
