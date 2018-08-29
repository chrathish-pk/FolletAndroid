
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

public class AdditionalInfoRecord implements Serializable {

    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("electronicResourceList")
    @Expose
    private List<Object> electronicResourceList = null;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("fountasAndPinnellList")
    @Expose
    private List<Object> fountasAndPinnellList = null;
    @SerializedName("isbnList")
    @Expose
    private List<String> isbnList = null;
    @SerializedName("issnList")
    @Expose
    private List<Object> issnList = null;
    @SerializedName("lccnList")
    @Expose
    private List<Object> lccnList = null;
    @SerializedName("lexileList")
    @Expose
    private List<Object> lexileList = null;
    @SerializedName("publicationList")
    @Expose
    private PublicationList publicationList;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("seriesList")
    @Expose
    private List<String> seriesList = null;
    @SerializedName("targetAudienceList")
    @Expose
    private List<String> targetAudienceList = null;
    @SerializedName("term33XList")
    @Expose
    private List<Object> term33XList = null;
    @SerializedName("titlesBy")
    @Expose
    private String titlesBy;

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public List<Object> getElectronicResourceList() {
        return electronicResourceList;
    }

    public void setElectronicResourceList(List<Object> electronicResourceList) {
        this.electronicResourceList = electronicResourceList;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Object> getFountasAndPinnellList() {
        return fountasAndPinnellList;
    }

    public void setFountasAndPinnellList(List<Object> fountasAndPinnellList) {
        this.fountasAndPinnellList = fountasAndPinnellList;
    }

    public List<String> getIsbnList() {
        return isbnList;
    }

    public void setIsbnList(List<String> isbnList) {
        this.isbnList = isbnList;
    }

    public List<Object> getIssnList() {
        return issnList;
    }

    public void setIssnList(List<Object> issnList) {
        this.issnList = issnList;
    }

    public List<Object> getLccnList() {
        return lccnList;
    }

    public void setLccnList(List<Object> lccnList) {
        this.lccnList = lccnList;
    }

    public List<Object> getLexileList() {
        return lexileList;
    }

    public void setLexileList(List<Object> lexileList) {
        this.lexileList = lexileList;
    }

    public PublicationList getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(PublicationList publicationList) {
        this.publicationList = publicationList;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<String> seriesList) {
        this.seriesList = seriesList;
    }

    public List<String> getTargetAudienceList() {
        return targetAudienceList;
    }

    public void setTargetAudienceList(List<String> targetAudienceList) {
        this.targetAudienceList = targetAudienceList;
    }

    public List<Object> getTerm33XList() {
        return term33XList;
    }

    public void setTerm33XList(List<Object> term33XList) {
        this.term33XList = term33XList;
    }

    public String getTitlesBy() {
        return titlesBy;
    }

    public void setTitlesBy(String titlesBy) {
        this.titlesBy = titlesBy;
    }

}
