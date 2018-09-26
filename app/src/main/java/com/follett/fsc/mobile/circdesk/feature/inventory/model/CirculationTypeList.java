
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CirculationTypeList {

    @SerializedName("circTypeList")
    @Expose
    private List<CircTypeList> circTypeList = null;

    public List<CircTypeList> getCircTypeList() {
        return circTypeList;
    }

    public void setCircTypeList(List<CircTypeList> circTypeList) {
        this.circTypeList = circTypeList;
    }

    public CirculationTypeList(List<CircTypeList> circTypeList) {
        this.circTypeList = circTypeList;
    }
}
