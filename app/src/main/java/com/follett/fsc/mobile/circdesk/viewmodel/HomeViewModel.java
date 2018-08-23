package com.follett.fsc.mobile.circdesk.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.interfaces.BasicNavigator;
import com.follett.fsc.mobile.circdesk.interfaces.SingleLiveEvent;
import com.follett.fsc.mobile.circdesk.model.HomeMenuModel;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel<BasicNavigator> {

    public final ObservableList<HomeMenuModel> homeMenuItems = new ObservableArrayList<>();
    private final SingleLiveEvent<String> mOpenCheckinCheckoutView = new SingleLiveEvent<>();

    public HomeViewModel() {
        super();
    }

    public SingleLiveEvent<String> getOpenTaskEvent() {
        return mOpenCheckinCheckoutView;
    }

    public void loadHomeMenuItems(Context context) {
        homeMenuItems.add(new HomeMenuModel(context.getString(R.string.checkInCheckout), R.drawable.checkin));
        homeMenuItems.add(new HomeMenuModel(context.getString(R.string.patronStatus), R.drawable.patron_status));
        homeMenuItems.add(new HomeMenuModel(context.getString(R.string.itemStatus), R.drawable.item_status));
        homeMenuItems.add(new HomeMenuModel(context.getString(R.string.inventory), R.drawable.inventory));
        homeMenuItems.add(new HomeMenuModel(context.getString(R.string.receive), R.drawable.receive));
    }


}
