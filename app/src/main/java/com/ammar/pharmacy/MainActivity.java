package com.ammar.pharmacy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ammar.pharmacy.acceptedorders.AcceptedOrdersFragment;
import com.ammar.pharmacy.login.LoginFragment;
import com.ammar.pharmacy.currentorder.CurrentOrdersFragment;
import com.ammar.pharmacy.more.MoreFragment;
import com.ammar.pharmacy.news.NewsFragment;
import com.ammar.pharmacy.ordershistory.OrdersHistoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.ammar.pharmacy.login.LoginFragment.token_key;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String token;
    public static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        SharedPreferences sharedPref = this.getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
         token =sharedPref.getString(token_key, null);
        if (token == null){
            loadFragment(new LoginFragment());
            bottomNavigationView.setVisibility(View.GONE);
        } else {
            // load any other fragment
            bottomNavigationView.setVisibility(View.VISIBLE);
            loadFragment(new CurrentOrdersFragment());
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
                    case R.id.accepted_orders:
                        loadFragment(new AcceptedOrdersFragment());
                        return true;
                    case R.id.more:
                        loadFragment(new MoreFragment());
                        return true;

                }
                return false;
            };

//    @Override
//    public void onBackPressed() {
//        if(token==null){
//            finish();
//        }else
//        super.onBackPressed();
//    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    public void removeFragment(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        //this will clear the back stack and displays no animation on the screen
//        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//    }
//    public void logOut(){
//        SharedPreferences sharedPref = this.getSharedPreferences(
//                token_key, Context.MODE_PRIVATE);
//        sharedPref.edit().putString(token_key,null);
//        removeFragment();
//        bottomNavigationView.setVisibility(View.GONE);
//        loadFragment(new LoginFragment());
//    }
}