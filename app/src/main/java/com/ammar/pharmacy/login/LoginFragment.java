package com.ammar.pharmacy.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ammar.pharmacy.CurrentOrdersFragment;
import com.ammar.pharmacy.MainActivity;
import com.ammar.pharmacy.R;
import com.ammar.pharmacy.register.RegisterFragment;
import com.ammar.pharmacy.retrofit.APIHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.retrofit.APIHelper.api;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    public static final String token_key = "100001" ;
    private static final String TAG = "LoginFragment";
    LinearLayout firstLinear, secondLinear, thirdLinear;
    ImageView pharmacyLogo, emailIV, passwordIV;
    EditText emailAddress, password;
    Button loginBTN, registerBTN;
    BottomNavigationView bottomNavigationView;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginObject loginObject = new LoginObject(emailAddress.getText().toString(),
                        password.getText().toString());
                login(loginObject);
            }
        });
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new RegisterFragment());
            }
        });
    }

    private void bindViews(View v) {
        firstLinear = v.findViewById(R.id.firstLinear);
        secondLinear = v.findViewById(R.id.secondLinear);
        thirdLinear = v.findViewById(R.id.thirdLinear);
        pharmacyLogo = v.findViewById(R.id.pharmacyLogo);
        emailIV = v.findViewById(R.id.emailIV);
        emailAddress = v.findViewById(R.id.emailAddress);
        passwordIV = v.findViewById(R.id.passwordIV);
        password = v.findViewById(R.id.password);
        loginBTN = v.findViewById(R.id.loginBTN);
        registerBTN = v.findViewById(R.id.registerBTN);
    }
    public void login(LoginObject loginobject) {
        new APIHelper();
        api.Login(loginobject).enqueue(new Callback<LoginReturnBody>() {
            @Override
            public void onResponse(Call<LoginReturnBody> call, Response<LoginReturnBody> response) {
                assert response.body() != null;
                Log.d(TAG, "Request success  is " + response.body().getMessage());
                LoginReturnBody body = response.body();
                String message = body.getMessage();
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                if (message.equals("success")){
                    //save token in shared prefs
                    getActivity();
                    SharedPreferences sharedPref = getActivity().getSharedPreferences(
                            token_key, Context.MODE_PRIVATE);
                    sharedPref.edit().putString(token_key,body.getToken());
                    loadFragment(new CurrentOrdersFragment());
                }
            }

            @Override
            public void onFailure(Call<LoginReturnBody> call, Throwable t) {

                Log.d(TAG, "Request failure is " + t);
            }
        });

    }
    public void loadFragment(Fragment fragment) {
        // load fragment
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}