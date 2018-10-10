package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class PurchasePriceItem {

    public PurchasePriceItem(String priceValueText) {
        this.priceValueText = priceValueText;
    }

    public String getPriceValueText() {
        return priceValueText;
    }

    public void setPriceValueText(String priceValueText) {
        this.priceValueText = priceValueText;
    }

    private String priceValueText;
}
