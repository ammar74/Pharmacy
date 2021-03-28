package com.ammar.pharmacy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.ammar.pharmacy.login.LoginFragment.token_key;


public class CurrentOrdersFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        String token = sharedPref.getString(token_key,null);
        if (token!=null){
            // make a request to retrieve a list of orders and populate it in a recycler view
        }
    }
}