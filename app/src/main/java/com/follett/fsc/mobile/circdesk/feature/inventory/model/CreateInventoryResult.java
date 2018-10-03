package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class CreateInventoryResult {

    private boolean success;
    private String message;
    private int partialID;

    public CreateInventoryResult(boolean success, String message, int partialID) {
        this.success = success;
        this.message = message;
        this.partialID = partialID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPartialID() {
        return partialID;
    }

    public void setPartialID(int partialID) {
        this.partialID = partialID;
    }
}
