package com.example.jatingarg.section3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment{

    private Button submitBtn;
    private EditText email;
    private EditText password;
    private LoginFragmentChangeListener mListener;
    private static final String TAG = "LoginFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (LoginFragmentChangeListener)context;
        }catch (Exception e){
            Log.e(TAG, "onAttach: Exception Encountered " + e.getMessage());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        email = (EditText) getActivity().findViewById(R.id.email);
        password = (EditText) getActivity().findViewById(R.id.password);
        submitBtn = (Button) getActivity().findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if(emailText.length() != 0 && passwordText.length() != 0){
                    if(emailText.trim().equalsIgnoreCase(getResources().getString(R.string.correct_email)) && passwordText.trim().equalsIgnoreCase(getResources().getString(R.string.correct_password))){
                        mListener.loginSuccess();
                    }else{
                        mListener.loginFailure();
                    }
                }

            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    public interface LoginFragmentChangeListener{
        void loginSuccess();
        void loginFailure();
    }
}
