
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

public class TitleDetails implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("temporary")
    @Expose
    private Boolean temporary;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("responsibility")
    @Expose
    private String responsibility;
    @SerializedName("holdMessageList")
    @Expose
    private List<Object> holdMessageList = null;
    @SerializedName("contentImageLink")
    @Expose
    private String contentImageLink;
    @SerializedName("awardInfoRecord")
    @Expose
    private AwardInfoRecord awardInfoRecord;
    @SerializedName("reviewInfoRecord")
    @Expose
    private ReviewInfoRecord reviewInfoRecord;
    @SerializedName("holdInfoRecord")
    @Expose
    private HoldInfoRecord holdInfoRecord;
    @SerializedName("quizInfoRecord")
    @Expose
    private QuizInfoRecord quizInfoRecord;
    @SerializedName("titleDetailsLink")
    @Expose
    private String titleDetailsLink;
    @SerializedName("additionalInfoRecord")
    @Expose
    private AdditionalInfoRecord additionalInfoRecord;
    @SerializedName("lostLocal")
    @Expose
    private Integer lostLocal;
    @SerializedName("totalLocal")
    @Expose
    private Integer totalLocal;
    @SerializedName("totalOffsite")
    @Expose
    private Integer totalOffsite;
    @SerializedName("callNumber")
    @Expose
    private String callNumber;
    @SerializedName("availableOffsite")
    @Expose
    private Integer availableOffsite;
    @SerializedName("availableLocal")
    @Expose
    private Integer availableLocal;
    @SerializedName("shelfNumber")
    @Expose
    private Object shelfNumber;
    @SerializedName("inUsersBooklist")
    @Expose
    private Boolean inUsersBooklist;
    @SerializedName("summaryList")
    @Expose
    private List<String> summaryList = null;
    @SerializedName("holdErrorList")
    @Expose
    private List<Object> holdErrorList = null;
    private final static long serialVersionUID = 3423712223050425072L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public List<Object> getHoldMessageList() {
        return holdMessageList;
    }

    public void setHoldMessageList(List<Object> holdMessageList) {
        this.holdMessageList = holdMessageList;
    }

    public String getContentImageLink() {
        return contentImageLink;
    }

    public void setContentImageLink(String contentImageLink) {
        this.contentImageLink = contentImageLink;
    }

    public AwardInfoRecord getAwardInfoRecord() {
        return awardInfoRecord;
    }

    public void setAwardInfoRecord(AwardInfoRecord awardInfoRecord) {
        this.awardInfoRecord = awardInfoRecord;
    }

    public ReviewInfoRecord getReviewInfoRecord() {
        return reviewInfoRecord;
    }

    public void setReviewInfoRecord(ReviewInfoRecord reviewInfoRecord) {
        this.reviewInfoRecord = reviewInfoRecord;
    }

    public HoldInfoRecord getHoldInfoRecord() {
        return holdInfoRecord;
    }

    public void setHoldInfoRecord(HoldInfoRecord holdInfoRecord) {
        this.holdInfoRecord = holdInfoRecord;
    }

    public QuizInfoRecord getQuizInfoRecord() {
        return quizInfoRecord;
    }

    public void setQuizInfoRecord(QuizInfoRecord quizInfoRecord) {
        this.quizInfoRecord = quizInfoRecord;
    }

    public String getTitleDetailsLink() {
        return titleDetailsLink;
    }

    public void setTitleDetailsLink(String titleDetailsLink) {
        this.titleDetailsLink = titleDetailsLink;
    }

    public AdditionalInfoRecord getAdditionalInfoRecord() {
        return additionalInfoRecord;
    }

    public void setAdditionalInfoRecord(AdditionalInfoRecord additionalInfoRecord) {
        this.additionalInfoRecord = additionalInfoRecord;
    }

    public Integer getLostLocal() {
        return lostLocal;
    }

    public void setLostLocal(Integer lostLocal) {
        this.lostLocal = lostLocal;
    }

    public Integer getTotalLocal() {
        return totalLocal;
    }

    public void setTotalLocal(Integer totalLocal) {
        this.totalLocal = totalLocal;
    }

    public Integer getTotalOffsite() {
        return totalOffsite;
    }

    public void setTotalOffsite(Integer totalOffsite) {
        this.totalOffsite = totalOffsite;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public Integer getAvailableOffsite() {
        return availableOffsite;
    }

    public void setAvailableOffsite(Integer availableOffsite) {
        this.availableOffsite = availableOffsite;
    }

    public Integer getAvailableLocal() {
        return availableLocal;
    }

    public void setAvailableLocal(Integer availableLocal) {
        this.availableLocal = availableLocal;
    }

    public Object getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(Object shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public Boolean getInUsersBooklist() {
        return inUsersBooklist;
    }

    public void setInUsersBooklist(Boolean inUsersBooklist) {
        this.inUsersBooklist = inUsersBooklist;
    }

    public List<String> getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(List<String> summaryList) {
        this.summaryList = summaryList;
    }

    public List<Object> getHoldErrorList() {
        return holdErrorList;
    }

    public void setHoldErrorList(List<Object> holdErrorList) {
        this.holdErrorList = holdErrorList;
    }

}
