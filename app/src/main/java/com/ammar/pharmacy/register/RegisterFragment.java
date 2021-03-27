package com.ammar.pharmacy.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.login.LoginObject;
import com.ammar.pharmacy.login.LoginReturnBody;
import com.ammar.pharmacy.retrofit.APIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    private static final  String TAG="RegisterFragment";

    TextView title, PH_info,password_info;
    EditText pharmacyNameET,pharmacyOwnerET,pharmacyEmailET,pharmacyAddressET,pharmacyLocationET,
            PH_passwordET,confirm_PH_passwordET;
    ImageButton PH_placeholder;
    LinearLayout locationLinear;
    Button pharmacyRegisterBTN2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_register, container, false);

        title =root.findViewById(R.id.title);
        PH_info =root.findViewById(R.id.PH_info);
        password_info =root.findViewById(R.id.password_info);
        pharmacyNameET =root.findViewById(R.id.pharmacyNameET);
        pharmacyOwnerET =root.findViewById(R.id.pharmacyOwnerET);
        pharmacyEmailET =root.findViewById(R.id.pharmacyEmailET);
        pharmacyAddressET =root.findViewById(R.id.pharmacyAddressET);
        pharmacyLocationET =root.findViewById(R.id.pharmacyLocationET);
        PH_passwordET =root.findViewById(R.id.PH_passwordET);
        confirm_PH_passwordET =root.findViewById(R.id.confirm_PH_passwordET);
        PH_placeholder =root.findViewById(R.id.PH_placeholder);
        locationLinear =root.findViewById(R.id.locationLinear);
        pharmacyRegisterBTN2 =root.findViewById(R.id.pharmacyRegisterBTN2);

        PH_placeholder.setOnClickListener(this);
        pharmacyRegisterBTN2.setOnClickListener(this);

        return root;

    }

    @Override
    public void onClick(View v) {
        RegisterObject registerObject=new RegisterObject("ammar","ammaryasser3016@gmail.com",
                "12345678","12345678","01026488962",
                "dfgh","254 251");

        switch (v.getId()) {

            case R.id.PH_placeholder:


                break;

            case R.id.pharmacyRegisterBTN2:
                register(registerObject);


                break;
        }

    }

    public void register(RegisterObject registerObject) {
        APIHelper.api.Register(registerObject).enqueue(new Callback<RegisterReturnBody>() {
            @Override
            public void onResponse(Call<RegisterReturnBody> call, Response<RegisterReturnBody> response) {
                Log.d(TAG, "Request failure is" + response.message());
            }

            @Override
            public void onFailure(Call<RegisterReturnBody> call, Throwable t) {

                Log.d(TAG, "Request failure is" + t);
            }
        });
    }

}