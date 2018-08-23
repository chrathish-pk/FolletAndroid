/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.model.Version;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIClient;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;

import io.reactivex.Observable;

public class AppRemoteRepository implements AppRepository {

    private APIInterface apiService;

    public static final String BASE_URL = "https://devprodtest.follettdestiny.com";

    public AppRemoteRepository() {
        apiService = APIClient.getClient(BASE_URL)
                .create(APIInterface.class);
    }


    //    @Override
//    public Observable<List<Movie>> getPopularMovies(int page) {
//        Observable<DiscoverMoviesResponse> discoverMoviesResponseObservable = apiService.discover("popularity.desc", page, ApiUtils.getApiKey());
//        return discoverMoviesResponseObservable.flatMap(new Function<DiscoverMoviesResponse, ObservableSource<? extends List<Movie>>>() {
//            @Override
//            public ObservableSource<? extends List<Movie>> apply(DiscoverMoviesResponse discoverMoviesResponse) throws Exception {
//                return Observable.just(discoverMoviesResponse.getResults());
//            }
//        });
//    }
//
//    @Override
//    public Observable<Movie> getMovieDetails(long movieId) {
//        return apiService.getMovieDetails(movieId, ApiUtils.getApiKey());
//    }

    @Override
    public Observable<Version> getVersion() {


        return apiService.getVersion("dvpdt_devprodtest", "COGNITE", "CircDeskMobile", "Android_24_7.0_lge_lucye_LG-H870DS", "1_Android", "English");

//        return versionObservable.flatMap(new Function<Version, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(Version version) throws Exception {
//                return Observable.just(version.getVersion());
//            }
//        });
    }

    @Override
    public Observable<ScanPatron> getScanPatron() {
        return apiService.getScanPatron("dvpdt_devprodtest", "FDPSA", "COGNITE",
                "tom", "DestinyCirc", "Android_24_7.0_lge_lucye_LG-H870DS",
                "1_Android", "English");
    }


}
