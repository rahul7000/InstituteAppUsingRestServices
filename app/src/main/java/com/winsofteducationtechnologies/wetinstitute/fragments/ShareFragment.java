package com.winsofteducationtechnologies.wetinstitute.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winsofteducationtechnologies.wetinstitute.R;

/*
        class              :       SignUp
        description        :       Used to insert the input fields and register the valid user
        arguments          :       
        return type        :
*/public class ShareFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }
}
