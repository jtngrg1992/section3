package com.example.jatingarg.section3;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jatingarg on 25/04/17.
 */

public class IndicatorFragment extends Fragment implements LoaderManager.LoaderCallbacks<IndicatorMessage> {
    public boolean isSuccessfull = false;
    private ImageView mImageView;
    private TextView mTextView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageView = (ImageView) getActivity().findViewById(R.id.indicatorImage);
        mTextView = (TextView) getActivity().findViewById(R.id.indicatorMessage);


        getLoaderManager().initLoader(0,null,this).forceLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_indicator,container,false);
    }

    @Override
    public Loader<IndicatorMessage> onCreateLoader(int id, Bundle args) {
        return new Messageloader(isSuccessfull,getActivity());
    }

    @Override
    public void onLoadFinished(Loader<IndicatorMessage> loader, IndicatorMessage data) {
        mImageView.setImageResource(data.getImageResource());
        mTextView.setText(data.getMessageText());
    }

    @Override
    public void onLoaderReset(Loader<IndicatorMessage> loader) {

    }
}
