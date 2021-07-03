package com.ammar.pharmacy.register;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.login.LoginFragment;
import com.ammar.pharmacy.login.LoginObject;
import com.ammar.pharmacy.login.LoginReturnBody;
import com.ammar.pharmacy.retrofit.APIHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback  {

    private static final String TAG = "RegisterFragment";
    private static final String TAG1 = "MapsFragment";

    TextView title, PH_info, password_info;
    EditText pharmacyNameET, pharmacyPhonesET, pharmacyEmailET, pharmacyAddressET, pharmacyLocationET,
            PH_passwordET, confirm_PH_passwordET;

    Button pharmacyRegisterBTN2,getLocationBTN;
    Coordinates coordinates=null;

    FusedLocationProviderClient fusedLocationClient;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        bindViews(root);

        return root;

    }

    private void bindViews(View root) {
        title = root.findViewById(R.id.title);
        PH_info = root.findViewById(R.id.PH_info);
        password_info = root.findViewById(R.id.password_info);
        pharmacyNameET = root.findViewById(R.id.pharmacyNameET);
        pharmacyPhonesET = root.findViewById(R.id.pharmacyPhonesET);
        pharmacyEmailET = root.findViewById(R.id.pharmacyEmailET);
        pharmacyAddressET = root.findViewById(R.id.pharmacyAddressET);
        PH_passwordET = root.findViewById(R.id.PH_passwordET);
        confirm_PH_passwordET = root.findViewById(R.id.confirm_PH_passwordET);
        getLocationBTN = root.findViewById(R.id.getLocationBTN);
        pharmacyRegisterBTN2 = root.findViewById(R.id.pharmacyRegisterBTN2);
        getLocationBTN.setOnClickListener(this);
        pharmacyRegisterBTN2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.getLocationBTN:
                setupLocationClient();
                getCurrentLocation();
                break;

            case R.id.pharmacyRegisterBTN2:
                RegisterObject registerObject = null;
                if (coordinates == null) {
                    Toast.makeText(getContext(),
                            "Pick up your coordinates and fill all of the fields", Toast.LENGTH_LONG).show();
                } else {
                    registerObject = new RegisterObject(
                            pharmacyNameET.getText().toString(),
                            pharmacyEmailET.getText().toString(),
                            PH_passwordET.getText().toString(),
                            confirm_PH_passwordET.getText().toString(),
                            new String[]{pharmacyPhonesET.getText().toString()},
                            pharmacyAddressET.getText().toString(),
                            new LocationAsCoordinates(coordinates));
                }
                register(registerObject);
                break;
        }

    }

    public void register(RegisterObject registerObject) {
        new APIHelper();
        APIHelper.api.register(registerObject).enqueue(new Callback<RegisterReturnBody>() {
            @Override
            public void onResponse(Call<RegisterReturnBody> call, Response<RegisterReturnBody> response) {
                String message = response.body().getMessage();
                Log.d(TAG, "Request success: " + message);
                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                if (message.equals("success")){
                    loadFragment(new LoginFragment());
                }
            }

            @Override
            public void onFailure(Call<RegisterReturnBody> call, Throwable t) {
                Log.d(TAG, "Request failure: " + t);
            }
        });
    }
    public void loadFragment(Fragment fragment) {
        //load fragment
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GoogleMap map=googleMap;
        getCurrentLocation();
    }

    private void setupLocationClient() {
        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(getActivity());
    }

    private void requestLocationPermissions() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION},100);
    }

    private void getCurrentLocation() {
        // 1
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
        ) !=
                PackageManager.PERMISSION_GRANTED
        ) {
            // 2
            requestLocationPermissions();
            getCurrentLocation();

        } else {
            // 3
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();

                    if (location != null) {
                        // 4
                        coordinates = new Coordinates(location.getLatitude(), location.getLongitude());
                        Log.d(TAG,"coordinates"+coordinates);
                        Toast.makeText(getContext(), "location has been picked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Can't get your location,check that gps is enabled"
                                , Toast.LENGTH_LONG).show();
                        Log.e(TAG1, "No location found");
                        LocationRequest locationRequest = new LocationRequest().
                                setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).
                                setInterval(10000).setFastestInterval(1000).setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                                coordinates = new Coordinates(location.getLatitude(),
                                        location.getLongitude());

                            }
                        };
                    }
                }
            });
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==100 && (grantResults.length > 0) &&
                (grantResults[0]+grantResults[1] == PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }else{
            Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_LONG).show();
        }
    }
}