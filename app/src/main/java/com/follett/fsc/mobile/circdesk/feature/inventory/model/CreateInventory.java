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
    private int limiterType;
    private List<String> limiterValueList;
    private boolean limiterIncludeCheckedOutItems;
    private List<String> resourceTypeList;
    private int priceLimiterOption;
    private String priceLimiterValue;
    private int adoptionEndFrom;
    private int adoptionEndTo;
    private boolean includeBarcoded;
    private boolean includeUnbarcoded;
    private boolean includeConsumables;
    private boolean markCheckedOutItemsUnaccountedFor;
    private boolean checkinOnScan;
    private int locationSelection;

    public CreateInventory(int collectionType, String inventoryName, String accountedForSince, String callNumberFrom, String callNumberTo, List<String> sublocationList, List<CircTypeRecord> circTypeList, int limiterType, List<String> limiterValueList, boolean limiterIncludeCheckedOutItems, List<String> resourceTypeList, int priceLimiterOption, String priceLimiterValue, int adoptionEndFrom, int adoptionEndTo, boolean includeBarcoded, boolean includeUnbarcoded, boolean includeConsumables, boolean markCheckedOutItemsUnaccountedFor, boolean checkinOnScan, int locationSelection) {
        this.collectionType = collectionType;
        this.inventoryName = inventoryName;
        this.accountedForSince = accountedForSince;
        this.callNumberFrom = callNumberFrom;
        this.callNumberTo = callNumberTo;
        this.sublocationList = sublocationList;
        this.circTypeList = circTypeList;
        this.limiterType = limiterType;
        this.limiterValueList = limiterValueList;
        this.limiterIncludeCheckedOutItems = limiterIncludeCheckedOutItems;
        this.resourceTypeList = resourceTypeList;
        this.priceLimiterOption = priceLimiterOption;
        this.priceLimiterValue = priceLimiterValue;
        this.adoptionEndFrom = adoptionEndFrom;
        this.adoptionEndTo = adoptionEndTo;
        this.includeBarcoded = includeBarcoded;
        this.includeUnbarcoded = includeUnbarcoded;
        this.includeConsumables = includeConsumables;
        this.markCheckedOutItemsUnaccountedFor = markCheckedOutItemsUnaccountedFor;
        this.checkinOnScan = checkinOnScan;
        this.locationSelection = locationSelection;
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

    public int getLimiterType() {
        return limiterType;
    }

    public void setLimiterType(int limiterType) {
        this.limiterType = limiterType;
    }

    public List<String> getLimiterValueList() {
        return limiterValueList;
    }

    public void setLimiterValueList(List<String> limiterValueList) {
        this.limiterValueList = limiterValueList;
    }

    public boolean isLimiterIncludeCheckedOutItems() {
        return limiterIncludeCheckedOutItems;
    }

    public void setLimiterIncludeCheckedOutItems(boolean limiterIncludeCheckedOutItems) {
        this.limiterIncludeCheckedOutItems = limiterIncludeCheckedOutItems;
    }

    public List<String> getResourceTypeList() {
        return resourceTypeList;
    }

    public void setResourceTypeList(List<String> resourceTypeList) {
        this.resourceTypeList = resourceTypeList;
    }

    public int getPriceLimiterOption() {
        return priceLimiterOption;
    }

    public void setPriceLimiterOption(int priceLimiterOption) {
        this.priceLimiterOption = priceLimiterOption;
    }

    public String getPriceLimiterValue() {
        return priceLimiterValue;
    }

    public void setPriceLimiterValue(String priceLimiterValue) {
        this.priceLimiterValue = priceLimiterValue;
    }

    public int getAdoptionEndFrom() {
        return adoptionEndFrom;
    }

    public void setAdoptionEndFrom(int adoptionEndFrom) {
        this.adoptionEndFrom = adoptionEndFrom;
    }

    public int getAdoptionEndTo() {
        return adoptionEndTo;
    }

    public void setAdoptionEndTo(int adoptionEndTo) {
        this.adoptionEndTo = adoptionEndTo;
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

    public boolean isMarkCheckedOutItemsUnaccountedFor() {
        return markCheckedOutItemsUnaccountedFor;
    }

    public void setMarkCheckedOutItemsUnaccountedFor(boolean markCheckedOutItemsUnaccountedFor) {
        this.markCheckedOutItemsUnaccountedFor = markCheckedOutItemsUnaccountedFor;
    }

    public boolean isCheckinOnScan() {
        return checkinOnScan;
    }

    public void setCheckinOnScan(boolean checkinOnScan) {
        this.checkinOnScan = checkinOnScan;
    }

    public int getLocationSelection() {
        return locationSelection;
    }

    public void setLocationSelection(int locationSelection) {
        this.locationSelection = locationSelection;
    }
}
