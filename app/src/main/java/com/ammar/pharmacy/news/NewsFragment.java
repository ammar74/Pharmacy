package com.ammar.pharmacy.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ammar.pharmacy.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

import static com.ammar.pharmacy.news.NewsAPIHelper.api;


public class NewsFragment extends Fragment {
    RecyclerView rv;
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

        NewsAdapter adapter=new NewsAdapter(articles);
        rv=view.findViewById(R.id.news_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    public void loadNews(String country, String category, String apiKey){
        NewsAPI newsAPI=new NewsAPI() {
            @Override
            public Call<NewsResponse> loadNews(String country, String category, String apiKey) {
                api.loadNews("eg","health","4b9d06a498454da48992eec2590e60d2")
                        .enqueue(new Callback<NewsResponse>);
                return null;
            }
        };


        }
    }



