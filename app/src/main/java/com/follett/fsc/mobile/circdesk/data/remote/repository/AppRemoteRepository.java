/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.model.Version;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIClient;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;

import io.reactivex.Observable;

public class AppRemoteRepository implements AppRepository {

    private APIInterface apiService;

    public static final String BASE_URL = "https://devprodtest.follettdestiny.com";

    public String scanPatronString = "{\n" +
            "\t\"scanPatronResult\": {\n" +
            "\t\t\"assetCheckouts\": \"0\",\n" +
            "\t\t\"assetOverdues\": \"0\",\n" +
            "\t\t\"libraryCheckouts\": \"0\",\n" +
            "\t\t\"libraryOverdues\": \"0\",\n" +
            "\t\t\"messages\": \"\",\n" +
            "\t\t\"patronList\": {\n" +
            "\t\t\t\"patron\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"barcode\": \"P 990836\",\n" +
            "\t\t\t\t\t\"lastFirstMiddleName\": \"Campbell, Tom\",\n" +
            "\t\t\t\t\t\"patronID\": \"861\",\n" +
            "\t\t\t\t\t\"patronPictureFileName\": \"/imagestore/patrons/1534778744727_screenshot20180816at12.43.54pm.jpg\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"barcode\": \"P 990785\",\n" +
            "\t\t\t\t\t\"lastFirstMiddleName\": \"Feldmann, Tom\",\n" +
            "\t\t\t\t\t\"patronID\": \"463\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"barcode\": \"P 990851\",\n" +
            "\t\t\t\t\t\"lastFirstMiddleName\": \"Lam, Tom\",\n" +
            "\t\t\t\t\t\"patronID\": \"876\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t\"patronNotes\": \"\",\n" +
            "\t\t\"success\": \"true\",\n" +
            "\t\t\"textbookCheckouts\": \"0\",\n" +
            "\t\t\"textbookOverdues\": \"0\"\n" +
            "\t}\n" +
            "}";




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

    @Override
    public Observable<LoginResults> getLoginResult() {
        return apiService.getLoginResult("dvpdt_devprodtest", "FDPSA", "COGNITE",
                "pk", "pk", "DestinyCirc",
                "Android_24_7.0_lge_lucye_LG-H870DS", "1_Android", "English");
    }


}
