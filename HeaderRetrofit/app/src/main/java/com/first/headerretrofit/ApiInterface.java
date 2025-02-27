package com.first.headerretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("company_id: Green Gold Seeds Pvt. Ltd.")
    @POST("/api/Employee/EmployeeDetailGet")
    Call<List<ResponseData>> getDetails(@Body UserData userData);
}
