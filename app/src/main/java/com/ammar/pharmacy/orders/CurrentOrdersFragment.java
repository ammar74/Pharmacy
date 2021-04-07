package com.ammar.pharmacy.orders;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.pharmacy.MainActivity;
import com.ammar.pharmacy.R;
import com.ammar.pharmacy.login.LoginObject;
import com.ammar.pharmacy.login.LoginReturnBody;
import com.ammar.pharmacy.retrofit.APIHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

import static com.ammar.pharmacy.login.LoginFragment.token_key;
import static com.ammar.pharmacy.retrofit.APIHelper.api;


public class CurrentOrdersFragment extends Fragment {
    private static final String TAG = "CurrentOrdersFragment";
    private TextView tv,PrescriptionDetails,PrescriptionDetails_tv,customer_name,
            customer_phone,customer_address ,order_time_tv,order_date_tv;
    private ImageView PrescriptionDetails_imageView;
    private Button pharmacyAcceptBTN,pharmacyRefuseBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specific_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PrescriptionDetails = view.findViewById(R.id.PrescriptionDetails);
        PrescriptionDetails_tv = view.findViewById(R.id.PrescriptionDetails_tv);
        customer_name = view.findViewById(R.id.customer_name);
        customer_phone = view.findViewById(R.id.customer_phone);
        customer_address = view.findViewById(R.id.customer_address);
        order_time_tv = view.findViewById(R.id.order_time_tv);
        order_date_tv = view.findViewById(R.id.order_date_tv);
        PrescriptionDetails_imageView = view.findViewById(R.id.PrescriptionDetails_imageView);
        pharmacyAcceptBTN = view.findViewById(R.id.pharmacyAcceptBTN);
        pharmacyRefuseBTN = view.findViewById(R.id.pharmacyRefuseBTN);

        pharmacyAcceptBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pharmacyRefuseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        String token = "aaabbb"+sharedPref.getString(token_key, null);
        Log.d(TAG,"the Coming token is "+token);
        if (token != null) {
            // make a request to retrieve an order
        //    GetOrdersObject getOrdersObject=new GetOrdersObject("aaabbb"+token);
            getOrders(token);
        }else {
            Log.d(TAG, "Token is Null ");
            Toast.makeText(getContext(),"Failed to load Orders",Toast.LENGTH_LONG);
        }
    }

    public void getOrders(String token) {
        new APIHelper();
        api.getOrders(token).enqueue(new Callback<GetOrdersReturnBody>() {
            @Override
            public void onResponse(Call<GetOrdersReturnBody> call, Response<GetOrdersReturnBody> response) {
                Log.d(TAG, "Get orders success  " + response.body().order.toString());
                GetOrdersReturnBody body = response.body();
                if (body.order!= null){
                    String date = body.order.date.substring(0,10);
                    String time = body.order.date.substring(11,19);
                    order_time_tv.setText(time);
                    order_date_tv.setText(date);
                    if (body.order.orderByTexting !=null)
                    PrescriptionDetails_tv.setText(body.order.orderByTexting);
                    else {PrescriptionDetails_tv.setVisibility(View.INVISIBLE);}
                    if(body.order.orderByPhoto != null) {
                         byte[] decodedString=Base64.decode(String.valueOf(body.order.orderByPhoto), Base64.DEFAULT);
                         Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                         PrescriptionDetails_imageView.setImageBitmap((decodedByte));
                         PrescriptionDetails_imageView.setVisibility(View.VISIBLE);
                    }else {PrescriptionDetails_imageView.setVisibility(View.INVISIBLE);}

                    customer_name.setText(body.customerData.name);
                    customer_phone.setText(body.customerData.phone);
                    customer_address.setText(body.customerData.locationAsAddress);

                } else {
                    //toast message
                }

//                Handler handler = new Handler(Looper.getMainLooper());
//               handler.post(new Runnable() {
//                   @Override
//                   public void run() {
//                       Toast.makeText(getActivity(), response.body().message, Toast.LENGTH_SHORT).show();
//
//                   }
//               });
            }


            @Override
            public void onFailure(Call<GetOrdersReturnBody> call, Throwable t) {
                Log.d(TAG, "Get Orders failed ", t);
            }


        });

    }

}