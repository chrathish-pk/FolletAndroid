
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

public class ReviewInfoRecord implements Serializable {

    @SerializedName("alreadyReviewedByUser")
    @Expose
    private String alreadyReviewedByUser;
    @SerializedName("myRatingApproved")
    @Expose
    private String myRatingApproved;
    @SerializedName("reviewAverage")
    @Expose
    private String reviewAverage;
    @SerializedName("reviewList")
    @Expose
    private List<Object> reviewList = null;

    public String getAlreadyReviewedByUser() {
        return alreadyReviewedByUser;
    }

    public void setAlreadyReviewedByUser(String alreadyReviewedByUser) {
        this.alreadyReviewedByUser = alreadyReviewedByUser;
    }

    public String getMyRatingApproved() {
        return myRatingApproved;
    }

    public void setMyRatingApproved(String myRatingApproved) {
        this.myRatingApproved = myRatingApproved;
    }

    public String getReviewAverage() {
        return reviewAverage;
    }

    public void setReviewAverage(String reviewAverage) {
        this.reviewAverage = reviewAverage;
    }

    public List<Object> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Object> reviewList) {
        this.reviewList = reviewList;
    }

}
