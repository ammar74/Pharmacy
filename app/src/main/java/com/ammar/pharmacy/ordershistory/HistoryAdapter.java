package com.ammar.pharmacy.ordershistory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.pharmacy.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {


    List<PharmacyOrders> pharmacyOrders;
    public static final String TAG="HistoryAdapter";

    public HistoryAdapter(List<PharmacyOrders> pharmacyOrders) {
        this.pharmacyOrders = pharmacyOrders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row, parent, false);
        return new HistoryHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        PharmacyOrders pharmacyOrdersItem=pharmacyOrders.get(position);
        String date = pharmacyOrdersItem.getDate().substring(0,10);
        String time = pharmacyOrdersItem.getDate().substring(11,19);
        holder.date_tv.setText(date);
        holder.time_tv.setText(time);
        if (pharmacyOrdersItem.orderByTexting !=null) {
            holder.medicine_tv.setText(pharmacyOrdersItem.getOrderByTexting());
        }
        else {
            byte[] decodedString= Base64.decode(String.valueOf(pharmacyOrdersItem.orderByPhoto), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.medicine_IV.setImageBitmap((decodedByte));
            holder.medicine_IV.setVisibility(View.VISIBLE);
            holder.medicine_tv.setVisibility(View.GONE);
        }
        holder.order_status_tv.setText(pharmacyOrdersItem.getGlobalStatus());
    }

    @Override
    public int getItemCount() {
        return pharmacyOrders.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView date_tv,time_tv,medicine_tv,order_status_tv;
        ImageView medicine_IV;
        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            date_tv=itemView.findViewById(R.id.date_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            medicine_tv=itemView.findViewById(R.id.medicine_tv);
            order_status_tv=itemView.findViewById(R.id.order_status_tv);
            medicine_IV=itemView.findViewById(R.id.medicine_IV);
        }
    }

}
