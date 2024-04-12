package com.example.java_story_bk.retrofit.api;

import com.example.java_story_bk.models.Chapter;
import com.example.java_story_bk.models.ChapterInfo;
import com.example.java_story_bk.models.ReadingInfoOfUser;
import com.example.java_story_bk.models.StatisticUser;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.StoryInfoWithIdChapterUpdate;
import com.example.java_story_bk.models.UserFollowedStory;
import com.example.java_story_bk.models.bodyModel.SendListStoriesIdBody;
import com.example.java_story_bk.models.bodyModel.SendReadingInfoOfUser;
import com.example.java_story_bk.models.bodyModel.SendUpdateFollowStoryBody;

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

    @GET("storys/get-story")
    Call<StoryInfo> getStoryById(
            @Query("story_id") String story_id
    );

    @GET("storys/get-chapter-by-id")
    Call<ChapterInfo> getChapterInfoById(@Query("chapter_id") String chapter_id);

    @GET("storys/get-next-chapter")
    Call<ChapterInfo> getNextChapter(@Query("story_id") String story_id, @Query("index") int index);

    @GET("storys/get-prev-chapter")
    Call<ChapterInfo> getPrevChapter(@Query("story_id") String story_id, @Query("index") int index);

    @POST("storys/get-list-stories-by-id")
    Call<ArrayList<StoryInfo>> getListStoriesFromId(@Body SendListStoriesIdBody listIdStories);

    @POST("storys/add-read-info-for-user")
    Call<ReadingInfoOfUser> addReadingInfoOfUser(@Body SendReadingInfoOfUser history_read);

    @GET("storys/get-reading-history-for-books")
    Call<ArrayList<StoryInfo>> getReadingHistoryForBooks(@Query("device_uuid") String device_uuid, @Query("page") int page, @Query("limit") int limit, @Query("user_id") String user_id);

    @GET("storys/current-chapter-in-story")
    Call<ChapterInfo> getCurrentChapterInStory(@Query("device_uuid") String device_uuid, @Query("story_id") String story_id, @Query("user_id") String user_id);


    @GET("storys/check-user-need-synchronized")
    Call<Boolean> getCheckNeedSynchronized(@Query("device_uuid") String device_uuid, @Query("user_id") String user_id);


    @GET("storys/synchronized-for-user")
    Call<String> makeSynchronizedForUser(@Query("device_uuid") String device_uuid, @Query("user_id") String user_id, @Query("is_synchronized") boolean is_synchronized);


    @GET("storys/get-followed-stories")
    Call<ArrayList<StoryInfoWithIdChapterUpdate>> getFollowedStories(@Query("user_id") String user_id, @Query("page") int page, @Query("limit") int limit);


    @GET("storys/get-followed-story-info")
    Call<UserFollowedStory> getFollowedStoryInfo(@Query("user_id") String user_id, @Query("story_id") String story_id);


    @POST("storys/update-follow-story")
    Call<String> updateFollowStory(@Body SendUpdateFollowStoryBody info_update);

    @GET("storys/get-count-new-chapter-stories")
    Call<Integer> getCountNewChapterStories (@Query("user_id") String user_id);
}
