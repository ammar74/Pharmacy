package com.ammar.pharmacy.more;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.pharmacy.AboutUsFragment;
import com.ammar.pharmacy.R;
import com.ammar.pharmacy.login.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import splitties.init.AppCtxInitProvider;
import splitties.init.AppCtxKt;

import static com.ammar.pharmacy.login.LoginFragment.token_key;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MoreHolder> {
    List<MoreInfoItem> moreInfoList;
    LayoutInflater layoutInflater;
    Context context;
    onItemClick onItemClick;

    public static final String TAG="MoreAdapter";
    public MoreAdapter(List<MoreInfoItem> moreInfoList, Context context) {
        this.moreInfoList = moreInfoList;
        layoutInflater=LayoutInflater.from(context);
        this.context=context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoreHolder onCreateViewHolder(@NonNull ViewGroup parent,final int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.more_row,parent,false);
        return new MoreHolder(root);

    }

    public void setOnItemClick(onItemClick onItemClick){
        this.onItemClick=onItemClick;
    }

    @Override
    public void onBindViewHolder(@NonNull MoreHolder holder, int position) {
        MoreInfoItem moreInfoItem =moreInfoList.get(position);
        holder.item_icon.setImageResource(moreInfoItem.getIcon());
        holder.item_tv.setText(moreInfoItem.name);

    }

    @Override
    public int getItemCount() {
        return moreInfoList.size();
    }

    public class MoreHolder extends RecyclerView.ViewHolder {
        ImageView item_icon;
        TextView item_tv;

        public MoreHolder(@NonNull View itemView) {
            super(itemView);
            item_icon= itemView.findViewById(R.id.item_icon);
            item_tv= itemView.findViewById(R.id.item_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick!=null)
                        onItemClick.onClickItem(getAdapterPosition());
                }
            });
        }
    }

}
