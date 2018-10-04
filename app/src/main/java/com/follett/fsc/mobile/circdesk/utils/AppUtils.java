/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */

package com.follett.fsc.mobile.circdesk.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.AlertDialogListener;
import com.follett.fsc.mobile.circdesk.app.CustomAlert;
import com.follett.fsc.mobile.circdesk.app.GlideApp;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;

public class AppUtils {

    private static AppUtils mInstance = null;
    private AlertDialog alertDialog;
    private ProgressDialog mProgressDialog;

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

            FollettLog.d(AppConstants.EXCEPTION, e.getMessage());
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
            FollettLog.d(AppConstants.EXCEPTION, e.getMessage());
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
            if (ctx instanceof Application && applicationInForeground(ctx)) {
                mProgressDialog = ProgressDialog.show(ctx, title, body, true);
                mProgressDialog.setIcon(icon);
                mProgressDialog.setCancelable(isCancellable);
            }
        } catch (Exception e) {
            FollettLog.d(AppConstants.EXCEPTION, e.getMessage());
        }
    }

    private boolean applicationInForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> services = null;
        if (activityManager != null) {
            services = activityManager.getRunningAppProcesses();
            boolean isActivityFound = false;

            if (services.get(0).processName
                    .equalsIgnoreCase(context.getPackageName())) {
                isActivityFound = true;
            }
            return isActivityFound;
        }
        return false;
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

        return !(TextUtils.isEmpty(editText.getText().toString().trim()));

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

                if (which == DialogInterface.BUTTON_POSITIVE) {
                    dialog.dismiss();
                }
            }
        };
        if (null != activity) {
            CustomAlert.showDialog(activity, activity.getString(R.string.no_internet_title), activity.getString(R.string.no_internet_des), activity.getString(R
                    .string.ok), onClickListener, null, onClickListener);
        }
    }

    public void showAlertDialog(Activity activity, String message) {

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == DialogInterface.BUTTON_POSITIVE) {
                    dialog.dismiss();
                }
            }
        };
        if (null != activity) {
            CustomAlert.showDialog(activity, null, message, activity.getString(R.string.ok), onClickListener, null, onClickListener);
        }
    }

    @BindingAdapter({"bind:itemImageUrl"})
    public static void loadItemImage(ImageView view, String imageUrl) {
        Context context = view.getContext();
        if (context != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()
                    .placeholder(R.drawable.inventory);


            GlideApp.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(AppRemoteRepository.getInstance().getString(SERVER_URI_VALUE) + imageUrl)
                    .into(view);

        }
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Context context = view.getContext();
        if (context != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()
                    .placeholder(R.drawable.avatar)
                    .transforms(new CenterCrop(), new RoundedCorners(500));

            GlideApp.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(AppRemoteRepository.getInstance().getString(SERVER_URI_VALUE)
                            + imageUrl + "?contextName=" + AppSharedPreferences.getInstance()
                            .getString(KEY_CONTEXT_NAME))
                    .into(view);

        }
    }

    public void showAlertDialog(final Context context, String title, final String msg, String positiveBtnName, String negativeBtnName, final AlertDialogListener alertDialogListener, final int statusCode) {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(
                    context, android.R.style.Theme_DeviceDefault_Light_Dialog));

            if (title != null)
                alertDialogBuilder.setTitle(title);
            alertDialogBuilder.setMessage(msg);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setNegativeButton(negativeBtnName, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialogListener.onNegativeButtonClick(statusCode);
                }
            });

            alertDialogBuilder.setPositiveButton(positiveBtnName, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    alertDialogListener.onPositiveButtonClick(statusCode);
                }
            });


            if (context != null && alertDialog != null && alertDialog.isShowing()) {
                alertDialog.dismiss();
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            } else {
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
            if (context != null) {
                TextView messageView = alertDialog.findViewById(android.R.id.message);
                messageView.setTextColor(ContextCompat.getColor(context,R.color.editTextBgColor));

                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                Button neutralButton = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);

                positiveButton.setTextColor(ContextCompat.getColor(context,R.color.blueLabel));
                negativeButton.setTextColor(ContextCompat.getColor(context,R.color.blueLabel));
                neutralButton.setTextColor(ContextCompat.getColor(context,R.color.blueLabel));
            }

        } catch (Exception e) {
            FollettLog.d(AppConstants.EXCEPTION, e.getMessage());
        }

    }

    public Map<String, String> getHeader(Context context) {
        if (context == null) {
            return new HashMap<>();
        }

        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppRemoteRepository.getInstance()
                .getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        return map;
    }

    public void clearSchoolPref() {
        AppSharedPreferences.getInstance()
                .removeValues(KEY_SITE_SHORT_NAME);
        AppSharedPreferences.getInstance()
                .removeValues(KEY_SITE_ID);
        AppSharedPreferences.getInstance()
                .removeValues(KEY_SITE_NAME);
    }

    public String getFormatDate(String dateValue) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy");
            DateFormat outputFormat = new SimpleDateFormat("YYYYMMdd");
            Date date = inputFormat.parse(dateValue);
            String outputDateStr = outputFormat.format(date);
            return outputDateStr;
        } catch (ParseException e) {
            FollettLog.e("Error", e.getMessage());
        }

        return null;
    }
}
