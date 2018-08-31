/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.app;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;

import java.util.ArrayList;

public class CustomAlert {

    private CustomAlert() {
    }

    public static void showDialog(Activity activity, String title, String message,
                                  String positivebuttoname, DialogInterface.OnClickListener positivebuttnlistener,
                                  String negativebuttoname, DialogInterface.OnClickListener negativebuttonlistener) {

        if (activity == null || activity.isFinishing()) {
            return;
        }

        Dialog.DialogDetails dialogDetails = new Dialog.DialogDetails();
        if (!TextUtils.isEmpty(title)) {
            dialogDetails.mTitle = title;
        }

        if (!TextUtils.isEmpty(message)) {
            dialogDetails.mMessage = message;
        }

        ArrayList<Dialog.Button> list = new ArrayList<>();
        if (!TextUtils.isEmpty(positivebuttoname)) {
            Dialog.Button button = new Dialog.Button(positivebuttoname,Dialog.Button.RIGHT, positivebuttnlistener);
            list.add(button);
        }

        if (!TextUtils.isEmpty(negativebuttoname)) {
            Dialog.Button button = new Dialog.Button(negativebuttoname, Dialog.Button.LEFT, negativebuttonlistener);
            list.add(button);
        }

        dialogDetails.mDialogButtonList = list;

        Dialog dialog = new Dialog(activity, dialogDetails);
        dialog.show();
    }
}
