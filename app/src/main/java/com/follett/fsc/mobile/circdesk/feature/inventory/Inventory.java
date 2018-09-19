/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

/**
 * Created by muthulakshmi on 18/09/18.
 */

public class Inventory {

    private String inventoryName;

    private boolean isSelected;


    public Inventory(String inventoryName, boolean isSelected) {
        this.inventoryName = inventoryName;
        this.isSelected = isSelected;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
