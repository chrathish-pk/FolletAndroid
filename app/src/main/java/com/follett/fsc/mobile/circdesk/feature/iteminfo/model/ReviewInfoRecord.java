
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewInfoRecord implements Parcelable
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

    protected ReviewInfoRecord(Parcel in) {
        byte tmpAlreadyReviewedByUser = in.readByte();
        alreadyReviewedByUser = tmpAlreadyReviewedByUser == 0 ? null : tmpAlreadyReviewedByUser == 1;
        reviewAverage = in.readString();
        byte tmpMyRatingApproved = in.readByte();
        myRatingApproved = tmpMyRatingApproved == 0 ? null : tmpMyRatingApproved == 1;
    }

    public static final Creator<ReviewInfoRecord> CREATOR = new Creator<ReviewInfoRecord>() {
        @Override
        public ReviewInfoRecord createFromParcel(Parcel in) {
            return new ReviewInfoRecord(in);
        }

        @Override
        public ReviewInfoRecord[] newArray(int size) {
            return new ReviewInfoRecord[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (alreadyReviewedByUser) dest.writeByte((byte) (alreadyReviewedByUser == null ? 0 : 1));
        else dest.writeByte((byte) (alreadyReviewedByUser == null ? 0 : 2));
        dest.writeString(reviewAverage);
        if (myRatingApproved) dest.writeByte((byte) (myRatingApproved == null ? 0 : 1));
        else dest.writeByte((byte) (myRatingApproved == null ? 0 : 2));
    }
}
