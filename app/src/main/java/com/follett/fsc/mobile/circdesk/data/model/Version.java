/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "version")
public class Version {
    
    @Element(required = false) String version;
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
}
