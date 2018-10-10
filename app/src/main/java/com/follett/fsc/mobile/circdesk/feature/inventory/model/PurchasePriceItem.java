package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class PurchasePriceItem {

    private String priceValueText;
    private int priceTypeID;

    public PurchasePriceItem(String priceValueText, int priceTypeID) {
        this.priceValueText = priceValueText;
        this.priceTypeID = priceTypeID;

    }

    public String getPriceValueText() {
        return priceValueText;
    }

    public void setPriceValueText(String priceValueText) {
        this.priceValueText = priceValueText;
    }

    public int getPriceTypeID() {
        return priceTypeID;
    }

    public void setPriceTypeID(int priceTypeID) {
        this.priceTypeID = priceTypeID;
    }
}
