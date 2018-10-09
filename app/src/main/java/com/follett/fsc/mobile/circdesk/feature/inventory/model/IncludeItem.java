package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class IncludeItem {

    private String includeItemName;

    private boolean isSelected;

    public IncludeItem(String includeItemName) {
        this.includeItemName = includeItemName;
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
}
