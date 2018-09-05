/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */

package com.follett.fsc.mobile.circdesk.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CustomAlert;
import com.follett.fsc.mobile.circdesk.app.GlideApp;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;

public class AppUtils {

    private static AppUtils mInstance = null;
    private static AlertDialog alertDialog;
    private static ProgressDialog mProgressDialog;

    public static AppUtils getInstance() {
        if (mInstance == null) {
            mInstance = new AppUtils();
        }
        return mInstance;
    }

    /**
     * @param activity Method to hide the keyboard from screen
     */
    public void hideKeyBoard(Context activity, View view) {
        try {
            if (activity != null && view != null) {
                InputMethodManager input = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(view.getWindowToken(), 0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param activity Method to hide the keyboard from screen
     */
    public void showKeyBoard(Activity activity, View view) {
        try {
            if (activity != null && view != null) {
                InputMethodManager input = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                input.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows a progress dialog with a spinning animation in it. This method must preferably called
     * from a UI thread.
     *
     * @param ctx           Activity context
     * @param title         Title of the progress dialog
     * @param body          Body/Message to be shown in the progress dialog
     * @param isCancellable True if the dialog can be cancelled on back button press, false otherwise
     *                      *
     */
    public void showProgressDialog(Context ctx, String title, String body, boolean isCancellable) {
        showProgressDialog(ctx, title, body, null, isCancellable);
    }

    /**
     * Shows a progress dialog with a spinning animation in it. This method must preferably called
     * from a UI thread.
     *
     * @param ctx           Activity context
     * @param title         Title of the progress dialog
     * @param body          Body/Message to be shown in the progress dialog
     * @param icon          Icon to show in the progress dialog. It can be null.
     * @param isCancellable True if the dialog can be cancelled on back button press, false otherwise
     *                      *
     */
    private void showProgressDialog(Context ctx, String title, String body, Drawable icon, boolean isCancellable) {

        try {
            if (ctx instanceof Activity) {
                if (!((Activity) ctx).isFinishing()) {
                    mProgressDialog = ProgressDialog.show(ctx, title, body, true);
                    mProgressDialog.setIcon(icon);
                    mProgressDialog.setCancelable(isCancellable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Dismiss the progress dialog if it is visible.
     * *
     */
    public void dismissProgressDialog() {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = null;
    }

    public void showShortToastMessages(Context context, String message) {
        Toast.makeText(context, !TextUtils.isEmpty(message) ? message + "" : "", Toast.LENGTH_SHORT)
                .show();
    }

    public boolean isEditTextNotEmpty(EditText editText) {

        if (TextUtils.isEmpty(editText.getText()
                .toString()
                .trim())) {
            return false;
        }
        return true;
    }

    public String getEditTextValue(EditText editText) {

        if (null != editText) {
            return editText.getText()
                    .toString()
                    .trim();
        }
        return "";
    }

    public void showNoInternetAlertDialog(Activity activity) {

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }

            }
        };
        if (null != activity) {
            CustomAlert.showDialog(activity, activity.getString(R.string.no_internet_title), activity.getString(R.string.no_internet_des), activity.getString(R
                    .string.ok), onClickListener, null, onClickListener);
        }
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Context context = view.getContext();
        if (context != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()
                    .placeholder(R.drawable.inventory)
                    .transforms(new CenterCrop(), new RoundedCorners(500));

            GlideApp.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(AppRemoteRepository.BASE_URL + imageUrl + "?contextName=dvpdt_devprodtest")
                    .into(view);

        }
    }
}
