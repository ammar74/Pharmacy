
package com.ammar.pharmacy.acceptedorders;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.currentorder.DoneOrderResponse;
import com.ammar.pharmacy.more.onItemClick;
import com.ammar.pharmacy.retrofit.APIHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.login.LoginFragment.token_key;
import static com.ammar.pharmacy.retrofit.APIHelper.api;

public class AcceptedOrdersAdapter extends RecyclerView.Adapter<AcceptedOrdersAdapter.ViewHolder> {

    List<OrderDetailsReturn> pharmacyOrders;
    LayoutInflater layoutInflater;
    Context context;
//    onItemClick onItemClick;
    String token = "aaabbb";
    public static final String TAG="AcceptedOrdersAdapter";

    public AcceptedOrdersAdapter(Context context,List<OrderDetailsReturn> pharmacyOrders) {
        this.pharmacyOrders = pharmacyOrders;
        layoutInflater=LayoutInflater.from(context);
        this.context=context;
        notifyDataSetChanged();
        SharedPreferences sharedPref = context.getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        token = token+ sharedPref.getString(token_key, null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.accepted_order_item,parent,false);
        return new ViewHolder(root);
    }

//    public void setOnItemClick(onItemClick onItemClick){
//        this.onItemClick=onItemClick;
//    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetailsReturn pharmacyOrdersItem=pharmacyOrders.get(position);
        String date = pharmacyOrdersItem.orderdata.getDate().substring(0,10);
        String time = pharmacyOrdersItem.orderdata.getDate().substring(11,19);
        holder.date_tv.setText(date);
        holder.time_tv.setText(time);
        if (pharmacyOrdersItem.orderdata.getOrderByTexting() !=null)
        {
        holder.medicine_tv.setText(pharmacyOrdersItem.orderdata.getOrderByTexting());
        }else {
            byte[] decodedString= Base64.decode(String.valueOf(pharmacyOrdersItem.orderdata.getOrderByPhoto()), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.medicine_IV.setImageBitmap((decodedByte));
            holder.medicine_IV.setVisibility(View.VISIBLE);
            holder.medicine_tv.setVisibility(View.GONE);
        }
        holder.order_status_tv.setText(pharmacyOrdersItem.orderdata.getGlobalStatus());
        holder.acceptBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (!token.equals("aaabbb"))
                doneOrder(token,new StatusIDWrapper(pharmacyOrdersItem.orderdata.get_id()));
              Log.d(TAG,"token = "+token);
              Log.d(TAG,"id = "+pharmacyOrdersItem.orderdata.get_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, String.valueOf(pharmacyOrders));
        return pharmacyOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_tv,time_tv,medicine_tv,order_status_tv;
        ImageButton acceptBTN;
        ImageView medicine_IV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_tv=itemView.findViewById(R.id.date_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            medicine_tv=itemView.findViewById(R.id.medicine_tv);
            order_status_tv=itemView.findViewById(R.id.order_status_tv);
            medicine_IV=itemView.findViewById(R.id.medicine_IV);
            acceptBTN=itemView.findViewById(R.id.acceptBTN);
//
//            acceptBTN.setOnClickListener(v -> {
//                if (onItemClick!=null)
//                    onItemClick.onClickItem(getAdapterPosition());
////                        pharmacyOrders.remove(getAdapterPosition());
////                        notifyItemRemoved(getAdapterPosition());
////                        notifyItemRangeChanged(getAdapterPosition(), pharmacyOrders.size());
//            });

        }
    }
    public void doneOrder(String token,StatusIDWrapper orderId){
        new APIHelper();
        api.doneOrder(token,orderId).enqueue(new Callback<DoneOrderResponse>() {
            @Override
            public void onResponse(@Nullable Call<DoneOrderResponse> call, Response<DoneOrderResponse> response) {
                DoneOrderResponse doneOrderResponse=response.body();
                Log.d(TAG,"Done Order Success is "+doneOrderResponse);
//                Toast.makeText(context,doneOrderResponse.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DoneOrderResponse> call, Throwable t) {
                Log.d(TAG,"Done Order Failed is ",t);
            }
        });
    }


}
