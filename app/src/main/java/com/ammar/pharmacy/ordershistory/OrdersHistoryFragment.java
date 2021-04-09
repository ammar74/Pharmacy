package com.ammar.pharmacy.ordershistory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.currentorder.Order;
import com.ammar.pharmacy.retrofit.APIHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.login.LoginFragment.token_key;
import static com.ammar.pharmacy.retrofit.APIHelper.api;


public class OrdersHistoryFragment extends Fragment {
    TextView tv;
    ImageView no_history;
    RecyclerView rv;
    List<Order> orders;
    private static final String TAG = "OrdersHistoryFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        String token = "aaabbb"+sharedPref.getString(token_key, null);
        Log.d(TAG,"the Coming token is "+token);
        rv=view.findViewById(R.id.ordersHistory_rv);
        tv=view.findViewById(R.id.orders_history_tv);
        no_history=view.findViewById(R.id.no_history);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        pharmacyOrderHistory(token);
    }

    public List<Order> pharmacyOrderHistory(String token) {
        new APIHelper();
        api.pharmacyOrderHistory(token).enqueue(new Callback<OrderHistoryResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
                OrderHistoryResponse orderHistoryResponse=response.body();
                Log.d(TAG,"ordersHistory message is "+response.body().message+
                        "    the orders is "+response.body().orders);
                if (orderHistoryResponse.orders!=null){
                    HistoryAdapter adapter=new HistoryAdapter(orderHistoryResponse.orders);
                    rv.setAdapter(adapter);
                }else {
                    orders=null;
                    Log.d(TAG,"message: Orders History is null");
                    Toast.makeText(getContext(),response.body().message,Toast.LENGTH_LONG);
                    tv.setText(response.body().message);
                    no_history.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<OrderHistoryResponse> call, Throwable t) {
                Log.d(TAG,  "message ",t);
            }
        });
        return orders;
    }
}