package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import java.util.List;

public class CreateInventory  {

    private  int collectionType;
    private String inventoryName;
    private String accountedForSince;
    private String callNumberFrom;
    private String callNumberTo;
    private List<String> sublocationList;
    private List<CircTypeRecord> circTypeList;


    public CreateInventory(int collectionType, String inventoryName, String accountedForSince, String callNumberFrom, String callNumberTo, List<String> sublocationList, List<CircTypeRecord> circTypeList) {
        this.collectionType = collectionType;
        this.inventoryName = inventoryName;
        this.accountedForSince = accountedForSince;
        this.callNumberFrom = callNumberFrom;
        this.callNumberTo = callNumberTo;
        this.sublocationList = sublocationList;
        this.circTypeList = circTypeList;
    }

    public int getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(int collectionType) {
        this.collectionType = collectionType;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getAccountedForSince() {
        return accountedForSince;
    }

    public void setAccountedForSince(String accountedForSince) {
        this.accountedForSince = accountedForSince;
    }

    public String getCallNumberFrom() {
        return callNumberFrom;
    }

    public void setCallNumberFrom(String callNumberFrom) {
        this.callNumberFrom = callNumberFrom;
    }

    public String getCallNumberTo() {
        return callNumberTo;
    }

    public void setCallNumberTo(String callNumberTo) {
        this.callNumberTo = callNumberTo;
    }

    public List<String> getSublocationList() {
        return sublocationList;
    }

    public void setSublocationList(List<String> sublocationList) {
        this.sublocationList = sublocationList;
    }

    public List<CircTypeRecord> getCircTypeList() {
        return circTypeList;
    }

    public void setCircTypeList(List<CircTypeRecord> circTypeList) {
        this.circTypeList = circTypeList;
    }
}
