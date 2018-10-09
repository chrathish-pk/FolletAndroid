package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class CheckoutHandling {


    private String checkoutHandlingName;
    private boolean isSelected;

    public CheckoutHandling(String checkoutHandlingName) {
        this.checkoutHandlingName = checkoutHandlingName;
    }

    public String getCheckoutHandlingName() {
        return checkoutHandlingName;
    }

    public void setCheckoutHandlingName(String checkoutHandlingName) {
        this.checkoutHandlingName = checkoutHandlingName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
