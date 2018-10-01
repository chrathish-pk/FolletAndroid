
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

    private boolean isSelected;


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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public CircTypeList(Integer circTypeID, String circTypeDescription, boolean isSelected) {
        this.circTypeID = circTypeID;
        this.circTypeDescription = circTypeDescription;
        this.isSelected = isSelected;
    }

}
