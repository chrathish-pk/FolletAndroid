package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class IncludeItem {

    private String includeItemName;
    private int includeItemID;

    private boolean isSelected;

    public IncludeItem(String includeItemName, int includeItemID) {
        this.includeItemName = includeItemName;
        this.includeItemID = includeItemID;
    }

    public String getIncludeItemName() {
        return includeItemName;
    }

    public void setIncludeItemName(String includeItemName) {
        this.includeItemName = includeItemName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getIncludeItemID() {
        return includeItemID;
    }

    public void setIncludeItemID(int includeItemID) {
        this.includeItemID = includeItemID;
    }
}
