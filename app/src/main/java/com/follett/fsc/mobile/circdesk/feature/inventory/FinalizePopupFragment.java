package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.InventoryPopupBinding;

public class FinalizePopupFragment extends DialogFragment {

    private InventoryPopupBinding inventoryPopupBinding;
    private FinalizeViewModel finalizeViewModel;
    private View rootView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.inventory_popup, null);

        setCancelable(false);

        builder.setView(view);
        Dialog dialog = builder.create();

        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        return dialog;

    }
}
