package com.example.java_story_bk.retrofit.api;

import com.example.java_story_bk.models.Chapter;
import com.example.java_story_bk.models.ChapterInfo;
import com.example.java_story_bk.models.StatisticUser;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.bodyModel.SendListStoriesIdBody;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StoryServices {

    @GET("statistics/statistics-user")
    Call<StatisticUser> getStatisticsOfUser();


    @GET("storys/get-all-storys")
    Call<ArrayList<StoryInfo>> getAllStories(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("search") String search
    );

    @GET("storys/get-all-chapters")
    Call<ArrayList<Chapter>> getAllChapters(@Query("story_id") String story_id, @Query("page") int page,
                                            @Query("limit") int limit);
    @GET("storys/get-chapter-by-id")
    Call<ChapterInfo> getChapterInfoById(@Query("chapter_id") String chapter_id);
    @GET("storys/get-next-chapter")
    Call<ChapterInfo> getNextChapter(@Query("story_id") String story_id,@Query("index") int index);
    @GET("storys/get-prev-chapter")
    Call<ChapterInfo> getPrevChapter(@Query("story_id") String story_id,@Query("index") int index);
    @POST("storys/get-list-stories-by-id")
    Call<ArrayList<StoryInfo>> getListStoriesFromId(@Body SendListStoriesIdBody listIdStories);
}
