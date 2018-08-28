
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Patron implements Serializable{

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

}
