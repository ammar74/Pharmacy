package com.ammar.pharmacy.news;


import java.util.ArrayList;
import java.util.ArrayList;

public class NewsResponse {
    String status ;
    int totalResults ;
    ArrayList<Article> articles=new ArrayList<Article>();

    public NewsResponse(String status, int totalResults, ArrayList<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles=articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
