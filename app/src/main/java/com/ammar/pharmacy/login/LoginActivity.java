package com.ammar.pharmacy.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ammar.pharmacy.MainActivity;
import com.ammar.pharmacy.R;
import com.ammar.pharmacy.register.RegisterFragment;
import com.ammar.pharmacy.retrofit.APIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ammar.pharmacy.retrofit.APIHelper.api;

public class LoginActivity extends MainActivity implements View.OnClickListener {

    private static final  String TAG="LoginActivity";
    LinearLayout firstLinear,secondLinear, thirdLinear;
    ImageView pharmacyLogo, emailIV, passwordIV;
    EditText emailAddress, password;
    Button loginBTN, RegisterBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firstLinear=findViewById(R.id.firstLinear);
        secondLinear=findViewById(R.id.secondLinear);
        thirdLinear=findViewById(R.id.thirdLinear);
        pharmacyLogo = findViewById(R.id.pharmacyLogo);
        emailIV =findViewById(R.id.emailIV);
        emailAddress = findViewById(R.id.emailAddress);
        passwordIV = findViewById(R.id.passwordIV);
        password = findViewById(R.id.password);
        loginBTN = findViewById(R.id.loginBTN);
        RegisterBTN = findViewById(R.id.registerBTN);

        loginBTN.setOnClickListener(this);
        RegisterBTN.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        LoginObject loginObject=new LoginObject ("asddfd","kdlsd");

        switch (v.getId()){
            case R.id.loginBTN:
                login(loginObject);

                Intent i =new Intent(this,MainActivity.class);
                startActivity(i);

                break;
            case R.id.registerBTN:
                loadFragment(new RegisterFragment());

                break;
        }

    }

    public void login(LoginObject loginobject) {
   new APIHelper();
   api.Login(loginobject).enqueue(new Callback<LoginReturnBody>() {
        @Override
        public void onResponse(Call<LoginReturnBody> call, Response<LoginReturnBody> response) {
            Log.d(TAG, "Request success  is " + response.message());
        }

        @Override
        public void onFailure(Call<LoginReturnBody> call, Throwable t) {

            Log.d(TAG, "Request failure is " + t);
        }
    });

    }





}