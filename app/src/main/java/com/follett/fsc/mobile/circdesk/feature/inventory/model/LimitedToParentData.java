package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class LimitedToParentData {
    private String limitedToParentName;
    private SubLocation location;
    private boolean isSelected;

    public LimitedToParentData(String limitedToParentName, SubLocation location, boolean isSelected) {
        this.limitedToParentName = limitedToParentName;
        this.location = location;
        this.isSelected = isSelected;
    }

    public String getLimitedToParentName() {
        return limitedToParentName;
    }

    public void setLimitedToParentName(String limitedToParentName) {
        this.limitedToParentName = limitedToParentName;
    }

    public SubLocation getSubLocation() {
        return location;
    }

    public void setSubLocation(SubLocation location) {
        this.location = location;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
