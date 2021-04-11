package com.ammar.pharmacy.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ammar.pharmacy.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.news.NewsAPIHelper.api;


public class NewsFragment extends Fragment {
    RecyclerView rv;
    ProgressBar progressBar;
    ArrayList<Article> articles;
    private static final String TAG = "NewsFragment";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv=view.findViewById(R.id.news_rv);
        progressBar=view.findViewById(R.id.progressBar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
            }
        },500);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        loadNews("eg","health","4b9d06a498454da48992eec2590e60d2");



    }


    public ArrayList<Article> loadNews(String country, String category, String apiKey) {
        new NewsAPIHelper();
        api.loadNews(country,category,apiKey).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse newsResponse=response.body();
                Log.d(TAG,"message "+response.body().articles);
                if(newsResponse.articles!=null){
                    NewsAdapter adapter=new NewsAdapter(newsResponse.articles);
                    rv.setAdapter(adapter);
                }
                else {
                    articles=null;
                    Log.d(TAG,"message: news is null");
                }

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d(TAG,  "message ",t);

            }
        });

        return articles;

    }
    }




