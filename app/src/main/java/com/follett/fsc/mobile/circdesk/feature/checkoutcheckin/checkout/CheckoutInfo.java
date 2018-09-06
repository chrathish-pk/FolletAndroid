/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by muthulakshmi on 29/08/18.
 */

public class CheckoutInfo {

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
}
