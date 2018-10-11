package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class LimitedToParentData {
    private String limitedToParentName;
    private int limitedToParentID;
    private Location location;
    private boolean isSelected;

    public LimitedToParentData(String limitedToParentName,int limitedToParentID, Location location, boolean isSelected) {
        this.limitedToParentName = limitedToParentName;
        this.limitedToParentID=limitedToParentID;
        this.location = location;
        this.isSelected = isSelected;
    }

    public String getLimitedToParentName() {
        return limitedToParentName;
    }

    public void setLimitedToParentName(String limitedToParentName) {
        this.limitedToParentName = limitedToParentName;
    }

    public Location getSubLocation() {
        return location;
    }

    public void setSubLocation(Location location) {
        this.location = location;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getLimitedToParentID() {
        return limitedToParentID;
    }

    public void setLimitedToParentID(int limitedToParentID) {
        this.limitedToParentID = limitedToParentID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
