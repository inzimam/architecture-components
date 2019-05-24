package com.inzy.practice;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.inzy.practice.entity.Blog;
import com.inzy.practice.entity.BlogWrapper;
import com.inzy.practice.networking.RestApiService;
import com.inzy.practice.networking.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogRepository {

    private Application application;
    private MutableLiveData<List<Blog>> mutableLiveData = new MutableLiveData<>();

    public BlogRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Blog>> getMutableLiveData() {
        RestApiService restApiService = RetrofitInstance.getApiService();
        Call<BlogWrapper> call = restApiService.getPopularBlog();
        call.enqueue(new Callback<BlogWrapper>() {
            @Override
            public void onResponse(Call<BlogWrapper> call, Response<BlogWrapper> response) {
                BlogWrapper blogWrapper = response.body();
                if (blogWrapper != null && blogWrapper.getBlog() != null) {
                    mutableLiveData.setValue(blogWrapper.getBlog());
                }
            }

            @Override
            public void onFailure(Call<BlogWrapper> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}