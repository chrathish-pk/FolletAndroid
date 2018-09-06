/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.model;

public class CustomCheckoutItem {
    
    private String checkoutTitle;
    private String bookName;
    private String barCode;
    private String dueDate;
    private Boolean isArrow;
    private Boolean isOverDue;
    
    public CustomCheckoutItem(String checkoutTitle, String bookName, String barCode, String dueDate, Boolean isArrow, Boolean isOverDue) {
        this.checkoutTitle = checkoutTitle;
        this.bookName = bookName;
        this.barCode = barCode;
        this.dueDate = dueDate;
        this.isArrow = isArrow;
        this.isOverDue = isOverDue;
    }
    
    public String getCheckoutTitle() {
        return checkoutTitle;
    }
    
    public String getBookName() {
        return bookName;
    }
    
    public String getBarCode() {
        return barCode;
    }
    
    public String getDueDate() {
        return dueDate;
    }
    
    public Boolean getTitle() {
        return isArrow;
    }
    
    public void setCheckoutTitle(String checkoutTitle) {
        this.checkoutTitle = checkoutTitle;
    }
    
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public void setTitle(Boolean title) {
        isArrow = title;
    }
    
    public Boolean getIsOverDue() {
        return isOverDue;
    }
    
    public void setIsOverDue(Boolean isOverDue) {
        this.isOverDue = isOverDue;
    }
}