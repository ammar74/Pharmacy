package com.ammar.pharmacy.currentorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.retrofit.APIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.login.LoginFragment.token_key;
import static com.ammar.pharmacy.retrofit.APIHelper.api;


public class CurrentOrdersFragment extends Fragment {
    private static final String TAG = "CurrentOrdersFragment";
    private TextView tv,PrescriptionDetails,PrescriptionDetails_tv,customer_name,
            customer_phone,customer_address ,order_time_tv,order_date_tv;
    private ImageView PrescriptionDetails_imageView,no_order;
    String order_id;
    private Button pharmacyAcceptBTN,pharmacyRefuseBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_order, container, false);
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
        pharmacyAcceptBTN = view.findViewById(R.id.pharmacyAcceptBTN);
        pharmacyRefuseBTN = view.findViewById(R.id.pharmacyRefuseBTN);

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        String token = "aaabbb"+sharedPref.getString(token_key, null);
        Log.d(TAG,"the Coming token is "+token);
        if (!token.equals("aaabbb")) {
            // make a request to retrieve an order
            //    GetOrdersObject getOrdersObject=new GetOrdersObject("aaabbb"+token);
            getOrders(token);
        }else {
            Log.d(TAG, "Token is Null ");
            Toast.makeText(getContext(),"Failed to load Orders",Toast.LENGTH_LONG).show();
        }

        pharmacyAcceptBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"prior calling agree fn: order id"+order_id);
                PharmacyAgree(token,new IdWrapper(order_id));



            }
        });
        pharmacyRefuseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PharmacyNotAgree(token,new IdWrapper(order_id));
            }
        });

    }

    public void getOrders(String token) {
        new APIHelper();
        api.getOrders(token).enqueue(new Callback<GetOrdersReturnBody>() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onResponse(Call<GetOrdersReturnBody> call, Response<GetOrdersReturnBody> response) {
                Log.d(TAG, "Get orders success  " + response.body());
                GetOrdersReturnBody body = response.body();

                if (body.order!= null){
                    order_id =body.order._id;
                    Log.d(TAG,"onResponse: order id"+order_id);
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
                    }else {PrescriptionDetails_imageView.setVisibility(View.GONE);}

                    customer_name.setText(body.customersData.name);
                    customer_phone.setText(body.customersData.phone);
                    customer_address.setText(body.customersData.locationAsAddress);

                } else {
                    //toast message
                    Log.d(TAG,"message: No found orders");
                    Toast.makeText(getContext(),response.body().message,Toast.LENGTH_LONG).show();
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
                    pharmacyAcceptBTN.setVisibility(View.GONE);
                    pharmacyRefuseBTN.setVisibility(View.GONE);

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

    public void PharmacyAgree(String token,IdWrapper idWrapper){
        new APIHelper();
        api.PharmacyAgree(token,idWrapper).enqueue(new Callback<PharmacyRespond>() {
            @Override
            public void onResponse(Call<PharmacyRespond> call, Response<PharmacyRespond> response) {
                Log.d(TAG,"in agree: order id is"+order_id);
                Log.d(TAG, "Pharmacy Agree success " + response.body());
                if (response.body()!=null)
                {
                    if (response.body().getMsg()=="success")
                        Toast.makeText(getContext(),"Order Accepted",Toast.LENGTH_LONG).show();
                        loadFragment(new CurrentOrdersFragment());
                }
            }

            @Override
            public void onFailure(Call<PharmacyRespond> call, Throwable t) {
                Log.d(TAG, "Pharmacy Agree fail " +t);

            }
        });
    }


    public void PharmacyNotAgree(String token,IdWrapper idWrapper){
        new APIHelper();
        api.PharmacyNotAgree(token,idWrapper).enqueue(new Callback<PharmacyRespond>() {
            @Override
            public void onResponse(Call<PharmacyRespond> call, Response<PharmacyRespond> response) {
                Log.d(TAG,"in refuse: order id is"+order_id);
                Log.d(TAG, "Pharmacy not Agree success " + response.body());
                if (response.body()!=null)
                {
                    if (response.body().getMsg()=="success")
                        Toast.makeText(getContext(),"Order Denied",Toast.LENGTH_LONG).show();
                        loadFragment(new CurrentOrdersFragment());
                }


            }

            @Override
            public void onFailure(Call<PharmacyRespond> call, Throwable t) {

            }
        });
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}