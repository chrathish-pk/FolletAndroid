/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */

package com.follett.fsc.mobile.circdesk.api;

import android.content.Context;


public class AllRestServiceCalls {
    
    private static AllRestServiceCalls allRestServiceCalls;
    private static Context mContext;
    private static APIInterface apiService;
    
    public static AllRestServiceCalls getInstance(Context context) {
        mContext = context;
        
        apiService = APIClient.getClient(APIConstants.BASE_URL)
                .create(APIInterface.class);
        if (allRestServiceCalls == null) { allRestServiceCalls = new AllRestServiceCalls(); }
        
        return allRestServiceCalls;
    }
    
    /* NearBy Section*/
    private void getServiceTypes() {
        /*Call<List<UserServices>> userServiceCall = apiService.getAllUserServices(userId, userType);

        userServiceCall.enqueue(new Callback<List<UserServices>>() {
            @Override
            public void onResponse(Call<List<UserServices>> call, Response<List<UserServices>> response) {
                AppUtils.getInstance().dismissProgressDialog();
                servicesList = response.body();
                AppDataClass.getInstance().setUserServices(servicesList);
                if (servicesList.size() > 0)
                    getUserServiceCategory();
                AppUtils.getInstance().PrintI("ServiceResponseSize-", "" + servicesList.size());
            }

            @Override
            public void onFailure(Call<List<UserServices>> call, Throwable t) {
                AppUtils.getInstance().PrintE("ErrorServiceResponse", t.toString());
                AppUtils.getInstance().dismissProgressDialog();
            }
        });*/
    }
}
