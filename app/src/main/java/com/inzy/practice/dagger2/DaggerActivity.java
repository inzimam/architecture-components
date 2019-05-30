package com.inzy.practice.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.inzy.practice.BasicApp;
import com.inzy.practice.R;
import com.inzy.practice.entity.BlogWrapper;
import com.inzy.practice.networking.RestApiService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        ((BasicApp) getApplication()).getNetComponent().inject(this);
        listView = (ListView) findViewById(R.id.list);
        getHeroes();

    }

    private void getHeroes() {
        RestApiService restApiService = retrofit.create(RestApiService.class);
        Call<BlogWrapper> call = restApiService.getPopularBlog();
        call.enqueue(new Callback<BlogWrapper>() {
            @Override
            public void onResponse(Call<BlogWrapper> call, Response<BlogWrapper> response) {
                BlogWrapper blogWrapper = response.body();
                if (blogWrapper != null && blogWrapper.getBlog() != null) {
                    String[] heroes = new String[blogWrapper.getBlog().size()];

                    for (int i = 0; i < blogWrapper.getBlog().size(); i++) {
                        heroes[i] = blogWrapper.getBlog().get(i).getTitle();
                    }
                    listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
                }
            }

            @Override
            public void onFailure(Call<BlogWrapper> call, Throwable t) {

            }
        });
    }
}
