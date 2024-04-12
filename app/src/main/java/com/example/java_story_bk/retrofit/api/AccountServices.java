package com.example.java_story_bk.retrofit.api;

import com.example.java_story_bk.models.Account;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.bodyModel.SendListStoriesIdBody;
import com.example.java_story_bk.models.bodyModel.SendLoginBody;
import com.example.java_story_bk.models.bodyModel.SendSignupBody;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountServices {
    @POST("user/login")
    Call<Account> loginUserAction(@Body SendLoginBody SendLoginBody);
    @POST("user/register")
    Call<Account> registerUserAction(@Body SendSignupBody sendSignupBody);
    @GET("user/user-info-account")
    Call<Account> getInfoAccount (@Query("user_id") String user_id);

}
