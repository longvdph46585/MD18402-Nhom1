package com.example.java_story_bk.retrofit.api;

import com.example.java_story_bk.models.StatisticUser;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.retrofit.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public  interface StoryServices {
    RetrofitClientInstance retrofitInstance = new RetrofitClientInstance() ;
    @GET("statistics/statistics-user")
     Call<StatisticUser> getStatisticsOfUser();


     @GET("storys/get-all-storys")
     Call<ArrayList<StoryInfo>> getAllStories(
             @Query("page") int page,
             @Query("limit") int limit,
             @Query("search") String search
     );
}
