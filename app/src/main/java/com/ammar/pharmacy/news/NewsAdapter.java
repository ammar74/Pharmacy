package com.ammar.pharmacy.news;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.pharmacy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

   public ArrayList<Article> articles ;
   public static final String TAG="NewsAdapter";

   public NewsAdapter(ArrayList<Article> articles){
       this.articles=articles;
       notifyDataSetChanged();
   }


    public class NewsHolder extends RecyclerView.ViewHolder {
        ImageView itemPoster;
        TextView itemTitle;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            itemPoster= itemView.findViewById(R.id.news_item_imageView);
            itemTitle= itemView.findViewById(R.id.news_item_textView);
            View viewHolderView = itemView;

        }
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row,parent,false);
        return new NewsHolder(root);


    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        Article  articleItem= articles.get(position);
        holder.itemTitle.setText(articleItem.title) ;
        Log.d(TAG,"news Title is "+articleItem.title);
        String imagePath = articleItem.urlToImage;
        Picasso.get()
                .load(imagePath)
                .placeholder(R.drawable.medicines_news)
                .into(holder.itemPoster);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


}
