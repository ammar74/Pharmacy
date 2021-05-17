package com.ammar.pharmacy.more;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ammar.pharmacy.R;
import com.ammar.pharmacy.login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import static com.ammar.pharmacy.login.LoginFragment.token_key;


public class MoreFragment extends Fragment {
    RecyclerView more_rv;
    List<MoreInfoItem> moreInfoList=new ArrayList<>();
    public static final String TAG="MoreFragment";



    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        more_rv=view.findViewById(R.id.more_rv);
        moreInfoList.add(new MoreInfoItem(R.drawable.ic_baseline_settings_24,"General Settings"));
        moreInfoList.add(new MoreInfoItem(R.drawable.ic_baseline_contact_page_24,"About Us"));
        moreInfoList.add(new MoreInfoItem(R.drawable.ic_baseline_exit_to_app_24,"LogOut"));

        MoreAdapter moreAdapter=new MoreAdapter(moreInfoList,getContext());
        more_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        more_rv.setAdapter(moreAdapter);

        moreAdapter.setOnItemClick(new onItemClick() {
            @Override
            public void onClickItem(int position) {
                MoreInfoItem moreInfoItem = moreInfoList.get(position);
                if (moreInfoItem.name.equals("General Settings")) {
                    Log.d(TAG,"Coming Soon..");
                    //Toast.makeText(getContext(), "Coming Soon..", Toast.LENGTH_LONG).show();
                    loadFragment(new EditFragment());

                } else if (moreInfoItem.name.equals("About Us")) {
                    loadFragment(new AboutUsFragment());
                } else if (moreInfoItem.name.equals("LogOut")) {
                    Log.d(TAG,"when press LogOut");
                    logOut();
                }
            }
            });
    }


    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    public void removeFragment(){
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//    }
    public void logOut(){
        SharedPreferences sharedPref = getContext().getSharedPreferences(
                token_key, Context.MODE_PRIVATE);
        sharedPref.edit().putString(token_key,null).apply();
       // bottomNavigationView.setVisibility(View.GONE);
        loadFragment(new LoginFragment());
        Toast.makeText(getContext(),"You Logged Out,you can Login again",Toast.LENGTH_LONG).show();

    }

}