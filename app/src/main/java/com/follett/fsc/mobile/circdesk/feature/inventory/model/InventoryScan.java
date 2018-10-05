/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.inventory.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InventoryScan {
    
    @SerializedName("info") @Expose private Info info;
    @SerializedName("newLocationID") @Expose private Object newLocationID;
    @SerializedName("success") @Expose private Boolean success;
    @SerializedName("notes") @Expose private List<Object> notes = null;
    @SerializedName("locationChangeMessage") @Expose private Object locationChangeMessage;
    @SerializedName("copyIDToUpdateLocation") @Expose private Object copyIDToUpdateLocation;
    @SerializedName("messages") @Expose private List<Object> messages = null;
    
    public Info getInfo() {
        return info;
    }
    
    public void setInfo(Info info) {
        this.info = info;
    }
    
    public Object getNewLocationID() {
        return newLocationID;
    }
    
    public void setNewLocationID(Object newLocationID) {
        this.newLocationID = newLocationID;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public List<Object> getNotes() {
        return notes;
    }
    
    public void setNotes(List<Object> notes) {
        this.notes = notes;
    }
    
    public Object getLocationChangeMessage() {
        return locationChangeMessage;
    }
    
    public void setLocationChangeMessage(Object locationChangeMessage) {
        this.locationChangeMessage = locationChangeMessage;
    }
    
    public Object getCopyIDToUpdateLocation() {
        return copyIDToUpdateLocation;
    }
    
    public void setCopyIDToUpdateLocation(Object copyIDToUpdateLocation) {
        this.copyIDToUpdateLocation = copyIDToUpdateLocation;
    }
    
    public List<Object> getMessages() {
        return messages;
    }
    
    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }
    
}
