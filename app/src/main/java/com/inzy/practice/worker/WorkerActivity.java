package com.inzy.practice.worker;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.inzy.practice.R;

import java.util.concurrent.TimeUnit;

public class WorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_activity);
        final TextView textView = findViewById(R.id.textViewStatus);


        Data data = new Data.Builder().putString(MyWorker.TASK_DESC, "data 1 ").build();
        Data data1 = new Data.Builder().putString(MyWorker.TASK_DESC, "data 2 ").build();
        Data data2 = new Data.Builder().putString(MyWorker.TASK_DESC, "data 3 ").build();
        Data dataPeriodic = new Data.Builder().putString(MyWorker.TASK_DESC, "data periodic ").build();

        Constraints constraints = new Constraints.Builder().setRequiresCharging(true).build();
        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setInputData(data)
                .setConstraints(constraints).build();
        final OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(MyWorker.class).setInputData(data1)
                .setConstraints(constraints).build();
        final OneTimeWorkRequest workRequest2 = new OneTimeWorkRequest.Builder(MyWorker.class).setInputData(data2)
                .setConstraints(constraints).build();
        final PeriodicWorkRequest periodicWorkRequest
                = new PeriodicWorkRequest.Builder(MyWorker.class, 5, TimeUnit.SECONDS).setInputData(dataPeriodic)
                .build();

        //A click listener for the button
        //inside the onClick method we will perform the work
        findViewById(R.id.buttonEnqueue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Enqueuing the work request
                WorkManager.getInstance().enqueue(workRequest);
            }
        });
        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().cancelWorkById(workRequest.getId());
                WorkManager.getInstance().cancelWorkById(periodicWorkRequest.getId());
            }
        });
        findViewById(R.id.buttonPeriodic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueue(periodicWorkRequest);

            }
        });
        findViewById(R.id.buttonChain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().
                        beginWith(workRequest)
                        .then(workRequest1)
                        .then(workRequest2)
                        .enqueue();
            }
        });

        WorkManager.getInstance().getWorkInfoByIdLiveData(periodicWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null && workInfo.getState().isFinished())
                    textView.append(workInfo.getOutputData().getString(MyWorker.TASK_DESC) + "\n");
                textView.append(workInfo.getState().name() + "\n");
            }
        });

        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null && workInfo.getState().isFinished())
                    textView.append(workInfo.getOutputData().getString(MyWorker.TASK_DESC) + "\n");
                textView.append(workInfo.getState().name() + "\n");
            }
        });
    }
}
