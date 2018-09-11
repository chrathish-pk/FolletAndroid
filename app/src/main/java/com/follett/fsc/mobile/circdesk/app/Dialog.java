/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;

public class Dialog extends AlertDialog.Builder implements DialogInterface.OnKeyListener {


    public static class DialogDetails {
        public String mTitle;
        public String mMessage;
        public List<Button> mDialogButtonList;
    }

    public static class Button {

        public static final int LEFT = 1;
        public static final int RIGHT = 2;
        public String mButtoName;
        public int mButtonPosition;
        public DialogInterface.OnClickListener mListener;

        public Button(String buttoname, int buttonPosition, DialogInterface.OnClickListener listener) {
            mButtoName = buttoname;
            mButtonPosition = buttonPosition;
            mListener = listener;
        }

        public Button(String buttoname, DialogInterface.OnClickListener listener) {
            mButtoName = buttoname;
            mListener = listener;
            mButtonPosition = LEFT;
        }
    }

    public Dialog(Context context, DialogDetails dialogDetails) {
        super(context);
        buildAlertDialog(dialogDetails);
    }

    /**
     * @param dialogDetails build alert dialog based on the values
     */
    private void buildAlertDialog(DialogDetails dialogDetails) {

        if (dialogDetails == null) {
            return;
        }

        if (!TextUtils.isEmpty(dialogDetails.mTitle)) {
            setTitle(dialogDetails.mTitle);
        }

        if (!TextUtils.isEmpty(dialogDetails.mMessage)) {
            setMessage(dialogDetails.mMessage);
        }

        setCancelable(false);

        if (dialogDetails.mDialogButtonList != null) {

            for (int i = 0; i < dialogDetails.mDialogButtonList.size(); i++) {
                switch (dialogDetails.mDialogButtonList.get(i).mButtonPosition) {
                    case Button.RIGHT:
                        setPositiveButton(dialogDetails.mDialogButtonList.get(i).mButtoName,
                                dialogDetails.mDialogButtonList.get(i).mListener);
                        break;
                    case Button.LEFT:
                        setNegativeButton(dialogDetails.mDialogButtonList.get(i).mButtoName,
                                dialogDetails.mDialogButtonList.get(i).mListener);
                        break;
                    default:
                        break;
                }

            }
        }

    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return  keyCode == KeyEvent.KEYCODE_SEARCH;
    }
}
