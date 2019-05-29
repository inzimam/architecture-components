package com.inzy.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.inzy.practice.ui.BasicViewModelActivity;
import com.inzy.practice.ui.LiveDataBasicActivity;
import com.inzy.practice.ui.LiveDataRetrofitActivity;
import com.inzy.practice.ui.LiveDataRoomActivity;
import com.inzy.practice.worker.WorkerActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, loadItems());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                Intent intent = new Intent(MainActivity.this, BasicViewModelActivity.class);
                startActivity(intent);
            } else if (position == 1) {
                Intent intent = new Intent(MainActivity.this, LiveDataBasicActivity.class);
                startActivity(intent);
            } else if (position == 2) {
                Intent intent = new Intent(MainActivity.this, LiveDataRoomActivity.class);
                startActivity(intent);
            } else if (position == 3) {
                Intent intent = new Intent(MainActivity.this, WorkerActivity.class);
                startActivity(intent);
            } else if (position == 3) {
                Intent intent = new Intent(MainActivity.this, LiveDataRetrofitActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<String> loadItems() {
        List<String> list = new ArrayList<>();
        list.add("Basic ViewModel with SQLite");
        list.add("Basic LiveData");
        list.add("Live Data + Room + MVVM");
        list.add("Basic Work Manager");
        list.add("Live Data + Retrofit");
        return list;
    }
}


