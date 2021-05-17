package com.ammar.pharmacy.ordershistory;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.util.Log;
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
import com.ammar.pharmacy.acceptedorders.OrderDetailsReturn;
import com.ammar.pharmacy.retrofit.APIHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.retrofit.APIHelper.api;

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


        return historyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        PharmacyOrders pharmacyOrdersItem=pharmacyOrders.get(position);
        String date = pharmacyOrdersItem.getDate().substring(0,10);
        String time = pharmacyOrdersItem.getDate().substring(11,19);
        holder.date_tv.setText(date);
        holder.time_tv.setText(time);
        if(pharmacyOrdersItem.orderByTexting!=null)
        {holder.medicine_tv.setText(pharmacyOrdersItem.getOrderByTexting());}
        holder.order_status_tv.setText(pharmacyOrdersItem.getGlobalStatus());

        holder.order_item_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView PrescriptionDetails_tv= dialog.findViewById(R.id.PrescriptionDetails_tv);
                TextView customer_name=dialog.findViewById(R.id.customer_name);
                TextView customer_phone=dialog.findViewById(R.id.customer_phone);
                TextView customer_address=dialog.findViewById(R.id.customer_address);
                TextView order_date= dialog.findViewById(R.id.order_date_tv);
                TextView order_time=dialog.findViewById(R.id.order_time_tv);
//                ImageView  medicine_IV=dialog.findViewById(R.id.medicine_IV);


                PrescriptionDetails_tv.setText(pharmacyOrdersItem.orderByTexting);
                customer_name.setText("Amr");
                customer_phone.setText("01115456789");
                String date = pharmacyOrders.get(holder.getAdapterPosition()).date.substring(0,10);
                String time = pharmacyOrders.get(holder.getAdapterPosition()).date.substring(11,19);
                order_date.setText(date);
                order_time.setText(time);
             //   orderInfo(pharmacyOrdersItem._id);


//               Toast.makeText(context,"test click "+String.valueOf(pharmacyOrdersItem)
//                       ,Toast.LENGTH_SHORT).show();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        });
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

    public void orderInfo(String orderId){
        new APIHelper();
        api.orderInfo(orderId).enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                OrderDetailsResponse body= response.body();
  //              String message= body.toString();
                Log.d(TAG," order info is  "+body);
//                if (body.getOrder() != null){
//                    Log.d(TAG,body.toString());
//                    String orderId =body.getOrder().get_id();
//                    Log.d(TAG,"onResponse: order id"+orderId);
//                    String date = body.getOrder().getDate().substring(0,10);
//                    String time = body.getOrder().getDate().substring(11,19);
//                    .setText(time);
//                    order_date_tv.setText(date);
//                    if (body.getOrder().getOrderByTexting() !=null)
//                        PrescriptionDetails_tv.setText(body.getOrder().getOrderByTexting());
//                    else {PrescriptionDetails_tv.setVisibility(View.INVISIBLE);}
//                    if(body.getOrder().getOrderByPhoto() != null) {
//                        byte[] decodedString= Base64.decode(String.valueOf(body.getOrder().getOrderByPhoto()), Base64.DEFAULT);
//                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        PrescriptionDetails_imageView.setImageBitmap((decodedByte));
//                        PrescriptionDetails_imageView.setVisibility(View.VISIBLE);
//                    }else {PrescriptionDetails_imageView.setVisibility(View.GONE);}
//
//                    customer_name.setText(body.getCustomersData().getName());
//                    customer_phone.setText(body.getCustomersData().getPhone());
//                    customer_address.setText(body.getCustomersData().getLocationAsAddress());

  //              } else {
                    //toast message
//                    Log.d(TAG,"message: No found orders");
//                    tv.setText("No Current Orders Yet");
//                    no_order.setVisibility(View.VISIBLE);
//                    order_time_tv.setVisibility(View.GONE);
//                    order_date_tv.setVisibility(View.GONE);
//                    PrescriptionDetails_tv.setVisibility(View.GONE);
//                    PrescriptionDetails_imageView.setVisibility(View.GONE);
//                    PrescriptionDetails.setVisibility(View.GONE);
//                    customer_name.setVisibility(View.GONE);
//                    customer_phone.setVisibility(View.GONE);
//                    customer_address.setVisibility(View.GONE);
             //   }

            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {

            }
        });
    }
//    public interface onOrderListener {
//        public void onClickItem(int position);
//    }

}
