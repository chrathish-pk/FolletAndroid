package com.follett.fsc.mobile.circdesk.feature.inventory.model;

public class CheckoutHandling {


    private String checkoutHandlingName;
    private int checkoutHandlingID;
    private boolean isSelected;

    public CheckoutHandling(String checkoutHandlingName, int checkoutHandlingID) {
        this.checkoutHandlingName = checkoutHandlingName;
        this.checkoutHandlingID = checkoutHandlingID;
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

    public int getCheckoutHandlingID() {
        return checkoutHandlingID;
    }

    public void setCheckoutHandlingID(int checkoutHandlingID) {
        this.checkoutHandlingID = checkoutHandlingID;
    }
}
