package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class IncludeItem {


    public IncludeItem() {
    }

    public IncludeItem(String includeItemName) {
        this.includeItemName = includeItemName;
      }

    public String getIncludeItemName() {
        return includeItemName;
    }

    public void setIncludeItemName(String includeItemName) {
        this.includeItemName = includeItemName;
    }

    private String includeItemName;
}
