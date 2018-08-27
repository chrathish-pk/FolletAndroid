package com.follett.fsc.mobile.circdesk.view.fragment;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.common.AppConstants;
import com.follett.fsc.mobile.circdesk.data.model.SiteRecord;
import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIClient;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;
import com.follett.fsc.mobile.circdesk.interfaces.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.view.adapter.SchoolListAdapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolListFragment extends Fragment {
    
    private static final String TAG = LoginFragment.class.getSimpleName();
    
    public static final String BASE_URL = "https://devprodtest.follettdestiny.com";
    
    private LayoutInflater mInflater;
    List<SiteRecord> schoolList;
    RecyclerView schoolListView;
    SchoolListAdapter schoolListAdapter;
    TextView noSchoolListText;
    ProgressBar schoolListProgressBar;
    
    private NavigationListener navigationListener;
    
    
    public static SchoolListFragment newInstance() {
        Bundle args = new Bundle();
        SchoolListFragment fragment = new SchoolListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            navigationListener = (NavigationListener) context;
        } catch (ClassCastException ex) {
            FollettLog.e(TAG, "ClassCastException");
        }
    }
    
    /**
     * base string used in Logger TAG building
     */
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        
        this.mInflater = inflater;
        View view = this.mInflater.inflate(R.layout.fragment_school_list, container, false);
        
        APIInterface apiService = APIClient.getClient(BASE_URL)
                .create(APIInterface.class);
        
        schoolListView = (RecyclerView) view.findViewById(R.id.schoolRecyclerview);
        schoolListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noSchoolListText = (TextView) view.findViewById(R.id.noschoollist);
        schoolListProgressBar = (ProgressBar) view.findViewById(R.id.schoollist_progressbar);
        schoolListProgressBar.setVisibility(View.VISIBLE);
        Call<SiteResults> call = apiService.getSchoolList("dvpdt_devprodtest", "COGNITE", "library,textbook,asset", AppConstants.APP_ID, AppConstants
                .CLIENT_NAME, "1_Android", AppConstants.APP_LANGUAGE);
        call.enqueue(new Callback<SiteResults>() {
            @Override
            public void onResponse(Call<SiteResults> call, Response<SiteResults> response) {
                if (response.body() != null) {
                    SchoolListFragment.this.schoolList = response.body().sites;
                    Log.d("TAG", "Response = " + SchoolListFragment.this.schoolList);
                    if (SchoolListFragment.this.schoolList != null) {
                        schoolListAdapter = new SchoolListAdapter(getActivity(), SchoolListFragment.this.schoolList);
                        schoolListView.setAdapter(schoolListAdapter);
                        schoolListProgressBar.setVisibility(View.INVISIBLE);
                        
                    } else {
                        noSchoolListText.setVisibility(View.VISIBLE);
                        schoolListProgressBar.setVisibility(View.INVISIBLE);
                        
                    }
                } else {
                    Toast.makeText(getActivity(), "Internal server error", Toast.LENGTH_LONG)
                            .show();
                    noSchoolListText.setVisibility(View.VISIBLE);
                    schoolListProgressBar.setVisibility(View.INVISIBLE);
                    
                }
            }
            
            @Override
            public void onFailure(Call<SiteResults> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
        
        
        return view;
    }
    
    @Override
    public void onDetach() {
        navigationListener.setToolBarTitle(getActivity().getString(R.string.connect_your_school_label));
        super.onDetach();
    }
}

//    AppRemoteRepository appRemoteRepository = new AppRemoteRepository();
//        appRemoteRepository.getSchoolList()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribeWith(new DisposableObserver<SiteResults>() {
//@Override
//public void onNext(SiteResults value) {
////                        if (response.body() != null) {
//        SchoolListFragment.this.schoolList = value.sites;
//        Log.d("TAG", "Response = " + SchoolListFragment.this.schoolList);
//        if (SchoolListFragment.this.schoolList != null) {
//        schoolListAdapter = new SchoolListAdapter(getActivity(), SchoolListFragment.this.schoolList);
//        schoolListView.setAdapter(schoolListAdapter);
//        schoolListProgressBar.setVisibility(View.INVISIBLE);
//
//        } else {
//        noSchoolListText.setVisibility(View.VISIBLE);
//        schoolListProgressBar.setVisibility(View.INVISIBLE);
//
//        }
//        }
//
//@Override
//public void onError(Throwable e) {
//
//        }
//
//@Override
//public void onComplete() {
//
//        }
//        });
//
