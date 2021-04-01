package com.ammar.pharmacy.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.pharmacy.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    List<Order> orderList;
    LayoutInflater layoutInflater;
    Context context;
   OrdersAdapter.onOrderClick onOrderClick;

    public HistoryAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        layoutInflater=LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = layoutInflater.from(parent.getContext()).inflate(R.layout.order_row, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Order order=orderList.get(position);
        holder.date_tv.setText(order.getDate());
        holder.time_tv.setText(order.getTime());
        holder.medicine_tv.setText(order.getMedcine());
        holder.order_status_tv.setText(order.getStatus().toString());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_tv,time_tv,medicine_tv,order_status_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date_tv=itemView.findViewById(R.id.date_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            medicine_tv=itemView.findViewById(R.id.medicine_tv);
            order_status_tv=itemView.findViewById(R.id.order_status_tv);

            medicine_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onOrderClick!= null)
                    {
                    }


                }
            });

        }
    }

}
