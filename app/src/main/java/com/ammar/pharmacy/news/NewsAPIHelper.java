package com.ammar.pharmacy.news;

import com.ammar.pharmacy.retrofit.NetworkAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsAPIHelper {
    public static NewsAPI api;
    public NewsAPIHelper(){


        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(NewsAPI.class);

    }

        }



