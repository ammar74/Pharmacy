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


}