package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;

public class SeenOnOrAfterViewModel extends BaseViewModel {

    private ItemClickListener itemClickListener;

    public SeenOnOrAfterViewModel(@NonNull Application application,ItemClickListener itemClickListener) {
        super(application);
        this.itemClickListener=itemClickListener;
    }


    public void onItemClicked(View view){
        itemClickListener.onItemClick(view, 0);
    }
}
