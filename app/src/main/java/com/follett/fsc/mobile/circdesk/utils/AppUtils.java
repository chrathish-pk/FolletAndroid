/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */

package com.follett.fsc.mobile.circdesk.utils;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.interfaces.AlertDialogListener;
import com.follett.fsc.mobile.circdesk.view.alert.Alert;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    
    public void showAlertDialog(boolean isWarning, String buttonName, final String msg, final Context context, final AlertDialogListener alertDialogListener,
            final int statusCode) {
        String title, btnName;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context, android.R.style.Theme_DeviceDefault_Light_Dialog));
        if (isWarning) {
            title = context.getResources()
                    .getString(R.string.alert_success);
        } else {
            title = context.getResources()
                    .getString(R.string.alert_success);
        }
        btnName = buttonName;
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setCancelable(false);
        if (buttonName.equals(context.getResources()
                .getString(R.string.ok)) || buttonName.equals(context.getResources()
                .getString(R.string.yes)) || buttonName.equals(context.getResources()
                .getString(R.string.update))) {
            
            alertDialogBuilder.setPositiveButton(btnName, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    alertDialogListener.onPositiveButtonClick(statusCode);
                }
            });
        } else if (buttonName.equalsIgnoreCase(context.getResources()
                .getString(R.string.net))) {
            alertDialogBuilder.setPositiveButton(context.getResources()
                    .getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    alertDialogListener.onPositiveButtonClick(statusCode);
                }
            });
        }
        
        
        if (isWarning) {
            if (buttonName.equals(context.getResources()
                    .getString(R.string.yes)) || buttonName.equals(context.getResources()
                    .getString(R.string.update))) {
                alertDialogBuilder.setNegativeButton(context.getResources()
                        .getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    
                    }
                });
            } else if (buttonName.equalsIgnoreCase(context.getResources()
                    .getString(R.string.ok))) {
                alertDialogBuilder.setNegativeButton(context.getResources()
                        .getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
            }
        }
        
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        
        
        TextView messageView = (TextView) alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER_HORIZONTAL);
        
        TextView titleView = (TextView) alertDialog.findViewById(context.getResources()
                .getIdentifier("alertTitle", "id", "android"));
        if (titleView != null) {
            titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        }
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
            Alert.showDialog(activity, activity.getString(R.string.no_internet_title), activity.getString(R.string.no_internet_des), activity.getString(R
                    .string.ok), onClickListener, null, onClickListener);
        }
    }
}
