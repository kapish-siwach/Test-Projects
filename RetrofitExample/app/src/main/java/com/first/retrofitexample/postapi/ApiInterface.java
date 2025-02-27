package com.first.retrofitexample.postapi;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("<EndPoint Here>")
    Call<List<ResponseData>> loginUser(@Body LoginRequest loginRequest);
}
