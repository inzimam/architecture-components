package com.inzy.practice.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.inzy.practice.R;
import com.inzy.practice.viewmodel.LiveDataViewModel;
import com.inzy.practice.worker.MyWorker;

import java.util.List;

public class LiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

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


