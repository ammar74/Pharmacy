package com.ammar.pharmacy.more;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.register.RegisterObject;

public class EditFragment extends Fragment implements View.OnClickListener {


    TextView general_settings_TV,nameET,pharmacyActualName,phoneET,pharmacyActualPhone,PasswordET,
            pharmacyActualPassword,AddressET,pharmacyActualAddress,LocationET,pharmacyActualLocation;
    ImageButton editName, editPassword,editPhone,editAddress,editLocation;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }

    }}