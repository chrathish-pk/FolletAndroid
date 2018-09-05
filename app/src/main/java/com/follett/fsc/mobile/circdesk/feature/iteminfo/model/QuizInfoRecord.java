
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

public class QuizInfoRecord implements Serializable
{

    @SerializedName("quizList")
    @Expose
    private List<QuizList> quizList = null;
    private final static long serialVersionUID = 8241381575270910591L;

    public List<QuizList> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<QuizList> quizList) {
        this.quizList = quizList;
    }

}
