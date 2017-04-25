package com.example.jatingarg.section3;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by jatingarg on 25/04/17.
 */

public class Messageloader extends AsyncTaskLoader<IndicatorMessage>{
    private static final String TAG = "Messageloader";

    private boolean isSucessfull;
    private Context mContext;
    private IndicatorMessage mMessage;

    private static final String SUCESSS_MESSAGE = "Login Successful!";
    private static final String FAILURE_MESSAGE = "Login Failure!";
    private static final int SUCCESS_ICON = R.drawable.success_icon;
    private static final int FAILURE_ICON = R.drawable.failure_icon;
    
    
    public Messageloader(boolean isSucessfull, Context mContext) {
        super(mContext);
        this.isSucessfull = isSucessfull;
        this.mContext = mContext;
        Log.d(TAG, "Messageloader: inside");
    }

    @Override
    public IndicatorMessage loadInBackground() {

        if(isSucessfull){
            mMessage = new IndicatorMessage(SUCESSS_MESSAGE,SUCCESS_ICON);
        }else{
            mMessage = new IndicatorMessage(FAILURE_MESSAGE,FAILURE_ICON);
        }
        Log.d(TAG, "loadInBackground: " + mMessage);
        return  mMessage;
    }


    @Override
    public void deliverResult(IndicatorMessage data) {
        Log.d(TAG, "deliverResult: inside");
        if(isReset()){
            return;
        }
        IndicatorMessage oldMessage = mMessage;
        mMessage = data;

        if(isStarted()){
            super.deliverResult(data);
        }

    }


    @Override
    protected void onStartLoading() {
        Log.d(TAG, "onStartLoading: starts");
        if(mMessage != null){
            Log.d(TAG, "onStartLoading: delivered");
            deliverResult(mMessage);
        }
    }
}
