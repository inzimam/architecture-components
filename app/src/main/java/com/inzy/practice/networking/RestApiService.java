package com.inzy.practice.networking;


import com.inzy.practice.entity.BlogWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on : Feb 25, 2019
 * Author     : AndroidWave
 */
public interface RestApiService {


    @GET("feed.json")
    Call<BlogWrapper> getPopularBlog();

}
