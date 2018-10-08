package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class CirculationID {

    private Integer circTypeID;

    public CirculationID(Integer circTypeID) {
        this.circTypeID = circTypeID;
    }

    public Integer getCircTypeID() {
        return circTypeID;
    }

    public void setCircTypeID(Integer circTypeID) {
        this.circTypeID = circTypeID;
    }
}
