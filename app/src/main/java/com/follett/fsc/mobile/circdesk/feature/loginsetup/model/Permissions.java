/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Permissions implements Parcelable {

    @SerializedName("canCheckinAsset")
    @Expose
    private String canCheckinAsset;
    @SerializedName("canCheckinLibrary")
    @Expose
    private String canCheckinLibrary;
    @SerializedName("canCheckinTextbook")
    @Expose
    private String canCheckinTextbook;
    @SerializedName("canCheckoutAsset")
    @Expose
    private String canCheckoutAsset;
    @SerializedName("canCheckoutLibrary")
    @Expose
    private String canCheckoutLibrary;
    @SerializedName("canCheckoutTextbook")
    @Expose
    private String canCheckoutTextbook;
    @SerializedName("canDeleteHold")
    @Expose
    private String canDeleteHold;
    @SerializedName("canDoBasicSearch")
    @Expose
    private String canDoBasicSearch;
    @SerializedName("canLinkToFollettShelf")
    @Expose
    private String canLinkToFollettShelf;
    @SerializedName("canOverrideBlocksAsset")
    @Expose
    private String canOverrideBlocksAsset;
    @SerializedName("canOverrideBlocksLibrary")
    @Expose
    private String canOverrideBlocksLibrary;
    @SerializedName("canOverrideBlocksTextbook")
    @Expose
    private String canOverrideBlocksTextbook;
    @SerializedName("canPlaceHold")
    @Expose
    private String canPlaceHold;
    @SerializedName("canPlaceHoldOnAvailableCopies")
    @Expose
    private String canPlaceHoldOnAvailableCopies;
    @SerializedName("canPlaceHoldOnMultipleCopies")
    @Expose
    private String canPlaceHoldOnMultipleCopies;
    @SerializedName("canPostToSocialMediaSites")
    @Expose
    private String canPostToSocialMediaSites;
    @SerializedName("canRenew")
    @Expose
    private String canRenew;
    @SerializedName("canSearchDigitalResources")
    @Expose
    private String canSearchDigitalResources;
    @SerializedName("canSearchOneSearch")
    @Expose
    private String canSearchOneSearch;
    @SerializedName("canSearchWPE")
    @Expose
    private String canSearchWPE;
    @SerializedName("canSeeSearchSuggestions")
    @Expose
    private String canSeeSearchSuggestions;
    @SerializedName("canSubmitLibraryReviewComments")
    @Expose
    private String canSubmitLibraryReviewComments;
    @SerializedName("canSubmitLibraryReviews")
    @Expose
    private String canSubmitLibraryReviews;
    @SerializedName("canUseBookclub")
    @Expose
    private String canUseBookclub;
    @SerializedName("canUseDestinyQuest")
    @Expose
    private String canUseDestinyQuest;
    @SerializedName("canViewCheckoutHistory")
    @Expose
    private String canViewCheckoutHistory;
    @SerializedName("canViewCopyStatusAsset")
    @Expose
    private String canViewCopyStatusAsset;
    @SerializedName("canViewCopyStatusLibrary")
    @Expose
    private String canViewCopyStatusLibrary;
    @SerializedName("canViewCopyStatusTextbook")
    @Expose
    private String canViewCopyStatusTextbook;
    @SerializedName("canViewItemsOutAsset")
    @Expose
    private String canViewItemsOutAsset;
    @SerializedName("canViewItemsOutLibrary")
    @Expose
    private String canViewItemsOutLibrary;
    @SerializedName("canViewItemsOutTextbook")
    @Expose
    private String canViewItemsOutTextbook;
    @SerializedName("canViewMyInfo")
    @Expose
    private String canViewMyInfo;
    @SerializedName("canViewMyList")
    @Expose
    private String canViewMyList;
    @SerializedName("canViewNewArrivals")
    @Expose
    private String canViewNewArrivals;
    @SerializedName("canViewOffsiteInfo")
    @Expose
    private String canViewOffsiteInfo;
    @SerializedName("canViewPatronStatus")
    @Expose
    private String canViewPatronStatus;
    @SerializedName("canViewPublicLists")
    @Expose
    private String canViewPublicLists;
    @SerializedName("canViewTop10")
    @Expose
    private String canViewTop10;

    protected Permissions(Parcel in) {
        canCheckinAsset = in.readString();
        canCheckinLibrary = in.readString();
        canCheckinTextbook = in.readString();
        canCheckoutAsset = in.readString();
        canCheckoutLibrary = in.readString();
        canCheckoutTextbook = in.readString();
        canDeleteHold = in.readString();
        canDoBasicSearch = in.readString();
        canLinkToFollettShelf = in.readString();
        canOverrideBlocksAsset = in.readString();
        canOverrideBlocksLibrary = in.readString();
        canOverrideBlocksTextbook = in.readString();
        canPlaceHold = in.readString();
        canPlaceHoldOnAvailableCopies = in.readString();
        canPlaceHoldOnMultipleCopies = in.readString();
        canPostToSocialMediaSites = in.readString();
        canRenew = in.readString();
        canSearchDigitalResources = in.readString();
        canSearchOneSearch = in.readString();
        canSearchWPE = in.readString();
        canSeeSearchSuggestions = in.readString();
        canSubmitLibraryReviewComments = in.readString();
        canSubmitLibraryReviews = in.readString();
        canUseBookclub = in.readString();
        canUseDestinyQuest = in.readString();
        canViewCheckoutHistory = in.readString();
        canViewCopyStatusAsset = in.readString();
        canViewCopyStatusLibrary = in.readString();
        canViewCopyStatusTextbook = in.readString();
        canViewItemsOutAsset = in.readString();
        canViewItemsOutLibrary = in.readString();
        canViewItemsOutTextbook = in.readString();
        canViewMyInfo = in.readString();
        canViewMyList = in.readString();
        canViewNewArrivals = in.readString();
        canViewOffsiteInfo = in.readString();
        canViewPatronStatus = in.readString();
        canViewPublicLists = in.readString();
        canViewTop10 = in.readString();
    }

    public static final Creator<Permissions> CREATOR = new Creator<Permissions>() {
        @Override
        public Permissions createFromParcel(Parcel in) {
            return new Permissions(in);
        }

        @Override
        public Permissions[] newArray(int size) {
            return new Permissions[size];
        }
    };

    public String getCanCheckinAsset() {
        return canCheckinAsset;
    }

    public void setCanCheckinAsset(String canCheckinAsset) {
        this.canCheckinAsset = canCheckinAsset;
    }

    public String getCanCheckinLibrary() {
        return canCheckinLibrary;
    }

    public void setCanCheckinLibrary(String canCheckinLibrary) {
        this.canCheckinLibrary = canCheckinLibrary;
    }

    public String getCanCheckinTextbook() {
        return canCheckinTextbook;
    }

    public void setCanCheckinTextbook(String canCheckinTextbook) {
        this.canCheckinTextbook = canCheckinTextbook;
    }

    public String getCanCheckoutAsset() {
        return canCheckoutAsset;
    }

    public void setCanCheckoutAsset(String canCheckoutAsset) {
        this.canCheckoutAsset = canCheckoutAsset;
    }

    public String getCanCheckoutLibrary() {
        return canCheckoutLibrary;
    }

    public void setCanCheckoutLibrary(String canCheckoutLibrary) {
        this.canCheckoutLibrary = canCheckoutLibrary;
    }

    public String getCanCheckoutTextbook() {
        return canCheckoutTextbook;
    }

    public void setCanCheckoutTextbook(String canCheckoutTextbook) {
        this.canCheckoutTextbook = canCheckoutTextbook;
    }

    public String getCanDeleteHold() {
        return canDeleteHold;
    }

    public void setCanDeleteHold(String canDeleteHold) {
        this.canDeleteHold = canDeleteHold;
    }

    public String getCanDoBasicSearch() {
        return canDoBasicSearch;
    }

    public void setCanDoBasicSearch(String canDoBasicSearch) {
        this.canDoBasicSearch = canDoBasicSearch;
    }

    public String getCanLinkToFollettShelf() {
        return canLinkToFollettShelf;
    }

    public void setCanLinkToFollettShelf(String canLinkToFollettShelf) {
        this.canLinkToFollettShelf = canLinkToFollettShelf;
    }

    public String getCanOverrideBlocksAsset() {
        return canOverrideBlocksAsset;
    }

    public void setCanOverrideBlocksAsset(String canOverrideBlocksAsset) {
        this.canOverrideBlocksAsset = canOverrideBlocksAsset;
    }

    public String getCanOverrideBlocksLibrary() {
        return canOverrideBlocksLibrary;
    }

    public void setCanOverrideBlocksLibrary(String canOverrideBlocksLibrary) {
        this.canOverrideBlocksLibrary = canOverrideBlocksLibrary;
    }

    public String getCanOverrideBlocksTextbook() {
        return canOverrideBlocksTextbook;
    }

    public void setCanOverrideBlocksTextbook(String canOverrideBlocksTextbook) {
        this.canOverrideBlocksTextbook = canOverrideBlocksTextbook;
    }

    public String getCanPlaceHold() {
        return canPlaceHold;
    }

    public void setCanPlaceHold(String canPlaceHold) {
        this.canPlaceHold = canPlaceHold;
    }

    public String getCanPlaceHoldOnAvailableCopies() {
        return canPlaceHoldOnAvailableCopies;
    }

    public void setCanPlaceHoldOnAvailableCopies(String canPlaceHoldOnAvailableCopies) {
        this.canPlaceHoldOnAvailableCopies = canPlaceHoldOnAvailableCopies;
    }

    public String getCanPlaceHoldOnMultipleCopies() {
        return canPlaceHoldOnMultipleCopies;
    }

    public void setCanPlaceHoldOnMultipleCopies(String canPlaceHoldOnMultipleCopies) {
        this.canPlaceHoldOnMultipleCopies = canPlaceHoldOnMultipleCopies;
    }

    public String getCanPostToSocialMediaSites() {
        return canPostToSocialMediaSites;
    }

    public void setCanPostToSocialMediaSites(String canPostToSocialMediaSites) {
        this.canPostToSocialMediaSites = canPostToSocialMediaSites;
    }

    public String getCanRenew() {
        return canRenew;
    }

    public void setCanRenew(String canRenew) {
        this.canRenew = canRenew;
    }

    public String getCanSearchDigitalResources() {
        return canSearchDigitalResources;
    }

    public void setCanSearchDigitalResources(String canSearchDigitalResources) {
        this.canSearchDigitalResources = canSearchDigitalResources;
    }

    public String getCanSearchOneSearch() {
        return canSearchOneSearch;
    }

    public void setCanSearchOneSearch(String canSearchOneSearch) {
        this.canSearchOneSearch = canSearchOneSearch;
    }

    public String getCanSearchWPE() {
        return canSearchWPE;
    }

    public void setCanSearchWPE(String canSearchWPE) {
        this.canSearchWPE = canSearchWPE;
    }

    public String getCanSeeSearchSuggestions() {
        return canSeeSearchSuggestions;
    }

    public void setCanSeeSearchSuggestions(String canSeeSearchSuggestions) {
        this.canSeeSearchSuggestions = canSeeSearchSuggestions;
    }

    public String getCanSubmitLibraryReviewComments() {
        return canSubmitLibraryReviewComments;
    }

    public void setCanSubmitLibraryReviewComments(String canSubmitLibraryReviewComments) {
        this.canSubmitLibraryReviewComments = canSubmitLibraryReviewComments;
    }

    public String getCanSubmitLibraryReviews() {
        return canSubmitLibraryReviews;
    }

    public void setCanSubmitLibraryReviews(String canSubmitLibraryReviews) {
        this.canSubmitLibraryReviews = canSubmitLibraryReviews;
    }

    public String getCanUseBookclub() {
        return canUseBookclub;
    }

    public void setCanUseBookclub(String canUseBookclub) {
        this.canUseBookclub = canUseBookclub;
    }

    public String getCanUseDestinyQuest() {
        return canUseDestinyQuest;
    }

    public void setCanUseDestinyQuest(String canUseDestinyQuest) {
        this.canUseDestinyQuest = canUseDestinyQuest;
    }

    public String getCanViewCheckoutHistory() {
        return canViewCheckoutHistory;
    }

    public void setCanViewCheckoutHistory(String canViewCheckoutHistory) {
        this.canViewCheckoutHistory = canViewCheckoutHistory;
    }

    public String getCanViewCopyStatusAsset() {
        return canViewCopyStatusAsset;
    }

    public void setCanViewCopyStatusAsset(String canViewCopyStatusAsset) {
        this.canViewCopyStatusAsset = canViewCopyStatusAsset;
    }

    public String getCanViewCopyStatusLibrary() {
        return canViewCopyStatusLibrary;
    }

    public void setCanViewCopyStatusLibrary(String canViewCopyStatusLibrary) {
        this.canViewCopyStatusLibrary = canViewCopyStatusLibrary;
    }

    public String getCanViewCopyStatusTextbook() {
        return canViewCopyStatusTextbook;
    }

    public void setCanViewCopyStatusTextbook(String canViewCopyStatusTextbook) {
        this.canViewCopyStatusTextbook = canViewCopyStatusTextbook;
    }

    public String getCanViewItemsOutAsset() {
        return canViewItemsOutAsset;
    }

    public void setCanViewItemsOutAsset(String canViewItemsOutAsset) {
        this.canViewItemsOutAsset = canViewItemsOutAsset;
    }

    public String getCanViewItemsOutLibrary() {
        return canViewItemsOutLibrary;
    }

    public void setCanViewItemsOutLibrary(String canViewItemsOutLibrary) {
        this.canViewItemsOutLibrary = canViewItemsOutLibrary;
    }

    public String getCanViewItemsOutTextbook() {
        return canViewItemsOutTextbook;
    }

    public void setCanViewItemsOutTextbook(String canViewItemsOutTextbook) {
        this.canViewItemsOutTextbook = canViewItemsOutTextbook;
    }

    public String getCanViewMyInfo() {
        return canViewMyInfo;
    }

    public void setCanViewMyInfo(String canViewMyInfo) {
        this.canViewMyInfo = canViewMyInfo;
    }

    public String getCanViewMyList() {
        return canViewMyList;
    }

    public void setCanViewMyList(String canViewMyList) {
        this.canViewMyList = canViewMyList;
    }

    public String getCanViewNewArrivals() {
        return canViewNewArrivals;
    }

    public void setCanViewNewArrivals(String canViewNewArrivals) {
        this.canViewNewArrivals = canViewNewArrivals;
    }

    public String getCanViewOffsiteInfo() {
        return canViewOffsiteInfo;
    }

    public void setCanViewOffsiteInfo(String canViewOffsiteInfo) {
        this.canViewOffsiteInfo = canViewOffsiteInfo;
    }

    public String getCanViewPatronStatus() {
        return canViewPatronStatus;
    }

    public void setCanViewPatronStatus(String canViewPatronStatus) {
        this.canViewPatronStatus = canViewPatronStatus;
    }

    public String getCanViewPublicLists() {
        return canViewPublicLists;
    }

    public void setCanViewPublicLists(String canViewPublicLists) {
        this.canViewPublicLists = canViewPublicLists;
    }

    public String getCanViewTop10() {
        return canViewTop10;
    }

    public void setCanViewTop10(String canViewTop10) {
        this.canViewTop10 = canViewTop10;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(canCheckinAsset);
        dest.writeString(canCheckinLibrary);
        dest.writeString(canCheckinTextbook);
        dest.writeString(canCheckoutAsset);
        dest.writeString(canCheckoutLibrary);
        dest.writeString(canCheckoutTextbook);
        dest.writeString(canDeleteHold);
        dest.writeString(canDoBasicSearch);
        dest.writeString(canLinkToFollettShelf);
        dest.writeString(canOverrideBlocksAsset);
        dest.writeString(canOverrideBlocksLibrary);
        dest.writeString(canOverrideBlocksTextbook);
        dest.writeString(canPlaceHold);
        dest.writeString(canPlaceHoldOnAvailableCopies);
        dest.writeString(canPlaceHoldOnMultipleCopies);
        dest.writeString(canPostToSocialMediaSites);
        dest.writeString(canRenew);
        dest.writeString(canSearchDigitalResources);
        dest.writeString(canSearchOneSearch);
        dest.writeString(canSearchWPE);
        dest.writeString(canSeeSearchSuggestions);
        dest.writeString(canSubmitLibraryReviewComments);
        dest.writeString(canSubmitLibraryReviews);
        dest.writeString(canUseBookclub);
        dest.writeString(canUseDestinyQuest);
        dest.writeString(canViewCheckoutHistory);
        dest.writeString(canViewCopyStatusAsset);
        dest.writeString(canViewCopyStatusLibrary);
        dest.writeString(canViewCopyStatusTextbook);
        dest.writeString(canViewItemsOutAsset);
        dest.writeString(canViewItemsOutLibrary);
        dest.writeString(canViewItemsOutTextbook);
        dest.writeString(canViewMyInfo);
        dest.writeString(canViewMyList);
        dest.writeString(canViewNewArrivals);
        dest.writeString(canViewOffsiteInfo);
        dest.writeString(canViewPatronStatus);
        dest.writeString(canViewPublicLists);
        dest.writeString(canViewTop10);
    }
}
