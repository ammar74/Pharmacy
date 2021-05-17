package com.ammar.pharmacy.more;

import android.app.AlertDialog;
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

public class EditFragment extends Fragment implements View.OnClickListener {

    LinearLayout Linear1,Linear2,Linear3,Linear4,Linear5;
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
        bindViews(view);
    }

    private void bindViews(View v){
        Linear1=v.findViewById(R.id.Linear1);
        Linear2=v.findViewById(R.id.Linear2);
        Linear3=v.findViewById(R.id.Linear3);
        Linear4=v.findViewById(R.id.Linear4);
        Linear5=v.findViewById(R.id.Linear5);
        general_settings_TV=v.findViewById(R.id.general_settings_TV);
        nameET=v.findViewById(R.id.nameET);
        pharmacyActualName=v.findViewById(R.id.pharmacyActualName);
        phoneET=v.findViewById(R.id.phoneET);
        pharmacyActualPhone=v.findViewById(R.id.pharmacyActualPhone);
        PasswordET=v.findViewById(R.id.PasswordET);
        pharmacyActualPassword=v.findViewById(R.id.general_settings_TV);
        AddressET=v.findViewById(R.id.AddressET);
        pharmacyActualAddress=v.findViewById(R.id.pharmacyActualAddress);
        LocationET=v.findViewById(R.id.LocationET);
        pharmacyActualLocation=v.findViewById(R.id.pharmacyActualLocation);

        editName=v.findViewById(R.id.editName);
        editPhone=v.findViewById(R.id.editPhone);
        editPassword=v.findViewById(R.id.editPassword);
        editAddress=v.findViewById(R.id.editAddress);
        editLocation=v.findViewById(R.id.editLocation);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editName:
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View mView=getLayoutInflater().inflate(R.layout.edit_name_dialog,null);
                final EditText newNameET=mView.findViewById(R.id.newNameET);
                final Button saveNewNameBTN=mView.findViewById(R.id.saveNewNameBTN);
                saveNewNameBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!newNameET.getText().toString().isEmpty()){

                        }else  Toast.makeText(getContext(),"please Enter Your New Name",Toast.LENGTH_LONG);
                    }
                });
                builder.setView(mView);
                AlertDialog dialog=builder.create();
                dialog.show();

                break;
            case R.id.editPhone:

                break;
            case R.id.editPassword:

                break;
            case R.id.editAddress:

                break;
            case R.id.editLocation:

                break;
        }
    }

    private void editNameDialog(){
    }
}