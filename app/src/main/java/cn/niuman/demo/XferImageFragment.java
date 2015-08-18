/*
 * Copyright (c) 1997-2015 Niuman. All rights reserved.
 */

package cn.niuman.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.NumberFormat;

import cn.niuman.androidcommon.R;
import cn.niuman.views.NCircleProgressBar;


/**
 * A placeholder fragment containing a simple view.
 */
public class XferImageFragment extends Fragment {

    private NumberFormat mPercentFormatter = NumberFormat.getPercentInstance();

    public XferImageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xfer_imageview, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    private void initView(View view) {
        NCircleProgressBar progressBar = (NCircleProgressBar)view.findViewById(R.id.pb_progress);
        progressBar.setProgress(120);
        progressBar.setText(mPercentFormatter.format(1f * 120/360));
    }
}
