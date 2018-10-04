package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class CheckoutHandling {

    public CheckoutHandling(String checkoutHandlingName) {
        this.checkoutHandlingName = checkoutHandlingName;
    }

    public String getCheckoutHandlingName() {
        return checkoutHandlingName;
    }

    public void setCheckoutHandlingName(String checkoutHandlingName) {
        this.checkoutHandlingName = checkoutHandlingName;
    }

    private String checkoutHandlingName;

}
