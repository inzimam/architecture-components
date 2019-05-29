package com.inzy.practice.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.inzy.practice.R;
import com.inzy.practice.viewmodel.LiveDataViewModel;

import java.util.List;

public class LiveDataBasicActivity extends AppCompatActivity {

    private String TAG = LiveDataBasicActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);


        /////////////////////////
        //  Below code is for initialising work manager from another activity and getting data on another activity
        ////////////////////////

        /*UUID UUID = (java.util.UUID) getIntent().getExtras().getSerializable("UUID");

        WorkManager.getInstance().getWorkInfoByIdLiveData(UUID).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null && workInfo.getState().isFinished())
                    Log.d(TAG, workInfo.getOutputData().getString(MyWorker.TASK_DESC) + "\n");
                Log.d(TAG, workInfo.getState().name() + "\n");
            }
        });*/

        ListView listView = findViewById(R.id.list);
        ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);



        LiveDataViewModel model = ViewModelProviders.of(this).get(LiveDataViewModel.class);
        model.getFruitList().observe(this, (List<String> fruitlist) -> {
            // update UI
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, fruitlist);
            // Assign adapter to ListView
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        });
    }
}


