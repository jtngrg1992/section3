package com.example.jatingarg.section3;

import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentChangeListener {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment fragment = new LoginFragment();
        fragmentTransaction.add(R.id.relativeLayout,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void loginSuccess() {
        //insert another fragment to indicate success
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        IndicatorFragment fragment = new IndicatorFragment();
        fragment.isSuccessfull = true;
        fragmentTransaction.replace(R.id.relativeLayout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void loginFailure() {
        //insert anoter fragment to indicate failure
        //insert another fragment to indicate success
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        IndicatorFragment fragment = new IndicatorFragment();
        fragment.isSuccessfull = false;
        fragmentTransaction.replace(R.id.relativeLayout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
