
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizList implements Serializable
{

    @SerializedName("serviceName")
    @Expose
    private String serviceName;
    @SerializedName("serviceType")
    @Expose
    private Integer serviceType;
    @SerializedName("readingLevel")
    @Expose
    private String readingLevel;
    @SerializedName("interestLevel")
    @Expose
    private String interestLevel;
    @SerializedName("points")
    @Expose
    private String points;
    @SerializedName("quizID")
    @Expose
    private String quizID;
    @SerializedName("quizNumber")
    @Expose
    private String quizNumber;
    private final static long serialVersionUID = -4423020165744468852L;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getReadingLevel() {
        return readingLevel;
    }

    public void setReadingLevel(String readingLevel) {
        this.readingLevel = readingLevel;
    }

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

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(String quizNumber) {
        this.quizNumber = quizNumber;
    }

}
