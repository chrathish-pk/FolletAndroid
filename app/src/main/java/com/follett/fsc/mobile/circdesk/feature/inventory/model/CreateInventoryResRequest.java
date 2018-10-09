package com.follett.fsc.mobile.circdesk.feature.inventory.model;

import java.util.List;

public class CreateInventoryResRequest {

private List<Integer> resourceTypeList;
private Integer collectionType;
private String inventoryName;
private boolean includeBarcoded;
private boolean includeUnbarcoded;
private boolean includeConsumables;
private boolean checkinOnScan;
private boolean markCheckedOutItemsUnaccountedFor;
private String accountedForSince;
private String priceLimiterValue;
private String priceLimiterOption;
private boolean limiterIncludeCheckedOutItems;
private String limiterType;
private List<Integer> limiterValueList;


    public CreateInventoryResRequest(List<Integer> resourceTypeList, Integer collectionType, String inventoryName, boolean includeBarcoded, boolean includeUnbarcoded, boolean includeConsumables, boolean checkinOnScan, boolean markCheckedOutItemsUnaccountedFor, String accountedForSince, String priceLimiterValue, String priceLimiterOption, boolean limiterIncludeCheckedOutItems, String limiterType, List<Integer> limiterValueList) {
        this.resourceTypeList = resourceTypeList;
        this.collectionType = collectionType;
        this.inventoryName = inventoryName;
        this.includeBarcoded = includeBarcoded;
        this.includeUnbarcoded = includeUnbarcoded;
        this.includeConsumables = includeConsumables;
        this.checkinOnScan = checkinOnScan;
        this.markCheckedOutItemsUnaccountedFor = markCheckedOutItemsUnaccountedFor;
        this.accountedForSince = accountedForSince;
        this.priceLimiterValue = priceLimiterValue;
        this.priceLimiterOption = priceLimiterOption;
        this.limiterIncludeCheckedOutItems = limiterIncludeCheckedOutItems;
        this.limiterType = limiterType;
        this.limiterValueList = limiterValueList;
    }

    public List<Integer> getResourceTypeList() {
        return resourceTypeList;
    }

    public void setResourceTypeList(List<Integer> resourceTypeList) {
        this.resourceTypeList = resourceTypeList;
    }

    public Integer getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(Integer collectionType) {
        this.collectionType = collectionType;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public boolean isIncludeBarcoded() {
        return includeBarcoded;
    }

    public void setIncludeBarcoded(boolean includeBarcoded) {
        this.includeBarcoded = includeBarcoded;
    }

    public boolean isIncludeUnbarcoded() {
        return includeUnbarcoded;
    }

    public void setIncludeUnbarcoded(boolean includeUnbarcoded) {
        this.includeUnbarcoded = includeUnbarcoded;
    }

    public boolean isIncludeConsumables() {
        return includeConsumables;
    }

    public void setIncludeConsumables(boolean includeConsumables) {
        this.includeConsumables = includeConsumables;
    }

    public boolean isCheckinOnScan() {
        return checkinOnScan;
    }

    public void setCheckinOnScan(boolean checkinOnScan) {
        this.checkinOnScan = checkinOnScan;
    }

    public boolean isMarkCheckedOutItemsUnaccountedFor() {
        return markCheckedOutItemsUnaccountedFor;
    }

    public void setMarkCheckedOutItemsUnaccountedFor(boolean markCheckedOutItemsUnaccountedFor) {
        this.markCheckedOutItemsUnaccountedFor = markCheckedOutItemsUnaccountedFor;
    }

    public String getAccountedForSince() {
        return accountedForSince;
    }

    public void setAccountedForSince(String accountedForSince) {
        this.accountedForSince = accountedForSince;
    }

    public String getPriceLimiterValue() {
        return priceLimiterValue;
    }

    public void setPriceLimiterValue(String priceLimiterValue) {
        this.priceLimiterValue = priceLimiterValue;
    }

    public String getPriceLimiterOption() {
        return priceLimiterOption;
    }

    public void setPriceLimiterOption(String priceLimiterOption) {
        this.priceLimiterOption = priceLimiterOption;
    }

    public boolean isLimiterIncludeCheckedOutItems() {
        return limiterIncludeCheckedOutItems;
    }

    public void setLimiterIncludeCheckedOutItems(boolean limiterIncludeCheckedOutItems) {
        this.limiterIncludeCheckedOutItems = limiterIncludeCheckedOutItems;
    }

    public String getLimiterType() {
        return limiterType;
    }

    public void setLimiterType(String limiterType) {
        this.limiterType = limiterType;
    }

    public List<Integer> getLimiterValueList() {
        return limiterValueList;
    }

    public void setLimiterValueList(List<Integer> limiterValueList) {
        this.limiterValueList = limiterValueList;
    }
}
