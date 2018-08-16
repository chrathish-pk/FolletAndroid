/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */

package com.follett.fsc.mobile.circdesk.api;
        
        import okhttp3.MultipartBody;
        import retrofit2.Call;
        import retrofit2.http.Multipart;
        import retrofit2.http.POST;
        import retrofit2.http.Part;
        import retrofit2.http.Query;

public interface APIInterface {
    @Multipart
    @POST("userdetails.php")
    Call<String> userDetails(@Query("name") String nameValue, @Query("phone") String phoneValue, @Query("email") String emailValue, @Query("password") String
            passwordValue, @Part MultipartBody.Part userfile);
    
}
