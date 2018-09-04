
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

public class ReviewInfoRecord implements Serializable
{

    @SerializedName("alreadyReviewedByUser")
    @Expose
    private Boolean alreadyReviewedByUser;
    @SerializedName("reviewAverage")
    @Expose
    private String reviewAverage;
    @SerializedName("myRatingApproved")
    @Expose
    private Boolean myRatingApproved;
    @SerializedName("reviewList")
    @Expose
    private List<Object> reviewList = null;
    private final static long serialVersionUID = 2382812993672654714L;

    public Boolean getAlreadyReviewedByUser() {
        return alreadyReviewedByUser;
    }

    public void setAlreadyReviewedByUser(Boolean alreadyReviewedByUser) {
        this.alreadyReviewedByUser = alreadyReviewedByUser;
    }

    public String getReviewAverage() {
        return reviewAverage;
    }

    public void setReviewAverage(String reviewAverage) {
        this.reviewAverage = reviewAverage;
    }

    public Boolean getMyRatingApproved() {
        return myRatingApproved;
    }

    public void setMyRatingApproved(Boolean myRatingApproved) {
        this.myRatingApproved = myRatingApproved;
    }

    public List<Object> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Object> reviewList) {
        this.reviewList = reviewList;
    }

}
