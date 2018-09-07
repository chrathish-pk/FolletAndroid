
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TitleDetails implements Parcelable
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

    protected TitleDetails(Parcel in) {
        title = in.readString();
        byte tmpTemporary = in.readByte();
        temporary = tmpTemporary == 0 ? null : tmpTemporary == 1;
        status = in.readString();
        responsibility = in.readString();
        contentImageLink = in.readString();
        awardInfoRecord = in.readParcelable(AwardInfoRecord.class.getClassLoader());
        reviewInfoRecord = in.readParcelable(ReviewInfoRecord.class.getClassLoader());
        holdInfoRecord = in.readParcelable(HoldInfoRecord.class.getClassLoader());
        quizInfoRecord = in.readParcelable(QuizInfoRecord.class.getClassLoader());
        titleDetailsLink = in.readString();
        additionalInfoRecord = in.readParcelable(AdditionalInfoRecord.class.getClassLoader());
        if (in.readByte() == 0) {
            lostLocal = null;
        } else {
            lostLocal = in.readInt();
        }
        if (in.readByte() == 0) {
            totalLocal = null;
        } else {
            totalLocal = in.readInt();
        }
        if (in.readByte() == 0) {
            totalOffsite = null;
        } else {
            totalOffsite = in.readInt();
        }
        callNumber = in.readString();
        if (in.readByte() == 0) {
            availableOffsite = null;
        } else {
            availableOffsite = in.readInt();
        }
        if (in.readByte() == 0) {
            availableLocal = null;
        } else {
            availableLocal = in.readInt();
        }
        byte tmpInUsersBooklist = in.readByte();
        inUsersBooklist = tmpInUsersBooklist == 0 ? null : tmpInUsersBooklist == 1;
        summaryList = in.createStringArrayList();
    }

    public static final Creator<TitleDetails> CREATOR = new Creator<TitleDetails>() {
        @Override
        public TitleDetails createFromParcel(Parcel in) {
            return new TitleDetails(in);
        }

        @Override
        public TitleDetails[] newArray(int size) {
            return new TitleDetails[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeByte((byte) (temporary == null ? 0 : temporary ? 1 : 2));
        dest.writeString(status);
        dest.writeString(responsibility);
        dest.writeString(contentImageLink);
        dest.writeParcelable(awardInfoRecord, flags);
        dest.writeParcelable(reviewInfoRecord, flags);
        dest.writeParcelable(holdInfoRecord, flags);
        dest.writeParcelable(quizInfoRecord, flags);
        dest.writeString(titleDetailsLink);
        dest.writeParcelable(additionalInfoRecord, flags);
        if (lostLocal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(lostLocal);
        }
        if (totalLocal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalLocal);
        }
        if (totalOffsite == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalOffsite);
        }
        dest.writeString(callNumber);
        if (availableOffsite == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(availableOffsite);
        }
        if (availableLocal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(availableLocal);
        }
        dest.writeByte((byte) (inUsersBooklist == null ? 0 : inUsersBooklist ? 1 : 2));
        dest.writeStringList(summaryList);
    }
}
