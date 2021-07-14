package com.ammar.pharmacy.ordershistory;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.pharmacy.MainActivity;
import com.ammar.pharmacy.R;
import com.ammar.pharmacy.acceptedorders.OrderDetailsReturn;
import com.ammar.pharmacy.retrofit.APIHelper;

import java.io.ByteArrayOutputStream;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.login.LoginFragment.token_key;
import static com.ammar.pharmacy.retrofit.APIHelper.api;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {


    List<PharmacyOrders> pharmacyOrders;
    Context context;
    Dialog dialog;
    public static final String TAG="HistoryAdapter";
    TextView PrescriptionDetails_tv;
    TextView customer_name;
    TextView customer_phone;
    TextView customer_address;
    TextView order_date;
    TextView order_time,orders_history_tv;
    ImageView  medicine_IV,no_history;

    SharedPreferences sharedPref;
    String token;
    String manipulatedToken;

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
        sharedPref = context.getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        token = sharedPref.getString(token_key, null);
        manipulatedToken = "aaabbb" + token;

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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                PrescriptionDetails_tv = dialog.findViewById(R.id.PrescriptionDetails_tv);

                customer_name = dialog.findViewById(R.id.customer_name);

                customer_phone = dialog.findViewById(R.id.customer_phone);

                customer_address = dialog.findViewById(R.id.customer_address);

                order_date = dialog.findViewById(R.id.order_date_tv);

                order_time = dialog.findViewById(R.id.order_time_tv);

                medicine_IV = dialog.findViewById(R.id.medicine_IV);


//                PrescriptionDetails_tv.setText(pharmacyOrdersItem.orderByTexting);
//                medicine_IV.setImageBitmap(convertToBitmap(pharmacyOrdersItem.orderByPhoto));
//                customer_name.setText("Mohamed");
//                customer_phone.setText("01115456789");
//                String date = pharmacyOrders.get(holder.getAdapterPosition()).date.substring(0,10);
//                String time = pharmacyOrders.get(holder.getAdapterPosition()).date.substring(11,19);
//                order_date.setText(date);
//                order_time.setText(time);
             orderInfo(manipulatedToken,pharmacyOrdersItem._id);
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

    public void orderInfo(String token,String orderId){
        new APIHelper();
        api.orderInfo(token,orderId).enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                OrderDetailsResponse body= response.body();
               String message= body.toString();
                Log.d(TAG," order info is  "+body);
               if (body.getOrderData() != null){
                    Log.d(TAG,body.toString());
                    String orderId =body.getOrderData().get_id();
                    Log.d(TAG,"onResponse: order id"+orderId);
                    String date = body.getOrderData().getDate().substring(0,10);
                    String time = body.getOrderData().getDate().substring(11,19);
                    order_time.setText(time);
                    order_date.setText(date);
                    Log.d(TAG,"order by text"+body.getOrderData().getOrderByTexting());
                    if (body.getOrderData().getOrderByTexting() !=null)
                        PrescriptionDetails_tv.setText(body.getOrderData().getOrderByTexting());
                    else {PrescriptionDetails_tv.setVisibility(View.INVISIBLE);}
                    if(body.getOrderData().getOrderByPhoto() != null) {
                        String photo= body.getOrderData().getOrderByPhoto();
                        if (photo.contains("data:image/jpeg;base64")) {
                            photo =removePrefix(photo,"data:image/jpeg;base64");
                        } else if (photo.contains("data:image/png;base64")) {
                            photo = removePrefix(photo,"data:image/png;base64");
                        }
                        Log.d(TAG, "photo is ${body.orderData.orderByPhoto}");
                        byte[] decodedString= Base64.decode(String.valueOf(photo), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        medicine_IV.setImageBitmap((decodedByte));
                        medicine_IV.setVisibility(View.VISIBLE);
                    }else {medicine_IV.setVisibility(View.GONE);}

                    customer_name.setText(body.getCustomersData().getName());
                    customer_phone.setText(body.getCustomersData().getPhone());
                    customer_address.setText(body.getCustomersData().getLocationAsAddress());

                } else {
                    //toast message
                    Log.d(TAG,"message: No found orders");
                    orders_history_tv.setText("No Current Orders Yet");
                    no_history.setVisibility(View.VISIBLE);
                    order_time.setVisibility(View.GONE);
                    order_date.setVisibility(View.GONE);
                    PrescriptionDetails_tv.setVisibility(View.GONE);
                    medicine_IV.setVisibility(View.GONE);
                    medicine_IV.setVisibility(View.GONE);
                    customer_name.setVisibility(View.GONE);
                    customer_phone.setVisibility(View.GONE);
                    customer_address.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {

            }
        });
    }
    public static String removePrefix(String s, String prefix)
    {
        if (s != null && prefix != null && s.startsWith(prefix)) {
            return s.substring(prefix.length());
        }
        return s;
    }
//    public interface onOrderListener {
//        public void onClickItem(int position);
//    }

//   @RequiresApi(api = Build.VERSION_CODES.O)
//    private Bitmap convertToBitmap(String image) {
//        byte[] decodedString= Base64.getMimeDecoder().decode(image);
//        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//    }
//    private String getBase64String(Bitmap bitmap){
//        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] byteArray= byteArrayOutputStream.toByteArray();
//        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);
//    }

}
