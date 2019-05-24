package com.inzy.practice.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.inzy.practice.BlogRepository;
import com.inzy.practice.entity.Blog;

import java.util.List;


public class LiveDataRetrofitViewModel extends AndroidViewModel {
    BlogRepository blogRepository;

    public LiveDataRetrofitViewModel(@NonNull Application application) {
        super(application);
        blogRepository = new BlogRepository(application);
    }

    public MutableLiveData<List<Blog>> getAllBlogs() {
        return blogRepository.getMutableLiveData();
    }
}
