package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class NewInventoryData {

    private String inventoryName;
    private String inventoryValue;

    public NewInventoryData(String inventoryName, String inventoryValue) {
        this.inventoryName = inventoryName;
        this.inventoryValue = inventoryValue;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(String inventoryValue) {
        this.inventoryValue = inventoryValue;
    }
}
