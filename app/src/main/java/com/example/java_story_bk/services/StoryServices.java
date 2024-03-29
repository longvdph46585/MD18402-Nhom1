package com.example.java_story_bk.services;

import com.example.java_story_bk.models.StatisticUser;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StoryServices {
    @GET("statistics/statistics-user")
    Call<StatisticUser> getStaticsUser();
}
