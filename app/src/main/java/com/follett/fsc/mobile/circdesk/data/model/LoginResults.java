/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResults {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("guest")
    @Expose
    private String guest;
    @SerializedName("invalidUsernameOrPassword")
    @Expose
    private String invalidUsernameOrPassword;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("listID")
    @Expose
    private String listID;
    @SerializedName("listSize")
    @Expose
    private String listSize;
    @SerializedName("lockedOut")
    @Expose
    private String lockedOut;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("myListsTotalSize")
    @Expose
    private String myListsTotalSize;
    @SerializedName("numberOfMyLists")
    @Expose
    private String numberOfMyLists;
    @SerializedName("passwordExpired")
    @Expose
    private String passwordExpired;
    @SerializedName("passwordNotSecure")
    @Expose
    private String passwordNotSecure;
    @SerializedName("permissions")
    @Expose
    private Permissions permissions;
    @SerializedName("sessionID")
    @Expose
    private String sessionID;
    @SerializedName("sessionTimeoutInMillis")
    @Expose
    private String sessionTimeoutInMillis;
    @SerializedName("success")
    @Expose
    private String success;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
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
