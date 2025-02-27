package com.first.headerretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("Your Custom Header here")
    @POST("Your End Points here")
    Call<List<ResponseData>> getDetails(@Body UserData userData);
}
