package com.first.retrofitexample.postapi;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/api/UserLogin/Login")
    Call<List<ResponseData>> loginUser(@Body LoginRequest loginRequest);
}
