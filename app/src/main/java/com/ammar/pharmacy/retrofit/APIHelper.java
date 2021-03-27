package com.ammar.pharmacy.retrofit;

import com.ammar.pharmacy.retrofit.NetworkAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    public static NetworkAPI api;

    public APIHelper ()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.34:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(NetworkAPI.class);
    }
}
