package com.example.java_story_bk.services;

import com.example.java_story_bk.retrofit.RetrofitClientInstance;
import com.example.java_story_bk.retrofit.api.AccountServices;
import com.example.java_story_bk.retrofit.api.StoryServices;

public interface MainServices {
    static final StoryServices storyService = RetrofitClientInstance.getInstance().create(StoryServices.class);
    static  final AccountServices accountService = RetrofitClientInstance.getInstance().create(AccountServices.class);

}
