/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "loginResults")
public class LoginResults {

    /*@Element(name = "firstName")
    private String firstName;*/

    @Element(name = "guest") private String guest;

    @Element(name = "invalidUsernameOrPassword") private String invalidUsernameOrPassword;

    @Element(name = "lastName") private String lastName;

    @Element(name = "listID") private String listID;

    @Element(name = "listSize") private String listSize;

    @Element(name = "lockedOut") private String lockedOut;

    @Element(name = "middleName") private String middleName;

    @Element(name = "myListsTotalSize") private String myListsTotalSize;

    @Element(name = "numberOfMyLists") private String numberOfMyLists;

    @Element(name = "passwordExpired") private String passwordExpired;

    @Element(name = "passwordNotSecure") private String passwordNotSecure;

    @Element(name = "sessionID") private String sessionID;

    @Element(name = "sessionTimeoutInMillis") private String sessionTimeoutInMillis;

    @Element(name = "success") private String success;

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getInvalidUsernameOrPassword() {
        return invalidUsernameOrPassword;
    }

    public void setInvalidUsernameOrPassword(String invalidUsernameOrPassword) {
        this.invalidUsernameOrPassword = invalidUsernameOrPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
    }

    public String getListSize() {
        return listSize;
    }

    public void setListSize(String listSize) {
        this.listSize = listSize;
    }

    public String getLockedOut() {
        return lockedOut;
    }

    public void setLockedOut(String lockedOut) {
        this.lockedOut = lockedOut;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMyListsTotalSize() {
        return myListsTotalSize;
    }

    public void setMyListsTotalSize(String myListsTotalSize) {
        this.myListsTotalSize = myListsTotalSize;
    }

    public String getNumberOfMyLists() {
        return numberOfMyLists;
    }

    public void setNumberOfMyLists(String numberOfMyLists) {
        this.numberOfMyLists = numberOfMyLists;
    }

    public String getPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(String passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    public String getPasswordNotSecure() {
        return passwordNotSecure;
    }

    public void setPasswordNotSecure(String passwordNotSecure) {
        this.passwordNotSecure = passwordNotSecure;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionTimeoutInMillis() {
        return sessionTimeoutInMillis;
    }

    public void setSessionTimeoutInMillis(String sessionTimeoutInMillis) {
        this.sessionTimeoutInMillis = sessionTimeoutInMillis;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
