/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "siteResults")
public class SiteResults {
    @ElementList(name = "sites") public List<SiteRecord> sites;
}
