package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import java.util.List;

public class CreateInventoryLibRequest {

    private  int collectionType;
    private String inventoryName;
    private String accountedForSince;
    private String callNumberFrom;
    private String callNumberTo;
    private List<Integer> sublocationList;
    private List<Integer> circTypeList;


    public CreateInventoryLibRequest(int collectionType, String inventoryName, String accountedForSince, String callNumberFrom, String callNumberTo, List<Integer> sublocationList, List<Integer> circTypeList) {
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

    public List<Integer> getSublocationList() {
        return sublocationList;
    }

    public void setSublocationList(List<Integer> sublocationList) {
        this.sublocationList = sublocationList;
    }

    public List<Integer> getCircTypeList() {
        return circTypeList;
    }

    public void setCircTypeList(List<Integer> circTypeList) {
        this.circTypeList = circTypeList;
    }
}
