
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CircTypeList {

    @SerializedName("circTypeID")
    @Expose
    private Integer circTypeID;
    @SerializedName("circTypeDescription")
    @Expose
    private String circTypeDescription;

    public Integer getCircTypeID() {
        return circTypeID;
    }

    public void setCircTypeID(Integer circTypeID) {
        this.circTypeID = circTypeID;
    }

    public String getCircTypeDescription() {
        return circTypeDescription;
    }

    public void setCircTypeDescription(String circTypeDescription) {
        this.circTypeDescription = circTypeDescription;
    }


    public CircTypeList(Integer circTypeID, String circTypeDescription) {
        this.circTypeID = circTypeID;
        this.circTypeDescription = circTypeDescription;
    }

}
