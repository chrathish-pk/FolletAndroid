
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizInfoRecord implements Parcelable
{

    @SerializedName("quizList")
    @Expose
    private List<QuizList> quizList = null;

    protected QuizInfoRecord(Parcel in) {
        quizList = in.createTypedArrayList(QuizList.CREATOR);
    }


    public static final Creator<QuizInfoRecord> CREATOR = new Creator<QuizInfoRecord>() {
        @Override
        public QuizInfoRecord createFromParcel(Parcel in) {
            return new QuizInfoRecord(in);
        }

        @Override
        public QuizInfoRecord[] newArray(int size) {
            return new QuizInfoRecord[size];
        }
    };

    public List<QuizList> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<QuizList> quizList) {
        this.quizList = quizList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(quizList);
    }
}
