package com.ammar.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ammar.pharmacy.register.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import static com.google.android.material.bottomnavigation.BottomNavigationView.*;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    BottomNavigationView bottomNavigationView;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent i=getIntent();


     //  loadFragment(new LoginFragment());



        // add fragment
        //loadFragment(new CurrentOrdersFragment());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBTN:

                break;
            case R.id.registerBTN:

                loadFragment(new RegisterFragment());

                break;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {

                switch (item.getItemId()) {
                    case R.id.current_orders:
                        loadFragment(new CurrentOrdersFragment());
                        return true;
                    case R.id.orders_history:
                        loadFragment(new OrdersHistoryFragment());
                        return true;
                    case R.id.news:
                        loadFragment(new NewsFragment());
                        return true;
                }
                return false;
            };

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}