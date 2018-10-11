package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import java.util.List;

public class ResourceTypeListData {

    private List<ResourceTypeList> resourceTypeListData;
    private int level;

    public ResourceTypeListData(List<ResourceTypeList> resourceTypeListData, int level) {
        this.resourceTypeListData = resourceTypeListData;
        this.level = level;
    }

    public List<ResourceTypeList> getResourceTypeListData() {
        return resourceTypeListData;
    }

    public void setResourceTypeListData(List<ResourceTypeList> resourceTypeListData) {
        this.resourceTypeListData = resourceTypeListData;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
