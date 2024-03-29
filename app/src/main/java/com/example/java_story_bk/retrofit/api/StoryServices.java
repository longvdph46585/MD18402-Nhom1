package com.example.java_story_bk.retrofit.api;

import com.example.java_story_bk.models.StatisticUser;
import com.example.java_story_bk.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

 public  interface StoryServices {
    RetrofitClientInstance retrofitInstance = new RetrofitClientInstance() ;
    @GET("statistics/statistics-user")
     Call<StatisticUser> getStatisticsOfUser();

}
