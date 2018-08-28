package com.follett.fsc.mobile.circdesk.viewmodel;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.model.HomeMenu;
import com.follett.fsc.mobile.circdesk.interfaces.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.interfaces.SingleLiveEvent;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel<CTAButtonListener> {


    public final ObservableList<HomeMenu> homeMenuItems = new ObservableArrayList<>();
    private final SingleLiveEvent<String> mOpenCheckinCheckoutView = new SingleLiveEvent<>();

    public HomeViewModel(Application application) {
        super(application);
    }

    public SingleLiveEvent<String> getOpenTaskEvent() {
        return mOpenCheckinCheckoutView;
    }

    public void loadHomeMenuItems(Context context) {
        homeMenuItems.add(new HomeMenu(context.getString(R.string.checkInCheckout), R.drawable.out));
        homeMenuItems.add(new HomeMenu(context.getString(R.string.patronStatus), R.drawable.patron));
        homeMenuItems.add(new HomeMenu(context.getString(R.string.itemStatus), R.drawable.status));
        homeMenuItems.add(new HomeMenu(context.getString(R.string.inventory), R.drawable.inventory));
        //homeMenuItems.add(new HomeMenu(context.getString(R.string.receive), R.drawable.receive));
    }


}
