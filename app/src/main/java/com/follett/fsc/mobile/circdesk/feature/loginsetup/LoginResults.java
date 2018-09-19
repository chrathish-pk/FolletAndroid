/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResults implements Parcelable {

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

    protected LoginResults(Parcel in) {
        firstName = in.readString();
        guest = in.readString();
        invalidUsernameOrPassword = in.readString();
        lastName = in.readString();
        listID = in.readString();
        listSize = in.readString();
        lockedOut = in.readString();
        middleName = in.readString();
        myListsTotalSize = in.readString();
        numberOfMyLists = in.readString();
        passwordExpired = in.readString();
        passwordNotSecure = in.readString();
        sessionID = in.readString();
        sessionTimeoutInMillis = in.readString();
        success = in.readString();
    }

    public static final Creator<LoginResults> CREATOR = new Creator<LoginResults>() {
        @Override
        public LoginResults createFromParcel(Parcel in) {
            return new LoginResults(in);
        }

        @Override
        public LoginResults[] newArray(int size) {
            return new LoginResults[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(guest);
        dest.writeString(invalidUsernameOrPassword);
        dest.writeString(lastName);
        dest.writeString(listID);
        dest.writeString(listSize);
        dest.writeString(lockedOut);
        dest.writeString(middleName);
        dest.writeString(myListsTotalSize);
        dest.writeString(numberOfMyLists);
        dest.writeString(passwordExpired);
        dest.writeString(passwordNotSecure);
        dest.writeString(sessionID);
        dest.writeString(sessionTimeoutInMillis);
        dest.writeString(success);
    }
    
    public LoginResults(String firstName, String guest, String invalidUsernameOrPassword, String lastName, String listID, String listSize, String lockedOut,
            String middleName, String myListsTotalSize, String numberOfMyLists, String passwordExpired, String passwordNotSecure, Permissions permissions,
            String sessionID, String sessionTimeoutInMillis, String success) {
        this.firstName = firstName;
        this.guest = guest;
        this.invalidUsernameOrPassword = invalidUsernameOrPassword;
        this.lastName = lastName;
        this.listID = listID;
        this.listSize = listSize;
        this.lockedOut = lockedOut;
        this.middleName = middleName;
        this.myListsTotalSize = myListsTotalSize;
        this.numberOfMyLists = numberOfMyLists;
        this.passwordExpired = passwordExpired;
        this.passwordNotSecure = passwordNotSecure;
        this.permissions = permissions;
        this.sessionID = sessionID;
        this.sessionTimeoutInMillis = sessionTimeoutInMillis;
        this.success = success;
    }
}
