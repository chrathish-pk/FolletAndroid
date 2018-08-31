
/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuizInfoRecord implements Serializable {

    @SerializedName("quizList")
    @Expose
    private QuizList quizList;

    public QuizList getQuizList() {
        return quizList;
    }

    public void setQuizList(QuizList quizList) {
        this.quizList = quizList;
    }

}
