
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.model.AdditionalInfo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quiz implements Serializable {

    @SerializedName("interestLevel")
    @Expose
    private String interestLevel;
    @SerializedName("points")
    @Expose
    private String points;
    @SerializedName("quizID")
    @Expose
    private List<Object> quizID = null;
    @SerializedName("quizNumber")
    @Expose
    private String quizNumber;
    @SerializedName("readingLevel")
    @Expose
    private String readingLevel;
    @SerializedName("serviceName")
    @Expose
    private String serviceName;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;

    public String getInterestLevel() {
        return interestLevel;
    }

    public void setInterestLevel(String interestLevel) {
        this.interestLevel = interestLevel;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public List<Object> getQuizID() {
        return quizID;
    }

    public void setQuizID(List<Object> quizID) {
        this.quizID = quizID;
    }

    public String getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(String quizNumber) {
        this.quizNumber = quizNumber;
    }

    public String getReadingLevel() {
        return readingLevel;
    }

    public void setReadingLevel(String readingLevel) {
        this.readingLevel = readingLevel;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

}
