package com.ammar.pharmacy.more;

import static com.ammar.pharmacy.login.LoginFragment.token_key;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.currentorder.IdWrapper;
import com.ammar.pharmacy.currentorder.PharmacyRespond;
import com.ammar.pharmacy.register.Coordinates;
import com.ammar.pharmacy.retrofit.APIHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFragment extends Fragment implements OnMapReadyCallback {
    private static final String TAG = "EditFragment";

  private String manipulatedToken ;

    TextView tv_email ,tv_name ,tv_address ,tv_coordinates ,tv_age ,tv_phone;
    ImageView iv_image ,iv_editProfilePic,iv_editName,iv_editPhone,iv_editCoordinates,iv_editAddress
                ,iv_editPassword;

    Coordinates coordinates = null;
    FusedLocationProviderClient fusedLocationClient;
    private String editString = "";
    private String passwordString = "";
    private String confirmPasswordString = "";
    View v = null;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String photo;
    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_edit, container, false);
        initViews(root);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        String token =sharedPref.getString(token_key, null);
        manipulatedToken="aaabbb"+token;

        Log.d(TAG,"the Coming token is "+token);

        //convert token
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload =new String(decoder.decode(chunks[1]));
        PharmacyProfile profile =  new Gson().fromJson(payload,PharmacyProfile.class);

        //Assign Data
        Log.d(TAG, "profile object:"+ profile);
        assignData(profile);

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void assignData(PharmacyProfile profile){
        tv_name.setText(profile.name);
        tv_email.setText(profile.email);
        tv_phone.setText(Arrays.toString(profile.getPhones()));
        tv_address.setText(profile.locationAsAddress);
        Log.d(TAG, "coordinates: "+profile.locationAsCoordinates);
        tv_coordinates.setText((profile.locationAsCoordinates.getCoordinates().getLat()
                +" \n"+profile.locationAsCoordinates.getCoordinates().getLon())+"");
        if (!(photo ==null)) {
            iv_image.setImageBitmap(convertToBitmap(photo));
        }
    }

    private void initViews(View root){
        tv_name=root.findViewById(R.id.tv_name);
        tv_email=root.findViewById(R.id.tv_email);
        tv_age=root.findViewById(R.id.tv_age);
        tv_address=root.findViewById(R.id.tv_address);
        tv_coordinates=root.findViewById(R.id.tv_coordinates);
        tv_phone=root.findViewById(R.id.tv_phone);

        iv_editProfilePic=root.findViewById(R.id.iv_editProfilePic);
        iv_editName=root.findViewById(R.id.iv_editName);
        iv_editPhone=root.findViewById(R.id.iv_editPhone);
        iv_editCoordinates=root.findViewById(R.id.iv_editCoordinates);
        iv_editAddress=root.findViewById(R.id.iv_editAddress);
        iv_editPassword=root.findViewById(R.id.iv_editPassword);

        iv_editProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameDialog();
            }
        });
        iv_editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addressDialog();
            }
        });
        iv_editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            phoneDialog();
            }
        });
        iv_editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordDialog();

            }
        });
        iv_editCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupLocationClient();
                getCurrentLocation();
                editCoordinates(manipulatedToken,new EditCoordinatesObject(coordinates));
            }
        });
    }



    public void nameDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.edit_name_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogView.<EditText>findViewById(R.id.newNameET).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                editString=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
               // newNameET.getText().toString();
                Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT).show();
                editName(manipulatedToken,new EditNameObject(editString));
                tv_name.setText(editString);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                Toast.makeText(getContext(),"Canceled",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void editName(String token,EditNameObject editNameObject){
        APIHelper.api.editName(token, editNameObject).enqueue(new Callback<PharmacyRespond>() {
            @Override
            public void onResponse(Call<PharmacyRespond> call, Response<PharmacyRespond> response) {
                Toast.makeText(getContext(),"edit name response"+response.body().getMsg(),
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG,"response Success"+response.body().getMsg());
            }

            @Override
            public void onFailure(Call<PharmacyRespond> call, Throwable t) {
                Log.d(TAG,"response Failed"+t);

            }
        });
    }

    public void addressDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.edit_address_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogView.<EditText>findViewById(R.id.newAddressET).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editString=s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                editAddress(manipulatedToken,new EditAddressObject(editString));
                tv_address.setText(editString);
                Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                Toast.makeText(getContext(),"Canceled",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void editAddress(String token,EditAddressObject editAddressObject) {
        new APIHelper();
        APIHelper.api.editAddress(token,editAddressObject).enqueue(new Callback<PharmacyRespond>() {
            @Override
            public void onResponse(Call<PharmacyRespond> call, Response<PharmacyRespond> response) {
                Toast.makeText(getContext(),"edit Address response"+response.body().getMsg(),
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG,"edit Address response Success"+response.body().getMsg());
            }

            @Override
            public void onFailure(Call<PharmacyRespond> call, Throwable t) {
                Log.d(TAG,"edit Address response Failed"+t);

            }
        });
    }

    public void phoneDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.edit_phone_dialog, null);
        dialogBuilder.setView(dialogView);
        ArrayList<String> phones=new ArrayList<String>();
        dialogView.<EditText>findViewById(R.id.newAddressET).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editString= s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT).show();
                phones.remove(phones);
                phones.add(editString);
                editPhone(manipulatedToken,new EditPhonesObject((phones)));
                tv_phone.setText(editString);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                Toast.makeText(getContext(),"Canceled",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void editPhone(String token,EditPhonesObject editPhonesObject){
        APIHelper.api.editPhone(token,editPhonesObject).enqueue(new Callback<PharmacyRespond>() {
            @Override
            public void onResponse(Call<PharmacyRespond> call, Response<PharmacyRespond> response) {
                Log.d(TAG,"Edit phone response Success:"+response.body().getMsg());
            }

            @Override
            public void onFailure(Call<PharmacyRespond> call, Throwable t) {
                Log.d(TAG,"Edit phone response Failed:"+t);


            }
        });

    }

    private void editCoordinates(String token,EditCoordinatesObject editCoordinatesObject){
        APIHelper.api.editCoordinates(token,editCoordinatesObject).enqueue(new Callback<PharmacyRespond>() {
            @Override
            public void onResponse(Call<PharmacyRespond> call, Response<PharmacyRespond> response) {
                Log.d(TAG,"edit coordinates response Success :"+response.body().getMsg());
            }

            @Override
            public void onFailure(Call<PharmacyRespond> call, Throwable t) {
                Log.d(TAG,"edit coordinates response Failed :"+t);
            }
        });
    }


    public void passwordDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.edit_password_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                Toast.makeText(getContext(),"Canceled",Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void editPassword(String token,EditPasswordObject editPasswordObject){
        APIHelper.api.editPassword(token,editPasswordObject).enqueue(new Callback<PharmacyRespond>() {
            @Override
            public void onResponse(Call<PharmacyRespond> call, Response<PharmacyRespond> response) {
            Log.d(TAG,"edit password respond Success :"+response.body().getMsg());
            }

            @Override
            public void onFailure(Call<PharmacyRespond> call, Throwable t) {
                Log.d(TAG,"edit password respond Failed :"+t);

            }
        });
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
                        Toast.makeText(getContext(), "location has been picked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Can't get your location,check that gps is enabled"
                                , Toast.LENGTH_LONG).show();
                        Log.e(TAG, "No location found");
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==100 && (grantResults.length > 0) &&
                (grantResults[0]+grantResults[1] == PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }else{
            Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_LONG).show();
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private Bitmap convertToBitmap(String image) {
        byte[] decodedString= Base64.getMimeDecoder().decode(image);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }

    private String getBase64String(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray= byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);
        }
}