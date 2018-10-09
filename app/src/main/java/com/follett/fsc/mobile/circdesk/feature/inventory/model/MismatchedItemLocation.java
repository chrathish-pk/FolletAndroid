package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class MismatchedItemLocation {


    public MismatchedItemLocation(String mismatchedItemLocationName,int mismatchedItemLocationStatus) {
        this.mismatchedItemLocationName = mismatchedItemLocationName;
        this.mismatchedItemLocationStatus = mismatchedItemLocationStatus;
    }

    public String getMismatchedItemLocationName() {
        return mismatchedItemLocationName;
    }

    public void setMismatchedItemLocationName(String mismatchedItemLocationName) {
        this.mismatchedItemLocationName = mismatchedItemLocationName;
    }

    private String mismatchedItemLocationName;

    public int getMismatchedItemLocationStatus() {
        return mismatchedItemLocationStatus;
    }

    public void setMismatchedItemLocationStatus(int mismatchedItemLocationStatus) {
        this.mismatchedItemLocationStatus = mismatchedItemLocationStatus;
    }

    private int mismatchedItemLocationStatus;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
