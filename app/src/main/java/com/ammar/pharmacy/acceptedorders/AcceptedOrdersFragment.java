package com.ammar.pharmacy.acceptedorders;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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


public class AcceptedOrdersFragment extends Fragment {
    TextView tv;
    ImageView no_accepted_orders_IV;
    RecyclerView rv;
    ImageButton acceptBTN;
    ProgressBar progressBar;
    List<OrderDetailsReturn> pharmacyOrders;
    public static final String TAG="AcceptedOrdersFragment";

    public AcceptedOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accepted_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        String token = "aaabbb"+sharedPref.getString(token_key, null);
        Log.d(TAG,"the Coming token is "+token);
        rv=view.findViewById(R.id.accepted_orders_rv);
        tv=view.findViewById(R.id.accepted_orders_TV);
        no_accepted_orders_IV=view.findViewById(R.id.no_accepted_orders_IV);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        acceptedOrders(token);

    }

    public void acceptedOrders(String token) {
        new APIHelper();
        api.acceptedOrders(token).enqueue(new Callback<AcceptedOrdersReturn>() {
            @Override
            public void onResponse(Call<AcceptedOrdersReturn> call, Response<AcceptedOrdersReturn> response) {
                AcceptedOrdersReturn acceptedOrdersReturn=response.body();

                Log.d(TAG,"Accepted Orders message is "+ acceptedOrdersReturn.getMessage() );
                     //   " Accepted Orders are "+ acceptedOrdersReturn.getPharmacyOrders().toString());
                if (acceptedOrdersReturn.getPharmacyOrders()!=null){
                    AcceptedOrdersAdapter adapter=new AcceptedOrdersAdapter(getContext(),acceptedOrdersReturn.getPharmacyOrders());
                    rv.setAdapter(adapter);
                }else {
                    pharmacyOrders=null;
                    Log.d(TAG,"message: Accepted Orders is null");
                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    tv.setText("No Accepted Orders Yet");
                    no_accepted_orders_IV.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<AcceptedOrdersReturn> call, Throwable t) {
                Log.d(TAG,  "message ",t);
            }
        });
        }

}