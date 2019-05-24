package com.inzy.practice.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inzy.practice.R;
import com.inzy.practice.viewmodel.ProductListViewModel;

public class UserDataFragment extends Fragment {
    public static final String TAG = "UserDataFragment";

    private ProductListViewModel mViewModel;

    public static UserDataFragment newInstance() {
        return new UserDataFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_data_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        // TODO: Use the ViewModel
    }

}
