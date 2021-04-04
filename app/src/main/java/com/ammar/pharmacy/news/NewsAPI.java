package com.ammar.pharmacy.news;

import com.ammar.pharmacy.login.LoginReturnBody;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
   @GET("top-headlines")
    public Call<NewsResponse> loadNews(@Query("country") String country,
                                       @Query("category")String category,@Query("apiKey") String apiKey);

    }


