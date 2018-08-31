
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TitleDetails implements Serializable {

    @SerializedName("additionalInfoRecord")
    @Expose
    private AdditionalInfoRecord additionalInfoRecord;
    @SerializedName("availableLocal")
    @Expose
    private String availableLocal;
    @SerializedName("availableOffsite")
    @Expose
    private String availableOffsite;
    @SerializedName("awardInfoRecord")
    @Expose
    private AwardInfoRecord awardInfoRecord;
    @SerializedName("callNumber")
    @Expose
    private String callNumber;
    @SerializedName("contentImageLink")
    @Expose
    private String contentImageLink;
    @SerializedName("holdErrorList")
    @Expose
    private List<Object> holdErrorList = null;
    @SerializedName("holdInfoRecord")
    @Expose
    private HoldInfoRecord holdInfoRecord;
    @SerializedName("holdMessageList")
    @Expose
    private List<Object> holdMessageList = null;
    @SerializedName("inUsersBooklist")
    @Expose
    private String inUsersBooklist;
    @SerializedName("lostLocal")
    @Expose
    private String lostLocal;
    @SerializedName("quizInfoRecord")
    @Expose
    private QuizInfoRecord quizInfoRecord;
    @SerializedName("responsibility")
    @Expose
    private String responsibility;
    @SerializedName("reviewInfoRecord")
    @Expose
    private ReviewInfoRecord reviewInfoRecord;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("summaryList")
    @Expose
    private SummaryList summaryList;
    @SerializedName("temporary")
    @Expose
    private String temporary;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleDetailsLink")
    @Expose
    private String titleDetailsLink;
    @SerializedName("totalLocal")
    @Expose
    private String totalLocal;
    @SerializedName("totalOffsite")
    @Expose
    private String totalOffsite;

    public AdditionalInfoRecord getAdditionalInfoRecord() {
        return additionalInfoRecord;
    }

    public void setAdditionalInfoRecord(AdditionalInfoRecord additionalInfoRecord) {
        this.additionalInfoRecord = additionalInfoRecord;
    }

    public String getAvailableLocal() {
        return availableLocal;
    }

    public void setAvailableLocal(String availableLocal) {
        this.availableLocal = availableLocal;
    }

    public String getAvailableOffsite() {
        return availableOffsite;
    }

    public void setAvailableOffsite(String availableOffsite) {
        this.availableOffsite = availableOffsite;
    }

    public AwardInfoRecord getAwardInfoRecord() {
        return awardInfoRecord;
    }

    public void setAwardInfoRecord(AwardInfoRecord awardInfoRecord) {
        this.awardInfoRecord = awardInfoRecord;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getContentImageLink() {
        return contentImageLink;
    }

    public void setContentImageLink(String contentImageLink) {
        this.contentImageLink = contentImageLink;
    }

    public List<Object> getHoldErrorList() {
        return holdErrorList;
    }

    public void setHoldErrorList(List<Object> holdErrorList) {
        this.holdErrorList = holdErrorList;
    }

    public HoldInfoRecord getHoldInfoRecord() {
        return holdInfoRecord;
    }

    public void setHoldInfoRecord(HoldInfoRecord holdInfoRecord) {
        this.holdInfoRecord = holdInfoRecord;
    }

    public List<Object> getHoldMessageList() {
        return holdMessageList;
    }

    public void setHoldMessageList(List<Object> holdMessageList) {
        this.holdMessageList = holdMessageList;
    }

    public String getInUsersBooklist() {
        return inUsersBooklist;
    }

    public void setInUsersBooklist(String inUsersBooklist) {
        this.inUsersBooklist = inUsersBooklist;
    }

    public String getLostLocal() {
        return lostLocal;
    }

    public void setLostLocal(String lostLocal) {
        this.lostLocal = lostLocal;
    }

    public QuizInfoRecord getQuizInfoRecord() {
        return quizInfoRecord;
    }

    public void setQuizInfoRecord(QuizInfoRecord quizInfoRecord) {
        this.quizInfoRecord = quizInfoRecord;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public ReviewInfoRecord getReviewInfoRecord() {
        return reviewInfoRecord;
    }

    public void setReviewInfoRecord(ReviewInfoRecord reviewInfoRecord) {
        this.reviewInfoRecord = reviewInfoRecord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SummaryList getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(SummaryList summaryList) {
        this.summaryList = summaryList;
    }

    public String getTemporary() {
        return temporary;
    }

    public void setTemporary(String temporary) {
        this.temporary = temporary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDetailsLink() {
        return titleDetailsLink;
    }

    public void setTitleDetailsLink(String titleDetailsLink) {
        this.titleDetailsLink = titleDetailsLink;
    }

    public String getTotalLocal() {
        return totalLocal;
    }

    public void setTotalLocal(String totalLocal) {
        this.totalLocal = totalLocal;
    }

    public String getTotalOffsite() {
        return totalOffsite;
    }

    public void setTotalOffsite(String totalOffsite) {
        this.totalOffsite = totalOffsite;
    }

}
