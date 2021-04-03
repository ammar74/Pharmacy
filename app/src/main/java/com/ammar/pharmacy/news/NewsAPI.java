package com.ammar.pharmacy.news;

import com.ammar.pharmacy.login.LoginReturnBody;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET("top-headlines")

        @Query("country") String country=
            @Query("category")category: String?,
    @Query("apiKey")
     Call<NewsResponse> String apiKey;


    }

}
