package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.ActivityItemStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

public class ItemStatusActivity extends BaseActivity<ItemStatusViewModel> implements View.OnClickListener, UpdateItemUIListener {

    private ItemDetails itemDetailsinfo;
    private ActivityItemStatusBinding activityItemStatusBinding;
    private ItemStatusViewModel itemStatusViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemStatusBinding = putContentView(R.layout.activity_item_status);
        setTitleBar(getString(R.string.item_status_title));
        setBackBtnVisible();
        itemStatusViewModel = new ItemStatusViewModel(getApplication(), new AppRemoteRepository(), this);
        baseBinding.backBtn.setOnClickListener(this);
        activityItemStatusBinding.itemStatusPatronGoBtn.setOnClickListener(this);
        activityItemStatusBinding.itemStatusCheckedoutInfoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.itemStatusPatronGoBtn)
        {
            AppUtils.getInstance()
                    .hideKeyBoard(this, activityItemStatusBinding.itemStatusPatronEntry);
            if (AppUtils.getInstance().isEditTextNotEmpty(activityItemStatusBinding.itemStatusPatronEntry)) {
                String barcode = AppSharedPreferences.getInstance(this).getString(AppSharedPreferences.KEY_BARCODE);
                if (TextUtils.isEmpty(barcode)) {
                    itemStatusViewModel.getScanItem(activityItemStatusBinding.itemStatusPatronEntry.getText().toString().trim());
                }
            } else {
                AppUtils.getInstance()
                        .showShortToastMessages(this, getString(R.string.errorPatronEntry));
            }
        }
        else if (v.getId() == R.id.itemStatusCheckedoutInfoBtn) {

            Intent titleIntent = new Intent(this, TitleInfoActivity.class);
            String bidID = Integer.toString(itemDetailsinfo.getBibID());
            titleIntent.putExtra("bibID",bidID);
            Log.i("TAG","BIDID :"+bidID);
            startActivity(titleIntent);

        }
        else if (v.getId() == R.id.backBtn)
            finish();
    }

    @Override
    public void updateUI(final Object itemDetails) {

        final Object itemDetailsValue = itemDetails;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                itemDetailsinfo = (ItemDetails) itemDetailsValue;
                if (itemDetailsinfo.getSuccess().equals(true))
                {
                    activityItemStatusBinding.itemStatusDetailLayout.setVisibility(View.VISIBLE);
                    activityItemStatusBinding.itemStatusCheckedoutCall.setVisibility(View.VISIBLE);
                    activityItemStatusBinding.itemStatusCheckoutDetailsLayout.setVisibility(View.VISIBLE);
                    activityItemStatusBinding.itemStatusCheckedoutName.setText(itemDetailsinfo.getTitle());
                    activityItemStatusBinding.itemStatusCheckedoutType.setText(itemDetailsinfo.getBarcode());
                    activityItemStatusBinding.itemStatusCheckedoutDue.setText("Status :" + itemDetailsinfo.getStatus());
                    activityItemStatusBinding.itemStatusCheckedoutCall.setText("Call #: " + itemDetailsinfo.getCallNumber());
                    CurrentCheckout currentCheckout = itemDetailsinfo.getCurrentCheckout();
                    if (currentCheckout != null && !currentCheckout.isEmpty()) {

                        activityItemStatusBinding.itemStatusLayoutCheckoutDetails.setVisibility(View.VISIBLE);
                        activityItemStatusBinding.itemStatusLayoutCheckoutNone.setVisibility(View.GONE);
                        activityItemStatusBinding.itemStatusCheckoutToName.setText(itemDetailsinfo.getCurrentCheckout().getCheckedOutToName());
                        activityItemStatusBinding.itemStatusCheckoutBarcode.setText(itemDetailsinfo.getCurrentCheckout().getCheckedOutToBarcode());
                        activityItemStatusBinding.itemStatusCheckoutDueDate.setText("Due : "+itemDetailsinfo.getCurrentCheckout().getDateDue());
                    }
                    else
                    {
                        activityItemStatusBinding.itemStatusLayoutCheckoutNone.setVisibility(View.VISIBLE);
                        activityItemStatusBinding.itemStatusLayoutCheckoutDetails.setVisibility(View.GONE);
                    }
                }
            }
        });


    }
}
