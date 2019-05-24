package com.inzy.practice.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.inzy.practice.R;

public class LiveDataRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_room);
        if (savedInstanceState == null) {
            ProductListFragment fragment = new ProductListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, UserDataFragment.TAG).commit();
        }
    }
}
