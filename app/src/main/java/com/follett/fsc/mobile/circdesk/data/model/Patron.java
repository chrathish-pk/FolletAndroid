package com.follett.fsc.mobile.circdesk.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "patron")
public class Patron {

    @Element(name = "barcode")
    private String barcode;

    @Element(name = "lastFirstMiddleName")
    private String lastFirstMiddleName;

    @Element(name = "patronID")
    private String patronID;

    @Element(name = "patronPictureFileName")
    private String patronPictureFileName;


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLastFirstMiddleName() {
        return lastFirstMiddleName;
    }

    public void setLastFirstMiddleName(String lastFirstMiddleName) {
        this.lastFirstMiddleName = lastFirstMiddleName;
    }

    public String getPatronID() {
        return patronID;
    }

    public void setPatronID(String patronID) {
        this.patronID = patronID;
    }

    public String getPatronPictureFileName() {
        return patronPictureFileName;
    }

    public void setPatronPictureFileName(String patronPictureFileName) {
        this.patronPictureFileName = patronPictureFileName;
    }
}
