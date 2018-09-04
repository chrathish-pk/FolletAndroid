
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatronList {
    
    @SerializedName("patronPictureFileName") @Expose private Object patronPictureFileName;
    @SerializedName("patronID") @Expose private Integer patronID;
    @SerializedName("gradeLevel") @Expose private Object gradeLevel;
    @SerializedName("lastFirstMiddleName") @Expose private String lastFirstMiddleName;
    @SerializedName("barcode") @Expose private String barcode;
    @SerializedName("homeroom") @Expose private Object homeroom;
    
    public Object getPatronPictureFileName() {
        return patronPictureFileName;
    }
    
    public void setPatronPictureFileName(Object patronPictureFileName) {
        this.patronPictureFileName = patronPictureFileName;
    }
    
    public Integer getPatronID() {
        return patronID;
    }
    
    public void setPatronID(Integer patronID) {
        this.patronID = patronID;
    }
    
    public Object getGradeLevel() {
        return gradeLevel;
    }
    
    public void setGradeLevel(Object gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    
    public String getLastFirstMiddleName() {
        return lastFirstMiddleName;
    }
    
    public void setLastFirstMiddleName(String lastFirstMiddleName) {
        this.lastFirstMiddleName = lastFirstMiddleName;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public Object getHomeroom() {
        return homeroom;
    }
    
    public void setHomeroom(Object homeroom) {
        this.homeroom = homeroom;
    }
    
}
