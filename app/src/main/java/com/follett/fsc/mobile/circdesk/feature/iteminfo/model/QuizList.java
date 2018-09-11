
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizList implements Parcelable
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

    protected QuizList(Parcel in) {
        serviceName = in.readString();
        if (in.readByte() == 0) {
            serviceType = null;
        } else {
            serviceType = in.readInt();
        }
        readingLevel = in.readString();
        interestLevel = in.readString();
        points = in.readString();
        quizID = in.readString();
        quizNumber = in.readString();
    }

    public static final Creator<QuizList> CREATOR = new Creator<QuizList>() {
        @Override
        public QuizList createFromParcel(Parcel in) {
            return new QuizList(in);
        }

        @Override
        public QuizList[] newArray(int size) {
            return new QuizList[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(serviceName);
        if (serviceType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(serviceType);
        }
        dest.writeString(readingLevel);
        dest.writeString(interestLevel);
        dest.writeString(points);
        dest.writeString(quizID);
        dest.writeString(quizNumber);
    }
}
