
package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResourceType {

    @SerializedName("resourceTypeList")
    @Expose
    private List<ResourceTypeList> resourceTypeList = null;

    public List<ResourceTypeList> getResourceTypeList() {
        return resourceTypeList;
    }

    public void setResourceTypeList(List<ResourceTypeList> resourceTypeList) {
        this.resourceTypeList = resourceTypeList;
    }

}
