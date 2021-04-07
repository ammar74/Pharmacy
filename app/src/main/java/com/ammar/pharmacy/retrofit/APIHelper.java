    package com.ammar.pharmacy.retrofit;

import com.ammar.pharmacy.retrofit.NetworkAPI;

import java.sql.ClientInfoStatus;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    public static NetworkAPI api;

    public APIHelper ()
    {
        OkHttpClient client   =  new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES) // write timeout
                .readTimeout(15, TimeUnit.MINUTES) // read timeout
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.7:3000/")
                .addConverterFactory(GsonConverterFactory.create())
               // .client(client)
                .build();
        api = retrofit.create(NetworkAPI.class);
    }


}
