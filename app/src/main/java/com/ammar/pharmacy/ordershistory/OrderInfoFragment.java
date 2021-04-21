package com.ammar.pharmacy.ordershistory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.acceptedorders.OrderDetailsReturn;
import com.ammar.pharmacy.retrofit.APIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.retrofit.APIHelper.api;

public class OrderInfoFragment extends Fragment {

    private static final String TAG = "CurrentOrdersFragment";
    private TextView tv,PrescriptionDetails,PrescriptionDetails_tv,customer_name,
            customer_phone,customer_address ,order_time_tv,order_date_tv;
    private ImageView PrescriptionDetails_imageView,no_order;
    public OrderInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv=view.findViewById(R.id.active_orders_tv);
        PrescriptionDetails = view.findViewById(R.id.PrescriptionDetails);
        PrescriptionDetails_tv = view.findViewById(R.id.PrescriptionDetails_tv);
        customer_name = view.findViewById(R.id.customer_name);
        customer_phone = view.findViewById(R.id.customer_phone);
        customer_address = view.findViewById(R.id.customer_address);
        order_time_tv = view.findViewById(R.id.order_time_tv);
        order_date_tv = view.findViewById(R.id.order_date_tv);
        PrescriptionDetails_imageView = view.findViewById(R.id.PrescriptionDetails_imageView);
        no_order=view.findViewById(R.id.no_order);
       //  orderInfo();
    }

    public void orderInfo(String orderId){
        new APIHelper();
        api.orderInfo(orderId).enqueue(new Callback<OrderDetailsReturn>() {
            @Override
            public void onResponse(Call<OrderDetailsReturn> call, Response<OrderDetailsReturn> response) {
                OrderDetailsReturn body= response.body();
                String message= body.toString();
                Log.d(TAG," order info is  "+message);
                if (body.getOrder() != null){
                    Log.d(TAG,body.toString());
                    String orderId =body.getOrder().get_id();
                    Log.d(TAG,"onResponse: order id"+orderId);
                    String date = body.getOrder().getDate().substring(0,10);
                    String time = body.getOrder().getDate().substring(11,19);
                    order_time_tv.setText(time);
                    order_date_tv.setText(date);
                    if (body.getOrder().getOrderByTexting() !=null)
                        PrescriptionDetails_tv.setText(body.getOrder().getOrderByTexting());
                    else {PrescriptionDetails_tv.setVisibility(View.INVISIBLE);}
                    if(body.getOrder().getOrderByPhoto() != null) {
                        byte[] decodedString= Base64.decode(String.valueOf(body.getOrder().getOrderByPhoto()), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        PrescriptionDetails_imageView.setImageBitmap((decodedByte));
                        PrescriptionDetails_imageView.setVisibility(View.VISIBLE);
                    }else {PrescriptionDetails_imageView.setVisibility(View.GONE);}

                    customer_name.setText(body.getCustomersData().getName());
                    customer_phone.setText(body.getCustomersData().getPhone());
                    customer_address.setText(body.getCustomersData().getLocationAsAddress());

                } else {
                    //toast message
                    Log.d(TAG,"message: No found orders");
                    tv.setText("No Current Orders Yet");
                    no_order.setVisibility(View.VISIBLE);
                    order_time_tv.setVisibility(View.GONE);
                    order_date_tv.setVisibility(View.GONE);
                    PrescriptionDetails_tv.setVisibility(View.GONE);
                    PrescriptionDetails_imageView.setVisibility(View.GONE);
                    PrescriptionDetails.setVisibility(View.GONE);
                    customer_name.setVisibility(View.GONE);
                    customer_phone.setVisibility(View.GONE);
                    customer_address.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<OrderDetailsReturn> call, Throwable t) {

            }
        });
    }
}