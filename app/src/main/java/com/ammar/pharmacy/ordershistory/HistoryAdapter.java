package com.ammar.pharmacy.ordershistory;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.pharmacy.MainActivity;
import com.ammar.pharmacy.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {


    List<PharmacyOrders> pharmacyOrders;
    Context context;
    Dialog dialog;
    public static final String TAG="HistoryAdapter";


    public HistoryAdapter(List<PharmacyOrders> pharmacyOrders, Context context) {
        this.pharmacyOrders = pharmacyOrders;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row, parent, false);
        HistoryHolder historyHolder=new HistoryHolder(root);

        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_order);

       historyHolder.order_item_id.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               TextView PrescriptionDetails_tv= dialog.findViewById(R.id.PrescriptionDetails_tv);
               TextView customer_name=dialog.findViewById(R.id.customer_name);
               TextView customer_phone=dialog.findViewById(R.id.customer_phone);
               TextView customer_address=dialog.findViewById(R.id.customer_address);

               PrescriptionDetails_tv.setText(pharmacyOrders.get(historyHolder.getAdapterPosition()).orderByTexting);
               customer_name.setText(pharmacyOrders.get(historyHolder.getAdapterPosition()).customerID);
               customer_phone.setText(pharmacyOrders.get(historyHolder.getAdapterPosition()).date);

               Toast.makeText(context,"test click "+String.valueOf(historyHolder.getAdapterPosition())
                       ,Toast.LENGTH_SHORT).show();
               dialog.show();
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
           }
       });
        return historyHolder;
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

    public class HistoryHolder extends RecyclerView.ViewHolder  {
        TextView date_tv,time_tv,medicine_tv,order_status_tv;
        ImageView medicine_IV;
        CardView order_item_id;
        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            date_tv=itemView.findViewById(R.id.date_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            medicine_tv=itemView.findViewById(R.id.medicine_tv);
            order_status_tv=itemView.findViewById(R.id.order_status_tv);
            medicine_IV=itemView.findViewById(R.id.medicine_IV);
            order_item_id=itemView.findViewById(R.id.order_item_id);
        }


    }

//    public interface onOrderListener {
//        public void onClickItem(int position);
//    }

}
