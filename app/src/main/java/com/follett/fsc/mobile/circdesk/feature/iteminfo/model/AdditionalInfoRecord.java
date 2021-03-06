
package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdditionalInfoRecord implements Parcelable
{

    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("titlesBy")
    @Expose
    private String titlesBy;
    @SerializedName("electronicResourceList")
    @Expose
    private List<Object> electronicResourceList = null;
    @SerializedName("targetAudienceList")
    @Expose
    private List<String> targetAudienceList = null;
    @SerializedName("fountasAndPinnellList")
    @Expose
    private List<Object> fountasAndPinnellList = null;
    @SerializedName("lexileList")
    @Expose
    private List<Object> lexileList = null;
    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("seriesList")
    @Expose
    private List<String> seriesList = null;
    @SerializedName("term33XList")
    @Expose
    private List<Object> term33XList = null;
    @SerializedName("publicationList")
    @Expose
    private List<PublicationList> publicationList = null;
    @SerializedName("isbnList")
    @Expose
    private List<String> isbnList = null;
    @SerializedName("lccnList")
    @Expose
    private List<Object> lccnList = null;
    @SerializedName("issnList")
    @Expose
    private List<Object> issnList = null;

    protected AdditionalInfoRecord(Parcel in) {
        format = in.readString();
        publisher = in.readString();
        titlesBy = in.readString();
        targetAudienceList = in.createStringArrayList();
        edition = in.readString();
        seriesList = in.createStringArrayList();
        isbnList = in.createStringArrayList();
    }

    public static final Creator<AdditionalInfoRecord> CREATOR = new Creator<AdditionalInfoRecord>() {
        @Override
        public AdditionalInfoRecord createFromParcel(Parcel in) {
            return new AdditionalInfoRecord(in);
        }

        @Override
        public AdditionalInfoRecord[] newArray(int size) {
            return new AdditionalInfoRecord[size];
        }
    };

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitlesBy() {
        return titlesBy;
    }

    public void setTitlesBy(String titlesBy) {
        this.titlesBy = titlesBy;
    }

    public List<Object> getElectronicResourceList() {
        return electronicResourceList;
    }

    public void setElectronicResourceList(List<Object> electronicResourceList) {
        this.electronicResourceList = electronicResourceList;
    }

    public List<String> getTargetAudienceList() {
        return targetAudienceList;
    }

    public void setTargetAudienceList(List<String> targetAudienceList) {
        this.targetAudienceList = targetAudienceList;
    }

    public List<Object> getFountasAndPinnellList() {
        return fountasAndPinnellList;
    }

    public void setFountasAndPinnellList(List<Object> fountasAndPinnellList) {
        this.fountasAndPinnellList = fountasAndPinnellList;
    }

    public List<Object> getLexileList() {
        return lexileList;
    }

    public void setLexileList(List<Object> lexileList) {
        this.lexileList = lexileList;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public List<String> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<String> seriesList) {
        this.seriesList = seriesList;
    }

    public List<Object> getTerm33XList() {
        return term33XList;
    }

    public void setTerm33XList(List<Object> term33XList) {
        this.term33XList = term33XList;
    }

    public List<PublicationList> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<PublicationList> publicationList) {
        this.publicationList = publicationList;
    }

    public List<String> getIsbnList() {
        return isbnList;
    }

    public void setIsbnList(List<String> isbnList) {
        this.isbnList = isbnList;
    }

    public List<Object> getLccnList() {
        return lccnList;
    }

    public void setLccnList(List<Object> lccnList) {
        this.lccnList = lccnList;
    }

    public List<Object> getIssnList() {
        return issnList;
    }

    public void setIssnList(List<Object> issnList) {
        this.issnList = issnList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(format);
        dest.writeString(publisher);
        dest.writeString(titlesBy);
        dest.writeStringList(targetAudienceList);
        dest.writeString(edition);
        dest.writeStringList(seriesList);
        dest.writeStringList(isbnList);
    }
}
