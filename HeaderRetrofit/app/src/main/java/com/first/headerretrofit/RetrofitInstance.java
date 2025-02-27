package com.first.headerretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL="Your custom Base URL HERE";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
       if (retrofit==null){
           retrofit=new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit;
    }
    public static ApiInterface getApiService(){
        return getRetrofitInstance().create(ApiInterface.class);
    }
}
