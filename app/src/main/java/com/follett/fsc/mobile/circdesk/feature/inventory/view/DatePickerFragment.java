package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements View.OnClickListener, DatePicker.OnDateChangedListener {

    private View rootView;
    private String formattedDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_calendar, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatePicker calendar = rootView.findViewById(R.id.calendar);
        int year = calendar.getYear();
        int month = calendar.getMonth();
        int day = calendar.getDayOfMonth();

        calendar.setMaxDate(System.currentTimeMillis());

        (rootView.findViewById(R.id.ok)).setOnClickListener(this);
        (rootView.findViewById(R.id.cancel)).setOnClickListener(this);
        ((DatePicker) rootView.findViewById(R.id.calendar)).init(year, month, day, this);
    }


    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Create a Date variable/object with user chosen date
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cal.getTimeInMillis());
        cal.set(year, monthOfYear, dayOfMonth, cal.getTime().getHours(), cal.getTime().getMinutes(), cal.getTime().getSeconds());
        Date chosenDate = cal.getTime();

        // Format the date using style and locale
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        formattedDate = df.format(chosenDate);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ok) {
            if (formattedDate != null) {
                AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_SEEN_DATE, formattedDate);
                AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_SEEN_FORMAT_DATE, AppUtils.getInstance().getFormatDate(formattedDate));
            } else {
                Date c = Calendar.getInstance().getTime();
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
                formattedDate = df.format(c);
                AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_SEEN_DATE, formattedDate);
                AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_SEEN_FORMAT_DATE, AppUtils.getInstance().getFormatDate(formattedDate));
            }
            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedDateLiveData.postValue(formattedDate);
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            dismiss();
        } else if (v.getId() == R.id.cancel) {
            dismiss();
        }
    }

}
