package com.ammar.pharmacy.ordershistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.medicine_tv.setText(pharmacyOrdersItem.getOrderByTexting());
        holder.order_status_tv.setText(pharmacyOrdersItem.getGlobalStatus());
    }

    @Override
    public int getItemCount() {
        return pharmacyOrders.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView date_tv,time_tv,medicine_tv,order_status_tv;
        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            date_tv=itemView.findViewById(R.id.date_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            medicine_tv=itemView.findViewById(R.id.medicine_tv);
            order_status_tv=itemView.findViewById(R.id.order_status_tv);
        }
    }

}
